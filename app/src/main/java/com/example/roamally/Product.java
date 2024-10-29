package com.example.roamally;

public class Product {
    private String productId;
    private String title;
    private String description;
    private String price;
    private String imageUrl;
    private boolean isFavorite; // New field for favorite status

    public Product() {
    }

    public Product(String productId, String title, String description, String price, String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isFavorite = false; // Initialize to false
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
