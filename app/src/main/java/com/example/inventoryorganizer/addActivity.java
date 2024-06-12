package com.example.inventoryorganizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {

    private EditText editTextProductID, editTextProduct, editTextCategory, editTextPrice, editTextNumber;
    private Button buttonAddItem, buttonReset, buttonBack;
    private Database inventoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        editTextProductID = findViewById(R.id.editTextProductID);
        editTextProduct = findViewById(R.id.editTextProduct);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonReset = findViewById(R.id.buttonReset);
        buttonBack = findViewById(R.id.backButton);

        inventoryDatabase = new Database(this);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addItem() {
        String productID = editTextProductID.getText().toString();
        String productName = editTextProduct.getText().toString();
        String category = editTextCategory.getText().toString();
        String priceText = editTextPrice.getText().toString();
        String numberText = editTextNumber.getText().toString();

        if (productID.isEmpty() || productName.isEmpty() || category.isEmpty() || priceText.isEmpty() || numberText.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        int number;

        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for price", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            number = Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid integer for quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        if (category.matches(".*\\d.*")) {
            Toast.makeText(this, "Category should not contain numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        if (productName.matches("\\d+")) {
            Toast.makeText(this, "Product name cannot be entirely numeric", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = inventoryDatabase.insertProduct(productID, productName, category, price, number);

        if (isInserted) {
            Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();

            resetFields();
        } else {
            Toast.makeText(this, "Failed to add item", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        editTextProductID.setText("");
        editTextProduct.setText("");
        editTextCategory.setText("");
        editTextPrice.setText("");
        editTextNumber.setText("");
    }
}
