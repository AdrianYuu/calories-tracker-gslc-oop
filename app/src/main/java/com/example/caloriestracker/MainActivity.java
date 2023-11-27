package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private DatabaseHelper db;
    private ArrayList<Food> foodList;
    private CustomAdapter customAdapter;
    private TextView totalCaloriesTxt;
    private Double totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        totalCaloriesTxt = findViewById(R.id.totalCalories);
        addButton = findViewById(R.id.addButton);
        db = new DatabaseHelper(MainActivity.this);
        foodList = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFood.class);
                startActivity(intent);
            }
        });

        storeDataToArrayList();

        totalCaloriesTxt.setText("Total Calories: " + String.format("%.2f", totalCalories));

        customAdapter = new CustomAdapter(MainActivity.this, foodList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void storeDataToArrayList(){
        totalCalories = 0.0;
        Cursor cursor = db.readData();

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()){
                foodList.add(new Food(cursor.getString(1), cursor.getDouble(2)));
                totalCalories += cursor.getDouble(2);
            }
        }
    }
}