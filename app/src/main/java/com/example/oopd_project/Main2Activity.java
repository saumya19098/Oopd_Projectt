package com.example.oopd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    Button User_Register_Button;
    Button User_Login_Button;
    EditText editTextEmail,editTextPassword;
    FirebaseAuth mFirebaseAuth;
    final Context context=this;
    Boolean userlogin=false;
    DatabaseReference ref,pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextEmail = (EditText) findViewById(R.id.userloginemail);
        editTextPassword = (EditText) findViewById(R.id.userloginpass);
        User_Register_Button=(Button)findViewById(R.id.User_Register);
        User_Login_Button=(Button)findViewById(R.id.UserLogin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        User_Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });

    }
    private void userlogin()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter valid emailid");
//            editTextEmail.requestFocus();
//            return;
//        }
        if (password.isEmpty()) {
            editTextPassword.setError("Enter Password");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimun length of password is 6");
            editTextPassword.requestFocus();
            return;
        }

        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(Main2Activity.this, Add_to_cart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    userlogin=true;
                    Toast.makeText(getApplicationContext(),"data saved",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    protected void onStart() {
//
//        super.onStart();
//        if (mFirebaseAuth.getCurrentUser()!=null)
//        {
//            finish();
//            startActivity(new Intent(this,check.class));
//        }
//    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.User_Register:
                finish();
                Intent inte = new Intent(this, Register_User.class);
                startActivity(inte);
                break;
            case R.id.UserLogin:
                userlogin();
                break;
        }

    }
}
