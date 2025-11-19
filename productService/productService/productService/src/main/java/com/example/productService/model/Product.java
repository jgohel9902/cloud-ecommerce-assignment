package com.example.productService.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String sku;
    private Boolean isActive;
    private String image;

    // Default constructor
    public Product() {
    }

    // Constructor with all fields
    public Product(Long id, String name, String description, Double price, String sku, Boolean isActive, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.isActive = isActive;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
