package com.example.inventoryorganizer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView emailWarningTextView;
    private TextView passwordWarningTextView;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        database = new Database(this);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.Password); // Ensure ID matches the XML
        Button loginButton = findViewById(R.id.loginBtn);
        TextView createButton = findViewById(R.id.createButton);
        Button exitButton = findViewById(R.id.exitBtn);
        emailWarningTextView = findViewById(R.id.tv_email_warning);
        passwordWarningTextView = findViewById(R.id.tv_password_warning);


        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (database.validateUserLogin(email, password)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("loggedInUserEmail", email);
                    editor.apply();

                    Log.d("LoginActivity", "Saved loggedInUserEmail: " + email);

                    Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void validateEmail(String email) {
        if (!email.contains("@") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailWarningTextView.setVisibility(TextView.VISIBLE);
            emailWarningTextView.setText("Invalid email format");
        } else {
            emailWarningTextView.setVisibility(TextView.GONE);
        }
    }

    private void validatePassword(String password) {
        if (password.isEmpty()) {
            passwordWarningTextView.setVisibility(TextView.VISIBLE);
            passwordWarningTextView.setText("Password cannot be empty");
        } else {
            passwordWarningTextView.setVisibility(TextView.GONE);
        }
    }
}
