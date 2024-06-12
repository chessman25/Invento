package com.example.inventoryorganizer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class modify extends AppCompatActivity {

    EditText editTextProductID, editTextProduct, editTextCategory, editTextPrice, editTextNumber;
    Button updateAddItem, resetButton;
    Database mydb;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify);

        mydb = new Database(this);

        editTextProductID = findViewById(R.id.editTextProductID);
        editTextProduct = findViewById(R.id.editTextProduct);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextNumber = findViewById(R.id.editTextNumber);
        updateAddItem = findViewById(R.id.updateAddItem);
        resetButton = findViewById(R.id.btnReset);


        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userEmail = sharedPreferences.getString("userEmail", "default@example.com");

        Button backButton = findViewById(R.id.backV);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        update();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    public void update() {
        updateAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productID = editTextProductID.getText().toString();
                String productName = editTextProduct.getText().toString();
                String category = editTextCategory.getText().toString();
                String priceText = editTextPrice.getText().toString();
                String numberText = editTextNumber.getText().toString();

                if (productID.isEmpty() || productName.isEmpty() || category.isEmpty() || priceText.isEmpty() || numberText.isEmpty()) {
                    Toast.makeText(modify.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int productIDText;
                double price;
                int number;
                try {
                    productIDText = Integer.parseInt(productID);
                } catch (NumberFormatException e) {
                    Toast.makeText(modify.this, "Please enter a valid number for product ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    price = Double.parseDouble(priceText);
                } catch (NumberFormatException e) {
                    Toast.makeText(modify.this, "Please enter a valid number for price", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    number = Integer.parseInt(numberText);
                } catch (NumberFormatException e) {
                    Toast.makeText(modify.this, "Please enter a valid integer for quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (category.matches(".*\\d.*")) {
                    Toast.makeText(modify.this, "Category should not contain numbers", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (productName.matches("\\d+")) {
                    Toast.makeText(modify.this, "Product name cannot be entirely numeric", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean updated = mydb.updateProduct(
                        productID,
                        productName,
                        category,
                        price,
                        number
                );




                if (updated) {
                    Toast.makeText(modify.this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
                    resetFields();
                } else {
                    Toast.makeText(modify.this, "Product ID does not exist in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetFields() {
        editTextProductID.setText("");
        editTextProduct.setText("");
        editTextCategory.setText("");
        editTextPrice.setText("");
        editTextNumber.setText("");
    }
}
