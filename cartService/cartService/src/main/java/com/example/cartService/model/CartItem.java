package com.example.cartService.model;

public class CartItem {

    private Long id;
    private Long productId;
    private String name;
    private Double price;
    private String sku;
    private String image;
    private Integer quantity;

    public CartItem() {
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public String getImage() {
        return image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
