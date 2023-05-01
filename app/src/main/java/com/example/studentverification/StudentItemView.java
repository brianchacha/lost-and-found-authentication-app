package com.example.studentverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentItemView extends AppCompatActivity {

    Button exitButton, updateButton, paymentButton;
    TextView info;

    FirebaseDatabase db;
    DatabaseReference reference;

    MyDatabaseHelper2 myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_item_view);

        exitButton = findViewById(R.id.exitButton);
        updateButton = findViewById(R.id.updateButton);
        info = findViewById(R.id.itemCount);
        paymentButton = findViewById(R.id.paymenteButton);

        collectedItems();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), Activationpage3.class);
                startActivity(intent);
                finish();
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                startActivity(intent);
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), ActivationPage2.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void collectedItems(){

        myDB = new MyDatabaseHelper2(this);
        String sum = myDB.getCount();
        info.setText(sum);

    }

}