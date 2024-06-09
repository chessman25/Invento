package com.example.inventoryorganizer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;




public class MainActivity4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        BottomNavigationView bottom_nav = findViewById(R.id.bottomNavigationView);
        bottom_nav.setSelectedItemId(R.id.homeNav);

        bottom_nav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            } else if (item.getItemId() == R.id.homeNav) {
                startActivity(new Intent(getApplicationContext(), home.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }

            return false;
        });
    }
}
