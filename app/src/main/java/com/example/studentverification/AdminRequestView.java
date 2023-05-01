package com.example.studentverification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminRequestView extends AppCompatActivity {

    RecyclerView rview;
    Button backButton;

    MyDatabaseHelper myDb;
    ArrayList<String> userId, itemName, itemColor, itemDescription;
    CustomAdaptor customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_view);

        rview = findViewById(R.id.rView3);
        backButton = findViewById(R.id.backButton);

        myDb = new MyDatabaseHelper(AdminRequestView.this);
        userId = new ArrayList<>();
        itemName = new ArrayList<>();
        itemColor = new ArrayList<>();
        itemDescription = new ArrayList<>();

        displayData();

        customAdaptor = new CustomAdaptor(AdminRequestView.this, userId, itemName, itemColor, itemDescription);
        rview.setAdapter(customAdaptor);
        rview.setLayoutManager(new LinearLayoutManager(AdminRequestView.this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemRegistration.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void displayData(){
        Cursor cursor = myDb.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                userId.add(cursor.getString(0));
                itemName.add(cursor.getString(1));
                itemColor.add(cursor.getString(2));
                itemDescription.add(cursor.getString(3));
            }
        }
    }

}