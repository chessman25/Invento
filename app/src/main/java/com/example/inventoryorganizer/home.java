package com.example.inventoryorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    private String userEmail = "user@example.com";
    private String userName = "username";

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

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, addActivity.class);
            startActivity(intent);
        });

        updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, modify.class);
            startActivity(intent);
        });

        viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, view.class);
            startActivity(intent);
        });
        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, delete.class);
            startActivity(intent);
        });

        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                Log.d("home", "Navigating to Profile with email: " + userEmail + " and username: " + userName);

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("email", userEmail);
                intent.putExtra("username", userName);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() == R.id.homeNav) {
                return true;
            }

            return false;
        });
    }
}
