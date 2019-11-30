package com.example.oopd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button User_Register_Button;
    Button User_Login_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        User_Register_Button=(Button)findViewById(R.id.User_Register);
        User_Login_Button=(Button)findViewById(R.id.User_Login);

        User_Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register_User.class);
                startActivity(intent);
            }
        });
        /*User_Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Shopping_Page.class);
                startActivity(intent);

            }
        });*/
    }
}
