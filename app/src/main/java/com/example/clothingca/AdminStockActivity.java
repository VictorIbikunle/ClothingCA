package com.example.clothingca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminStockActivity extends AppCompatActivity {
    EditText titleField, manufacturerField, priceField, categoryField;
    TextView adminInfo;
    Button submitStockButton, viewStockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stock);

        initializeViews();
        setupButtonListeners();
    }

    private void initializeViews() {
        adminInfo = findViewById(R.id.adminInfo);
        titleField = findViewById(R.id.titleField);
        manufacturerField = findViewById(R.id.manufacturerField);
        priceField = findViewById(R.id.priceField);
        categoryField = findViewById(R.id.categoryField);
        submitStockButton = findViewById(R.id.submitStockButton);
        viewStockButton = findViewById(R.id.viewStockButton);
    }

    private void setupButtonListeners() {
        submitStockButton.setOnClickListener(v -> addStockItem());
        viewStockButton.setOnClickListener(v -> viewStock());
    }

    private void addStockItem() {
        // Logic to add stock item to database
        Toast.makeText(this, "Stock item added.", Toast.LENGTH_SHORT).show();
    }

    private void viewStock() {
        // Start the ViewStockActivity
        Intent intent = new Intent(this, ViewStockActivity.class);
        startActivity(intent);
    }
}
