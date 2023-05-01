package com.example.studentverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Button button, regUser, regItem;
    EditText password;
    LinearLayout layout1;
    TextView registeredItem, studentRequests;

    MyDatabaseHelper2 myDB1;
    MyDatabaseHelper myDB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.loginButton);
        password = findViewById(R.id.editPassword);
        layout1 = findViewById(R.id.option1);
        regUser = findViewById(R.id.regUser);
        regItem = findViewById(R.id.regItem);
        registeredItem = findViewById(R.id.adminRegCount);
        studentRequests = findViewById(R.id.adminRequestCount);

        myDB1 = new MyDatabaseHelper2(this);
        myDB2 = new MyDatabaseHelper(this);
        registeredItem.setText(myDB1.getCount());
        studentRequests.setText(myDB2.getCount());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String passcode = password.getText().toString();

                if(passcode.equals("001brianz")){

                    layout1.setVisibility(View.VISIBLE);

                    regUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), AtivationPage2B.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    regItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), ItemRegistration.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity2.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(getApplicationContext(), AdminManual.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}