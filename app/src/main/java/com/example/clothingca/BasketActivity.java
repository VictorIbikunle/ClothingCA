package com.example.clothingca;

import android.content.Intent;
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
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView = findViewById(R.id.basketRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalAmount = findViewById(R.id.totalAmount);
        checkoutButton = findViewById(R.id.homeButton);

        adapter = new ClothingItemAdapter(this, basketItems, true);
        recyclerView.setAdapter(adapter);

        fetchBasketItems();
        setupCheckoutButton();
    }

    private void fetchBasketItems() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("basket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                basketItems.clear();
                double total = 0.0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClothingItem item = snapshot.getValue(ClothingItem.class);
                    if (item != null) {
                        basketItems.add(item);
                        total += item.getPrice();
                    }
                }
                adapter.notifyDataSetChanged();
                totalAmount.setText(String.format("Total: $%.2f", total));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BasketActivity.this, "Failed to load basket items: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupCheckoutButton() {
        checkoutButton.setOnClickListener(v -> goToCheckout());
    }

    private void goToCheckout() {
        Intent intent = new Intent(BasketActivity.this, Checkout.class);
        intent.putExtra("basketItems", new ArrayList<>(basketItems));
        intent.putExtra("totalAmount", totalAmount.getText().toString());
        startActivity(intent);
    }
}