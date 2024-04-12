package com.example.clothingca;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClothingItemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClothingItemAdapter adapter;
    private List<ClothingItem> clothingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_list);

        recyclerView = findViewById(R.id.recyclerViewClothingItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initializeData();

        // Ensure to pass the correct boolean flag for the context (e.g., false for adding items)
        adapter = new ClothingItemAdapter(this, clothingItems, false); // Assuming this is for adding items to the basket
        recyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        clothingItems = new ArrayList<>();
        // Sample data
        clothingItems.add(new ClothingItem("1", "T-Shirt", 19.99, 10));
        clothingItems.add(new ClothingItem("2", "Jeans", 39.99, 20));
        clothingItems.add(new ClothingItem("3", "Hoodie", 59.99, 15));
    }
}
