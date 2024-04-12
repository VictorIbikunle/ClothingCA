package com.example.clothingca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClothingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClothingItemAdapter adapter;
    private List<ClothingItem> clothingItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_list);

        recyclerView = findViewById(R.id.recyclerViewClothingItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Update here: pass 'false' for isCheckout as this is not the checkout context
        adapter = new ClothingItemAdapter(this, clothingItems, false);
        recyclerView.setAdapter(adapter);

        fetchClothingItemsFromFirebase();
    }

    private void fetchClothingItemsFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clothingItems");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clothingItems.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ClothingItem item = postSnapshot.getValue(ClothingItem.class);
                    clothingItems.add(item);
                }
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ClothingListActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
