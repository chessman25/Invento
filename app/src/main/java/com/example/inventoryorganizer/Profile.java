package com.example.inventoryorganizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    private TextView profileUsername, profileEmail;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        Button changeProfile = findViewById(R.id.changeProf);
        profileUsername = findViewById(R.id.Profile_Username);
        profileEmail = findViewById(R.id.Profile_Email);
        BottomNavigationView bottom_nav = findViewById(R.id.bottomNavigationView);
        bottom_nav.setSelectedItemId(R.id.profile);
        db = new Database(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String loggedInUserEmail = sharedPreferences.getString("loggedInUserEmail", "");

        Log.d("ProfileActivity", "Logged in user email: " + loggedInUserEmail);

        if (loggedInUserEmail != null && !loggedInUserEmail.isEmpty()) {
            Cursor cursor = null;
            try {
                cursor = db.getUserDetails(loggedInUserEmail);
                if (cursor != null && cursor.moveToFirst()) {
                    int usernameIndex = cursor.getColumnIndex("USERNAME");
                    int emailIndex = cursor.getColumnIndex("EMAIL");

                    if (usernameIndex != -1 && emailIndex != -1) {
                        String username = cursor.getString(usernameIndex);
                        String email = cursor.getString(emailIndex);

                        Log.d("ProfileActivity", "Username: " + username + ", Email: " + email);

                        profileUsername.setText(username);
                        profileEmail.setText(email);
                    } else {
                        Log.d("ProfileActivity", "Invalid column index");
                    }
                } else {
                    Log.d("ProfileActivity", "No user found with email: " + loggedInUserEmail);
                    Toast.makeText(this, "No user found.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("ProfileActivity", "Error fetching user details", e);
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } else {
            Toast.makeText(this, "No user is currently logged in.", Toast.LENGTH_SHORT).show();
        }


        // Logout button implementation
        findViewById(R.id.profile_logout).setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("loggedInUserEmail");
            editor.apply();

            Intent intent = new Intent(Profile.this, MainActivity2.class);
            startActivity(intent);
            finish();
        });


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