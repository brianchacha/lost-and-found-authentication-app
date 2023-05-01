package com.example.studentverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminManual extends AppCompatActivity {

    LinearLayout layout1, layout2, layout3, layout4;
    TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manual);

        layout1 = findViewById(R.id.registerUserManual);
        layout2 = findViewById(R.id.registerItemManual);
        layout3 = findViewById(R.id.updateManual);
        layout4 = findViewById(R.id.sendMessageManual);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.VISIBLE);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout2.setVisibility(View.VISIBLE);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout3.setVisibility(View.VISIBLE);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout4.setVisibility(View.VISIBLE);
            }
        });

    }
}