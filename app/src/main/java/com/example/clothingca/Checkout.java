package com.example.clothingca;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClothingItemAdapter adapter;
    private TextView totalAmount;
    private List<ClothingItem> basketItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerView = findViewById(R.id.checkoutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalAmount = findViewById(R.id.totalAmount);

        // Deserialize the basket items if they were passed as serializable
        basketItems = (List<ClothingItem>) getIntent().getSerializableExtra("basketItems");
        if (basketItems == null) {
            basketItems = new ArrayList<>();
            Toast.makeText(this, "No items passed to checkout", Toast.LENGTH_SHORT).show();
        }

        // Set the total amount from intent extra
        totalAmount.setText(getIntent().getStringExtra("totalAmount"));

        // Initialize the adapter with the list of items to display
        adapter = new ClothingItemAdapter(this, basketItems, false); // false indicates it's not the basket view
        recyclerView.setAdapter(adapter);
    }
}
