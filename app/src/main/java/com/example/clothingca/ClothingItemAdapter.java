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
    private boolean isCheckout; // Flag to determine the context of usage

    public ClothingItemAdapter(Context context, List<ClothingItem> clothingItems, boolean isCheckout) {
        this.context = context;
        this.clothingItems = clothingItems;
        this.isCheckout = isCheckout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isCheckout ? R.layout.activity_checkout : R.layout.activity_clothing_item;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(view, isCheckout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothingItem item = clothingItems.get(position);
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(String.format("$%.2f", item.getPrice()));

        if (!isCheckout) {
            holder.actionButton.setText("Add to Basket");
            holder.actionButton.setOnClickListener(v -> {
                addToBasket(item);
                Toast.makeText(context, "Added to basket: " + item.getName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            holder.actionButton.setText("Remove");
            holder.actionButton.setOnClickListener(v -> {
                removeItemFromBasket(item, position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return clothingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        Button actionButton;

        public ViewHolder(View itemView, boolean isCheckout) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            actionButton = itemView.findViewById(isCheckout ? R.id.removeButton : R.id.addToBasketButton);
        }
    }

    private void addToBasket(ClothingItem item) {
        DatabaseReference basketRef = FirebaseDatabase.getInstance().getReference("basket");
        String key = basketRef.push().getKey();
        basketRef.child(key).setValue(item);
    }

    private void removeItemFromBasket(ClothingItem item, int position) {
        DatabaseReference basketRef = FirebaseDatabase.getInstance().getReference("basket");
        basketRef.child(item.getId()).removeValue(); // Assuming item ID is the Firebase key
        clothingItems.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Removed from basket: " + item.getName(), Toast.LENGTH_SHORT).show();
    }
}
