package com.example.inventoryorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottom_nav = findViewById(R.id.bottomNavigationView);
        bottom_nav.setSelectedItemId(R.id.homeNav);

        CardView addButton = findViewById(R.id.addBtn);
        CardView updateButton = findViewById(R.id.updateBtn);
        CardView deleteButton = findViewById(R.id.deleteBtn);
        CardView viewButton = findViewById(R.id.selectBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, addActivity.class);
                startActivity(intent);

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, modify.class);
                startActivity(intent);
            }
        });

        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() == R.id.homeNav) {
                return  true;
            }

            return false;
        });

    }
    }