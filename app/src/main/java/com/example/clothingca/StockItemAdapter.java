package com.example.clothingca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockItemAdapter extends RecyclerView.Adapter<StockItemAdapter.ViewHolder> {

    private Context context;
    private List<StockItem> stockItems;

    public StockItemAdapter(Context context, List<StockItem> stockItems) {
        this.context = context;
        this.stockItems = stockItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockItem item = stockItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvManufacturer.setText(item.getManufacturer());
        holder.tvPrice.setText(String.format("$%.2f", item.getPrice()));
        holder.tvCategory.setText(item.getCategory());
    }

    @Override
    public int getItemCount() {
        return stockItems.size();
    }

    // Method to update the dataset
    public void setStockItems(List<StockItem> newStockItems) {
        this.stockItems = newStockItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvManufacturer, tvPrice, tvCategory;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvManufacturer = itemView.findViewById(R.id.tvManufacturer);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
