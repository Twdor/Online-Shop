package com.codecool.shop.model;


public class ShoppingCartModel{
    private int id;
    private int quantity;
    private String customerId;
    private int productId;
    private String option;

    public ShoppingCartModel(int productId, String customerId, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public ShoppingCartModel(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getQuantity() { return quantity; }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
