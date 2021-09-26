package com.codecool.shop.model;


public class ShoppingCartModel extends BaseModel{
    private ProductModel product;
    private int quantity;
    private String customerId;
    private int productId;
    private String option;

    public ShoppingCartModel(ProductModel product, String customerId, int quantity) {
        super("client "+customerId+" shoppingCart");
        this.product = product;
        this.customerId = customerId;
        this.quantity = quantity;
    }


    public ShoppingCartModel(int quantity) {
        super("tempShoppingCart");
        this.quantity = quantity;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
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


    @Override
    public String toString() {
        return String.format("{\"id\": %1$d,\"name\": \"%2$s\",\"defaultPrice\": %3$f,\"price\":\"%4$s\",\"quantity\": %5$d,\"defaultCurrency\":\"%6$s\", \"productId\": %7$d}",
                this.id, product.getName(), product.getDefaultPrice(), product.getPrice(), quantity, product.getDefaultCurrency(), product.getId());
    }

}
