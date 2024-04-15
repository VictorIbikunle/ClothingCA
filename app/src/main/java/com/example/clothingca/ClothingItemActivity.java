package com.example.clothingca;

import android.os.Bundle;
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
        clothingItems = new ArrayList<>();
        adapter = new ClothingItemAdapter(this, clothingItems, false); // Assuming this is for viewing items
        recyclerView.setAdapter(adapter);

        loadClothingItems();
    }

    private void loadClothingItems() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("stock");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clothingItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClothingItem item = snapshot.getValue(ClothingItem.class);
                    if (item != null) {
                        clothingItems.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ClothingItemActivity.this, "Failed to load items.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
