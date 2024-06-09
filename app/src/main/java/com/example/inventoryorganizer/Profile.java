package com.example.inventoryorganizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottom_nav = findViewById(R.id.bottomNavigationView);
        bottom_nav.setSelectedItemId(R.id.profile);
        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeNav) {
                startActivity(new Intent(getApplicationContext(), home.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() ==R.id.profile) {
                return  true;
            }

            return false;
        });

    }
}