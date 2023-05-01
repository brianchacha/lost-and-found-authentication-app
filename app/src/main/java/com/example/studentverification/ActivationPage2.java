package com.example.studentverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivationPage2 extends AppCompatActivity {

    EditText name, password;
    FirebaseAuth mAuth;
    FirebaseUser user;
    ProgressBar progressbar;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Activationpage3.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation_page2);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please Wait...");

        Button registerButton = findViewById(R.id.registerButton);
        Button loginButton = findViewById(R.id.loginButton);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        progressbar = findViewById(R.id.progressbar);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                String name1, password1;
                name1 = name.getText().toString();
                password1 = password.getText().toString();

                if(TextUtils.isEmpty(name1)){
                    Toast.makeText(ActivationPage2.this, "Enter Email.", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(ActivationPage2.this, "Enter your Password.", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }

                mAuth.signInWithEmailAndPassword(name1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    progress.hide();
                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(mAuth.getCurrentUser().isEmailVerified()){
                                                Intent intent = new Intent(getApplicationContext(), StudentItemView.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(ActivationPage2.this, "Verify your Email", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }
                                    });

                                } else {
                                    progress.hide();
                                    Toast.makeText(ActivationPage2.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    name.setText("");
                                    password.setText("");
                                }
                            }
                        });

            }
        });
    }
}