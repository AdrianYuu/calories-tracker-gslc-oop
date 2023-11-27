package com.example.caloriestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddFood extends AppCompatActivity {
    private FloatingActionButton backButton;
    private EditText nameInput, caloriesInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        nameInput = findViewById(R.id.nameInput);
        caloriesInput = findViewById(R.id.caloriesInput);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper DB = new DatabaseHelper(AddFood.this);
                DB.addFood(nameInput.getText().toString().trim(), Double.valueOf(caloriesInput.getText().toString().trim()));

                Intent intent = new Intent(AddFood.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFood.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}