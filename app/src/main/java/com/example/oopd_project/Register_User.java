package com.example.oopd_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_User extends AppCompatActivity implements View.OnClickListener {
    EditText editname_userregister, editPassword_userregister, editConfirmpassword_userregister, editEmail_userregister,editaddress_userregister
            ,editphone_userregister;
    static Pattern pat = Pattern.compile("^[0-9a-zA-Z\\s]*.@iiitd.ac.in$");
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__user);
        editname_userregister = (EditText) findViewById(R.id.userregistername);
        editPassword_userregister = (EditText) findViewById(R.id.userregisterpass);
        editConfirmpassword_userregister = (EditText) findViewById(R.id.userregisterconpass);
        editEmail_userregister = (EditText) findViewById(R.id.userregisteremail);
        editaddress_userregister =(EditText) findViewById(R.id.userregisteraddress);
        editphone_userregister=(EditText) findViewById(R.id.userregisterphone);
        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        //DatabaseReference data1 = mDatabase.child("");

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.userregistersubmit).setOnClickListener(this);
        findViewById(R.id.userregistercancel).setOnClickListener(this);
    }
    private void registeruser() {
        final String Username, Email_Register;
        final String Password_Register,Confirmpassword,Phone,Address;

        Username = editname_userregister.getText().toString().trim();
        Password_Register = editPassword_userregister.getText().toString().trim();
        Confirmpassword = editConfirmpassword_userregister.getText().toString().trim();
        Email_Register = editEmail_userregister.getText().toString().trim();
        Phone=editphone_userregister.getText().toString().trim();
        Address=editaddress_userregister.getText().toString().trim();
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
            editEmail_userregister.setError("Email is required");
            editEmail_userregister.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email_Register).matches()) {
            editEmail_userregister.setError("Enter valid emailid");
            editEmail_userregister.requestFocus();
            return;
        }
        if (Password_Register.isEmpty()) {
            editPassword_userregister.setError("Enter Password");
            editPassword_userregister.requestFocus();
            //    Credentials = true;
            return;
        }
        if (Password_Register.length() < 6) {
            editPassword_userregister.setError("Minimun length of password is 6");
            editPassword_userregister.requestFocus();
            // Credentials = true;
            return;
        }
        if (Confirmpassword.isEmpty()) {
            editConfirmpassword_userregister.setError("Enter Confirm Password");
            editConfirmpassword_userregister.requestFocus();
            //Credentials = true;
            return;
        }
        if (!( Password_Register.equals(Confirmpassword))) {
            editConfirmpassword_userregister.setError("Password & confirm password must be same");
            editConfirmpassword_userregister.requestFocus();
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
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            { finish();
                                Toast.makeText(Register_User.this,"Data saved",Toast.LENGTH_LONG).show();
                                Profile_user profile = new Profile_user(Username,Phone,Address,Email_Register);
                                Log.d("profile", "profile details");
                            }
                            else
                            {
                                //failure
                                Toast.makeText(Register_User.this,"Data Not saved",Toast.LENGTH_LONG).show();
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
            case R.id.userregistersubmit:
                startActivity(new Intent(Register_User.this,Main2Activity.class));
                registeruser();

                break;
            case R.id.userregistercancel:
                startActivity(new Intent(Register_User.this,MainActivity.class));
                break;

        }
    }
}
