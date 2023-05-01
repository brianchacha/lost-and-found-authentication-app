package com.example.studentverification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRegisteredDetails extends AppCompatActivity {
    
    EditText updateName, updateColor, updateDescription;
    Button updateButton, deleteButton, backButton;

    String id, name, color, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_registered_details);

        updateName = findViewById(R.id.updateName);
        updateColor = findViewById(R.id.updateColor);
        updateDescription = findViewById(R.id.updateDescription);
        updateButton = findViewById(R.id.updateRegButton);
        deleteButton = findViewById(R.id.deleteRegButton);
        backButton = findViewById(R.id.backButton3);

        //call the getAndSetIntent before updateData()
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(UpdateRegisteredDetails.this);
                myDB.updateData(id, name, color, description);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CollectedItems.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("color") && getIntent().hasExtra("description")){

            //getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            color = getIntent().getStringExtra("color");
            description = getIntent().getStringExtra("description");

            //setting intent data
            updateName.setText(name);
            updateColor.setText(color);
            updateDescription.setText(description);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + "?");
        builder.setMessage("Are you sure you want to Delete " + name + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(UpdateRegisteredDetails.this);
                myDB.deleteOneRow(id);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Nothing to happen.
            }
        });
        builder.create().show();
    }

}