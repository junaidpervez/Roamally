package com.example.roamally;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.titleText.setText(product.getTitle());
        holder.descriptionText.setText(product.getDescription());
        holder.priceText.setText(product.getPrice());

        Glide.with(context).load(product.getImageUrl()).into(holder.imageView);

        // Set favorite icon color based on favorite status
        holder.favoriteIcon.setColorFilter(product.isFavorite() ? Color.RED : Color.GRAY);

        // Set up the favoriteIcon to toggle color on click
        holder.favoriteIcon.setOnClickListener(v -> {
            if (holder.favoriteIcon.getTag() == null || !(Boolean) holder.favoriteIcon.getTag()) {
                // If not set or not favorited, set color to red and mark as favorite
                holder.favoriteIcon.setColorFilter(Color.RED);
                holder.favoriteIcon.setTag(true);
            } else {
                // If already favorited, reset to default color
                holder.favoriteIcon.setColorFilter(Color.GRAY);
                holder.favoriteIcon.setTag(false);
            }
        });

        // Toggle favorite status on click
        holder.favoriteIcon.setOnClickListener(v -> {
            product.setFavorite(!product.isFavorite()); // Toggle favorite status
            notifyItemChanged(position); // Refresh the item to update UI
        });

        holder.updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, InsertProductActivity.class);
            intent.putExtra("productId", product.getProductId());
            intent.putExtra("title", product.getTitle());
            intent.putExtra("description", product.getDescription());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("imageUrl", product.getImageUrl());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
            databaseReference.child(product.getProductId()).removeValue().addOnSuccessListener(aVoid -> {
                productList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show();
            });
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, descriptionText, priceText;
        ImageView imageView, favoriteIcon; // Add favoriteIcon ImageView
        Button updateButton, deleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            priceText = itemView.findViewById(R.id.priceText);
            imageView = itemView.findViewById(R.id.imageView);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon); // Initialize favorite icon
        }
    }
}
