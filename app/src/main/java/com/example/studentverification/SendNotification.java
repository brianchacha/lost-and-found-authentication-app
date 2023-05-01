package com.example.studentverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendNotification extends AppCompatActivity {

    EditText phoneNumber, message;
    Button sendButton, backButton;

    String id, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        ActivityCompat.requestPermissions(SendNotification.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);



        phoneNumber = findViewById(R.id.editNumber);
        message = findViewById(R.id.editMessage);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton2);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sms = message.getText().toString();
                String userNumber = phoneNumber.getText().toString();

                SmsManager mySmsManager = SmsManager.getDefault();
                mySmsManager.sendTextMessage(userNumber, null, sms, null, null);

                MyDatabaseHelper myDB = new MyDatabaseHelper(SendNotification.this);
                myDB.deleteOneRow(id);

                Toast.makeText(SendNotification.this, "Student Request Successfully Removed", Toast.LENGTH_SHORT).show();

            }
        });

        getAndSetIntentData();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemRegistration.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")){

            //getting data from intent
            id = getIntent().getStringExtra("id");
            phone = getIntent().getStringExtra("name");

            //setting intent data
            phoneNumber.setText("0" + phone);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

}