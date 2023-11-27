package com.example.caloriestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "CaloriesTracker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Foods";
    private static final String COLUMN_ID = "food_id";
    private static final String COLUMN_NAME =  "food_name";
    private static final String COLUMN_CALORIES = "food_calories";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
            "CREATE TABLE " +
            TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_CALORIES + " DOUBLE);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFood(String name, Double calories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CALORIES, calories);

        long rs = db.insert(TABLE_NAME, null, cv);

        if(rs == -1) {
            Toast.makeText(context, "Failed to add new data!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully added new data!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
