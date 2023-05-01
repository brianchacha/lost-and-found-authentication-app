package com.example.studentverification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemRegistration extends AppCompatActivity {

    private static final int REQUEST_CODE = 22;

    EditText name, colour, description;
    ImageView picture;
    Button update, takePic, viewStudentRequests, viewCollectedItems;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_registration);

        name = findViewById(R.id.itemName);
        colour = findViewById(R.id.itemColor);
        description = findViewById(R.id.itemDescription);
        picture = findViewById(R.id.itemPicture);
        viewStudentRequests = findViewById(R.id.viewDatabase);
        viewCollectedItems = findViewById(R.id.collectedItems);

        takePic = findViewById(R.id.takePic);
        update = findViewById(R.id.updateDatabase);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1, colour1, description1;
                name1 = name.getText().toString();
                colour1 = colour.getText().toString();
                description1 = description.getText().toString();

                if(TextUtils.isEmpty(name1)){
                    Toast.makeText(ItemRegistration.this, "Enter name of item", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(colour1)){
                    Toast.makeText(ItemRegistration.this, "Enter color of the item", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(description1)){
                    Toast.makeText(ItemRegistration.this, "Describe the item", Toast.LENGTH_SHORT).show();
                    return;
                }

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Collected Items");
                String key = reference.push().getKey();
                reference.child(key).child("Item Name").setValue(name1);
                reference.child(key).child("Item Color").setValue(colour1);
                reference.child(key).child("Item Description").setValue(description1);

                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(ItemRegistration.this);
                myDB.addBook(name.getText().toString().trim(),
                        colour.getText().toString().trim(),
                        description.getText().toString().trim());

                name.setText("");
                colour.setText("");
                description.setText("");
            }
        });

        viewCollectedItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CollectedItems.class);
                startActivity(intent);
                finish();
            }
        });

        viewStudentRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminRequestView.class);
                startActivity(intent);
                finish();
            }
        });

        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent, new Bundle(REQUEST_CODE));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(photo);
        }
        else{
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}