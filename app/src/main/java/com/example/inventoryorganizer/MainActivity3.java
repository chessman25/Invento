package com.example.inventoryorganizer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private EditText etPassword;
    private EditText etEmail;
    private EditText etConfirmPassword;
    private TextView tvConfirmPasswordWarning;
    private TextView tvPasswordWarning;
    private TextView tvEmailWarning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        Button backButton = findViewById(R.id.backBtn);
        Button confirmButton = findViewById(R.id.cBtn);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.pass);
        tvPasswordWarning = findViewById(R.id.tv_password_warning);
        tvEmailWarning = findViewById(R.id.tv_email_warning);
        etConfirmPassword = findViewById(R.id.confirmPassword);
        tvConfirmPasswordWarning = findViewById(R.id.tv_confirm_password_warning);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePassword(s.toString());
                validateConfirmPassword(etPassword.getText().toString(), etConfirmPassword.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });
        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateConfirmPassword(etPassword.getText().toString(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });




        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
    private void validateEmail(String email) {
        if (!email.contains("@") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvEmailWarning.setVisibility(TextView.VISIBLE);
        } else {
            tvEmailWarning.setVisibility(TextView.GONE);
        }
    }
    private void validatePassword(String password) {
        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*")) {
            tvPasswordWarning.setVisibility(TextView.VISIBLE);
        } else {
            tvPasswordWarning.setVisibility(TextView.GONE);
        }
    }
    private void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            tvConfirmPasswordWarning.setVisibility(TextView.VISIBLE);
        } else {
            tvConfirmPasswordWarning.setVisibility(TextView.GONE);
        }
    }
    }