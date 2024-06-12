package com.example.inventoryorganizer;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class view extends AppCompatActivity {
    private LinearLayout productContainer;
    private Database dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);


        dbHelper = new Database(this);


        productContainer = findViewById(R.id.productContainer);

        displayProductData();

        Button backButton = findViewById(R.id.VBACK);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayProductData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                Database.PRODUCT_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String productId = cursor.getString(cursor.getColumnIndex(Database.PRODUCT_COL_1));
            String productName = cursor.getString(cursor.getColumnIndex(Database.PRODUCT_COL_2));
            String category = cursor.getString(cursor.getColumnIndex(Database.PRODUCT_COL_3));
            double price = cursor.getDouble(cursor.getColumnIndex(Database.PRODUCT_COL_4));
            int quantity = cursor.getInt(cursor.getColumnIndex(Database.PRODUCT_COL_5));

            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 20);
            cardView.setLayoutParams(layoutParams);
            cardView.setBackgroundResource(R.drawable.bgscroll);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(layoutParams);
            cardView.addView(linearLayout);

            TextView productIdTextView = new TextView(this);
            productIdTextView.setId(View.generateViewId());
            productIdTextView.setMaxLines(1);
            productIdTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            productIdTextView.setTextSize(14);
            productIdTextView.setPadding(64, 16, 16, 16);
            productIdTextView.setText("Product ID: " + productId);
            linearLayout.addView(productIdTextView);

            TextView productNameTextView = new TextView(this);
            productNameTextView.setId(View.generateViewId());
            productNameTextView.setMaxLines(1);
            productNameTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            productNameTextView.setTextSize(14);
            productNameTextView.setPadding(64, 16, 16, 16);
            productNameTextView.setText("Product Name: " + productName);
            linearLayout.addView(productNameTextView);

            TextView productCategoryTextView = new TextView(this);
            productCategoryTextView.setId(View.generateViewId());
            productCategoryTextView.setMaxLines(1);
            productCategoryTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            productCategoryTextView.setTextSize(14);
            productCategoryTextView.setPadding(64, 16, 16, 16);
            productCategoryTextView.setText("Category: " + category);
            linearLayout.addView(productCategoryTextView);

            TextView productPriceTextView = new TextView(this);
            productPriceTextView.setId(View.generateViewId());
            productPriceTextView.setMaxLines(1);
            productPriceTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            productPriceTextView.setTextSize(14);
            productPriceTextView.setPadding(64, 16, 16, 16);
            productPriceTextView.setText("Price: " + price);
            linearLayout.addView(productPriceTextView);

            TextView productQuantityTextView = new TextView(this);
            productQuantityTextView.setId(View.generateViewId());
            productQuantityTextView.setMaxLines(1);
            productQuantityTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            productQuantityTextView.setTextSize(14);
            productQuantityTextView.setPadding(64, 16, 16, 16);
            productQuantityTextView.setText("Quantity: " + quantity);
            linearLayout.addView(productQuantityTextView);

            productContainer.addView(cardView);
        }

        cursor.close();
        db.close();
    }
}