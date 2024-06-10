package com.example.inventoryorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;

public class addActivity extends AppCompatActivity {
    private EditText editTextProduct, editTextCategory, editTextPrice, editTextNumber;
    private Button buttonAddItem, buttonReset, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        editTextProduct = findViewById(R.id.editTextProduct);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonBack = findViewById(R.id.backButton);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}
