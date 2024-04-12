package com.example.clothingca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class BasketActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClothingItemAdapter adapter;
    private List<ClothingItem> basketItems = new ArrayList<>();
    private TextView totalAmount;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView = findViewById(R.id.basketRecyclerView); // Updated ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalAmount = findViewById(R.id.totalAmount);
        homeButton = findViewById(R.id.homeButton);

        adapter = new ClothingItemAdapter(this, basketItems, true);
        recyclerView.setAdapter(adapter);

        fetchBasketItems();
    }

    private void fetchBasketItems() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("basket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                basketItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClothingItem item = snapshot.getValue(ClothingItem.class);
                    basketItems.add(item);
                }
                adapter.notifyDataSetChanged();
                updateTotalAmount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BasketActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotalAmount() {
        double total = 0.0;
        for (ClothingItem item : basketItems) {
            total += item.getPrice();
        }
        totalAmount.setText(String.format("Total: $%.2f", total));
    }
}
