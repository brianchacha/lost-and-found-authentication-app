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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AtivationPage2B extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText name1, regNumber1, email1, password1, phone1;
    Button registerButton;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_ativation_page2_b);

        name1 = findViewById(R.id.name1);
        regNumber1 = findViewById(R.id.regNumber1);
        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.phone1);
        phone1 = findViewById(R.id.phoneNumber);

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please Wait...");

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.show();
                String name2, regNumber2, email2, phone2, password2;
                name2 = name1.getText().toString();
                regNumber2 = regNumber1.getText().toString();
                email2 = email1.getText().toString();
                password2 = password1.getText().toString();
                phone2 = phone1.getText().toString();

                if(TextUtils.isEmpty(name2)){
                    Toast.makeText(AtivationPage2B.this, "Enter your Names", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }
                if(TextUtils.isEmpty(regNumber2)){
                    Toast.makeText(AtivationPage2B.this, "Enter your Registration Number", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }
                if(TextUtils.isEmpty(email2)){
                    Toast.makeText(AtivationPage2B.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }
                if(TextUtils.isEmpty(phone2)){
                    Toast.makeText(AtivationPage2B.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                    progress.hide();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email2, password2)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //updating the realtime database with users
                                    Users users = new Users(name2, regNumber2, email2, phone2);
                                    db = FirebaseDatabase.getInstance();
                                    reference = db.getReference("Users");
                                    reference.child(name2).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(AtivationPage2B.this, "Realtime database Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progress.hide();
                                                Toast.makeText(AtivationPage2B.this, "Account Created, Verify Your Email Address", Toast.LENGTH_SHORT).show();

                                                if(mAuth.getCurrentUser().isEmailVerified()){
                                                    progress.hide();
                                                    Intent intent = new Intent(getApplicationContext(), Activationpage3.class);
                                                    startActivity(intent);
                                                }
                                                else{
                                                    Toast.makeText(AtivationPage2B.this, "Verify your Email", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(AtivationPage2B.this, "Email Verification failed", Toast.LENGTH_SHORT).show();
                                                progress.hide();
                                            }
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    progress.hide();
                                    Toast.makeText(AtivationPage2B.this, "Account Not Created, PLEASE RETRY", Toast.LENGTH_SHORT).show();
                                    name1.setText("");
                                    regNumber1.setText("");
                                    email1.setText("");
                                    password1.setText("");
                                    phone1.setText("");
                                }
                            }
                        });

                name1.setText("");
                regNumber1.setText("");
                email1.setText("");
                password1.setText("");
                phone1.setText("");

            }
        });

    }

}