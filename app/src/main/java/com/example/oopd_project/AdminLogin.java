package com.example.oopd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AdminLogin extends AppCompatActivity implements View.OnClickListener{
    Button admin_Register_Button;
    Button admin_Login_Button;
    EditText editTextEmail,editTextPassword;
    FirebaseAuth mFirebaseAuth1;
    DatabaseReference ref,pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        editTextEmail = (EditText) findViewById(R.id.adminloginemail);
        editTextPassword = (EditText) findViewById(R.id.adminloginpass);
        admin_Register_Button=(Button)findViewById(R.id.Admin_Register);
        admin_Login_Button=(Button)findViewById(R.id.AdminLogin);
        mFirebaseAuth1 = FirebaseAuth.getInstance();
        admin_Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register_User.class);
                startActivity(intent);
            }
        });
        admin_Login_Button.setOnClickListener(new View.OnClickListener() {
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
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter valid emailid");
            editTextEmail.requestFocus();
            return;
        }
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

        mFirebaseAuth1.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(AdminLogin.this, check.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void login(View view) {
        Intent i= new Intent(this,check.class);
        startActivity(i);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.Admin_Register:
                finish();
                Intent inte = new Intent(this, Register_Admin.class);
                startActivity(inte);
                break;
            case R.id.AdminLogin:
                userlogin();
                break;
        }

    }
}
