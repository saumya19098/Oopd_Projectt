package com.example.oopd_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changeprice extends AppCompatActivity {
Button change,cancel;
EditText changep,changepp;
    private DatabaseReference mDatabase1;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeprice);
        changep=(EditText)findViewById(R.id.itemname2);
        changepp=(EditText)findViewById(R.id.itemprice);
        change=(Button) findViewById(R.id.change_button);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase1=firebaseDatabase.getReference("Inventory");
        cancel=(Button)findViewById(R.id.Cancelp_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Admin_report.class));

            }
        });

    }
    private void change()
    {
        String item,itemprice;
        item=(changep).getText().toString();
        itemprice=(changepp).getText().toString();
        //mDatabase1.orderByKey().
        mDatabase1.child(item).setValue(itemprice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(changeprice.this, "Daata saved", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
