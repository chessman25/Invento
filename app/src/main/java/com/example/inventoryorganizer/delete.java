package com.example.inventoryorganizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete extends AppCompatActivity {
    Database mydb;
    EditText DeleteID;
    Button finaldelete;
    CheckBox confirmDeleteCheckbox;
    Button BackBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        mydb = new Database(this);

        DeleteID = findViewById(R.id.delete_ID);
        finaldelete = findViewById(R.id.finaldelete);
        confirmDeleteCheckbox = findViewById(R.id.deletecheck);
        BackBttn = findViewById(R.id.DBack);

        BackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        deleted();
    }

    public void deleted() {
        finaldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!confirmDeleteCheckbox.isChecked()) {
                    Toast.makeText(delete.this, "Please check the confirmation box to delete.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String productID = DeleteID.getText().toString();
                if (productID.isEmpty()) {
                    Toast.makeText(delete.this, "Please enter a Product ID.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer delete = mydb.deleteProduct(productID);
                if (delete > 0) {
                    Toast.makeText(delete.this, "Item deleted successfully!", Toast.LENGTH_SHORT).show();
                    DeleteID.setText("");
                } else {
                    Toast.makeText(delete.this, "Product ID not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
