package com.example.clothingca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ClothingItemAdapter extends RecyclerView.Adapter<ClothingItemAdapter.ViewHolder> {
    private Context context;
    private List<ClothingItem> clothingItems;
    private boolean isBasket;

    public ClothingItemAdapter(Context context, List<ClothingItem> clothingItems, boolean isBasket) {
        this.context = context;
        this.clothingItems = clothingItems;
        this.isBasket = isBasket;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.clothing_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothingItem item = clothingItems.get(position);
        holder.itemName.setText(item.getName());
        holder.itemManufacturer.setText(item.getManufacturer());
        holder.itemPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.itemCategory.setText(item.getCategory());

        if (isBasket) {
            holder.actionButton.setText("Remove");
            holder.actionButton.setOnClickListener(v -> removeItemFromBasket(item, position));
        } else {
            holder.actionButton.setText("Add to Basket");
            holder.actionButton.setOnClickListener(v -> addToBasket(item));
        }
    }

    @Override
    public int getItemCount() {
        return clothingItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemManufacturer, itemPrice, itemCategory;
        Button actionButton;

        ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemManufacturer = itemView.findViewById(R.id.itemManufacturer);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            actionButton = itemView.findViewById(R.id.addToBasketButton);
        }
    }

    private void addToBasket(ClothingItem item) {
        DatabaseReference basketRef = FirebaseDatabase.getInstance().getReference("basket");
        String key = basketRef.push().getKey();
        if (key != null) {
            basketRef.child(key).setValue(item)
                    .addOnSuccessListener(aVoid -> Toast.makeText(context, "Added to basket: " + item.getName(), Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(context, "Error adding to basket", Toast.LENGTH_SHORT).show());
        }
    }

    private void removeItemFromBasket(ClothingItem item, int position) {
        if (item.getFirebaseKey() != null) {
            DatabaseReference basketRef = FirebaseDatabase.getInstance().getReference("basket");
            basketRef.child(item.getFirebaseKey()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        clothingItems.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Removed from basket: " + item.getName(), Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Failed to remove item", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(context, "Item key is missing", Toast.LENGTH_SHORT).show();
        }
    }
}
