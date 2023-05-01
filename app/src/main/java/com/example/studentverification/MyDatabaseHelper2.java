package com.example.studentverification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper2 extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "CollectedItems.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "collected_item";
    private static final String COLUMN_ID = "item_id";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_COLOUR = "item_colour";
    private static final String COLUMN_DESCRIPTION = "item_description";

    MyDatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_COLOUR + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    void addBook(String itemName, String itemColor, String itemDescription){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, itemName);
        cv.put(COLUMN_COLOUR, itemColor);
        cv.put(COLUMN_DESCRIPTION, itemDescription);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public String getCount(){

        SQLiteDatabase database = getReadableDatabase();
        String sQty;
        String sQuery = "SELECT COUNT(*) FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(sQuery, null);

        if(cursor.moveToFirst()){
            sQty = String.valueOf(cursor.getInt(0));
        }
        else {
            sQty = "0";
        }
        database.close();
        return sQty;

    }

    Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String colour, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_COLOUR, colour);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv, "item_name=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "item_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
