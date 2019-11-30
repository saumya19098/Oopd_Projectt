package com.example.oopd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Admin extends AppCompatActivity implements View.OnClickListener {
    EditText editname_adminregister, editPassword_adminregister, editConfirmpassword_adminregister, editEmail_adminregister,editaddress_adminregister
            ,editphone_adminregister;
    static Pattern pat = Pattern.compile("^[0-9a-zA-Z\\s]*.@iiitd.ac.in$");
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__admin);
        editname_adminregister = (EditText) findViewById(R.id.userregistername);
        editPassword_adminregister = (EditText) findViewById(R.id.userregisterpass);
        editConfirmpassword_adminregister = (EditText) findViewById(R.id.userregisterconpass);
        editEmail_adminregister = (EditText) findViewById(R.id.userregisteremail);
        editaddress_adminregister =(EditText) findViewById(R.id.userregisteraddress);
        editphone_adminregister=(EditText) findViewById(R.id.userregisterphone);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.adminregistersubmit).setOnClickListener(this);
        findViewById(R.id.adminregistercancel).setOnClickListener(this);
    }
    private void registeruser() {
        final String Username, Email_Register;
        String Password_Register,Confirmpassword,Phone,Address;

        Username = editname_adminregister.getText().toString().trim();
        Password_Register = editPassword_adminregister.getText().toString().trim();
        Confirmpassword = editConfirmpassword_adminregister.getText().toString().trim();
        Email_Register = editEmail_adminregister.getText().toString().trim();
        Phone=editphone_adminregister.getText().toString().trim();
        Address=editaddress_adminregister.getText().toString().trim();
        //Matcher matcher = pat.matcher(Email_Register);
        //  while (!Credentials)
        // {
//        if(!matcher.find()){
//            editEmail_Register.setError("Please enter iiitd email only");
//            editEmail_Register.requestFocus();
//            return;
//
//        }
        if (Email_Register.isEmpty()) {
            editEmail_adminregister.setError("Email is required");
            editEmail_adminregister.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email_Register).matches()) {
            editEmail_adminregister.setError("Enter valid emailid");
            editEmail_adminregister.requestFocus();
            return;
        }
        if (Password_Register.isEmpty()) {
            editPassword_adminregister.setError("Enter Password");
            editPassword_adminregister.requestFocus();
            //    Credentials = true;
            return;
        }
        if (Password_Register.length() < 6) {
            editPassword_adminregister.setError("Minimun length of password is 6");
            editPassword_adminregister.requestFocus();
            // Credentials = true;
            return;
        }
        if (Confirmpassword.isEmpty()) {
            editConfirmpassword_adminregister.setError("Enter Confirm Password");
            editConfirmpassword_adminregister.requestFocus();
            //Credentials = true;
            return;
        }
        if (!( Password_Register.equals(Confirmpassword))) {
            editConfirmpassword_adminregister.setError("Password & confirm password must be same");
            editConfirmpassword_adminregister.requestFocus();
            //  Credentials = true;
            return;
        }


        mAuth.createUserWithEmailAndPassword(Email_Register,Password_Register).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   finish();

                    Toast.makeText(getApplicationContext(),"User Regsitered Successfully",Toast.LENGTH_SHORT).show();
                    //Store the data to database
                    Userdetails user= new Userdetails(Username, Email_Register);
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            { finish();
                                Toast.makeText(Register_Admin.this,"Data saved",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                //failure
                                Toast.makeText(Register_Admin.this,"Data Not saved",Toast.LENGTH_LONG).show();
                            }
                        }
                    });




                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.adminregistersubmit:
                startActivity(new Intent(this,Main2Activity.class));
                registeruser();

                break;
            case R.id.adminregistercancel:
                startActivity(new Intent(this,MainActivity.class));
                break;

        }
    }
}
