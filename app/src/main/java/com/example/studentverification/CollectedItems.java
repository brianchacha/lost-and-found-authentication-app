package com.example.studentverification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CollectedItems extends AppCompatActivity {

    Button backButton;
    RecyclerView rView2;

    MyDatabaseHelper2 myDB;
    ArrayList<String> userId, itemName, itemColor, itemDescription;
    CustomAdaptor2 customAdaptor;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collected_items);

        backButton = findViewById(R.id.backButton);
        rView2 = findViewById(R.id.rView2);

        myDB = new MyDatabaseHelper2(CollectedItems.this);
        userId = new ArrayList<>();
        itemName = new ArrayList<>();
        itemColor = new ArrayList<>();
        itemDescription = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.my_row, itemName);
        //rView2.setAdapter(arrayAdapter);

        displayData();

        customAdaptor = new CustomAdaptor2(CollectedItems.this,this, userId, itemName, itemColor, itemDescription);
        rView2.setAdapter(customAdaptor);
        rView2.setLayoutManager(new LinearLayoutManager(CollectedItems.this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemRegistration.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = myDB.readAllData();
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