package com.example.inventoryorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private EditText etPassword;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etConfirmPassword;
    private TextView tvConfirmPasswordWarning;
    private TextView tvPasswordWarning;
    private TextView tvEmailWarning;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        database = new Database(this);

        Button backButton = findViewById(R.id.backBtn);
        Button confirmButton = findViewById(R.id.cBtn);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.pass);
        etUsername = findViewById(R.id.Username);
        tvPasswordWarning = findViewById(R.id.tv_password_warning);
        tvEmailWarning = findViewById(R.id.tv_email_warning);
        etConfirmPassword = findViewById(R.id.confirmPassword);
        tvConfirmPasswordWarning = findViewById(R.id.tv_confirm_password_warning);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (username.isEmpty()){
                    Toast.makeText(MainActivity3.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity3.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidPassword(password)) {
                    Toast.makeText(MainActivity3.this, "Password must be at least 8 characters long and contain at least one digit and one special character", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity3.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (database.validateUserLogin(email,password)) {
                    Toast.makeText(MainActivity3.this, "Account already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (database.isUserExists(username)) {
                    Toast.makeText(MainActivity3.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = database.insertUser(email, username, password);
                if (isInserted) {
                    Toast.makeText(MainActivity3.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(MainActivity3.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }
}
