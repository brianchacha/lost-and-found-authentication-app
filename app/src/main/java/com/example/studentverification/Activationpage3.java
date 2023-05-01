package com.example.studentverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activationpage3 extends AppCompatActivity {

    Button exit, submit;
    FirebaseUser user;
    FirebaseAuth auth;
    EditText itemName, itemColor, description, phone;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activationpage3);

        itemName = findViewById(R.id.itemName);
        itemColor = findViewById(R.id.itemColor);
        description = findViewById(R.id.itemDescription);
        phone = findViewById(R.id.phoneNumber1);

        submit = findViewById(R.id.submitDatabase);
        exit = findViewById(R.id.exitButton);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(getApplicationContext(), ActivationPage2.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Give a detailed description", Toast.LENGTH_LONG).show();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1, colour1, description1, phone1;
                name1 = itemName.getText().toString();
                colour1 = itemColor.getText().toString();
                description1 = description.getText().toString();
                phone1 = phone.getText().toString();

                if(TextUtils.isEmpty(name1)){
                    Toast.makeText(Activationpage3.this, "Enter name of item", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(colour1)){
                    Toast.makeText(Activationpage3.this, "Enter color of the item", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(description1)){
                    Toast.makeText(Activationpage3.this, "Describe the item", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone1)){
                    Toast.makeText(Activationpage3.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                }

                MyDatabaseHelper myDB = new MyDatabaseHelper(Activationpage3.this);
                myDB.addBook(Integer.parseInt(phone.getText().toString().trim()),
                        itemName.getText().toString().trim(),
                        itemColor.getText().toString().trim(),
                        description.getText().toString().trim());

                itemName.setText("");
                itemColor.setText("");
                description.setText("");
                phone.setText("");

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), ActivationPage2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}