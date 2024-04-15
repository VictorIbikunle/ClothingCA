package com.example.clothingca;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewStockActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StockItemAdapter adapter;
    private List<StockItem> stockItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);

        recyclerView = findViewById(R.id.recyclerViewStockItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StockItemAdapter(this, stockItems);
        recyclerView.setAdapter(adapter);

        loadStockItems();
    }

    private void loadStockItems() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("stock");

        // Adding a ValueEventListener to update the UI with new stock items
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<StockItem> stockItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    StockItem item = snapshot.getValue(StockItem.class);
                    if (item != null) {
                        stockItems.add(item);
                    }
                }
                // Assuming you have a method to update your RecyclerView or UI with the new list
                updateStockItems(stockItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
                Toast.makeText(getApplicationContext(), "Error loading stock items: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStockItems(List<StockItem> stockItems) {
        if (adapter != null) {
            adapter.setStockItems(stockItems);
        } else {
            adapter = new StockItemAdapter(this, stockItems);
            recyclerView.setAdapter(adapter);
        }
    }


}
