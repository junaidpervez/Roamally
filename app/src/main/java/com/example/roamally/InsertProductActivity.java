package com.example.roamally;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertProductActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, priceEditText, imageUrlEditText;
    private Button insertButton;
    private DatabaseReference databaseReference;

    private String productId;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        imageUrlEditText = findViewById(R.id.imageUrlEditText);
        insertButton = findViewById(R.id.insertButton);

        Intent intent = getIntent();
        if (intent.hasExtra("productId")) {
            isUpdating = true;
            productId = intent.getStringExtra("productId");

            titleEditText.setText(intent.getStringExtra("title"));
            descriptionEditText.setText(intent.getStringExtra("description"));
            priceEditText.setText(intent.getStringExtra("price"));
            imageUrlEditText.setText(intent.getStringExtra("imageUrl"));

            insertButton.setText("Update Product");
        } else {
            productId = databaseReference.push().getKey();
        }

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    updateProduct();
                } else {
                    insertProduct();
                }
            }
        });
    }

    private void insertProduct() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String imageUrl = imageUrlEditText.getText().toString().trim();

        Product product = new Product(productId, title, description, price, imageUrl);

        if (productId != null) {
            databaseReference.child(productId).setValue(product).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(InsertProductActivity.this, "Product inserted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(InsertProductActivity.this, "Failed to insert product", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateProduct() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String imageUrl = imageUrlEditText.getText().toString().trim();

        Product updatedProduct = new Product(productId, title, description, price, imageUrl);

        if (productId != null) {
            databaseReference.child(productId).setValue(updatedProduct).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(InsertProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(InsertProductActivity.this, "Failed to update product", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
