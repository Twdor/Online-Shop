package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductSubcategory extends BaseModel {
    private ProductCategory productCategory;
    private List<Product> products;

    public ProductSubcategory(String name, ProductCategory productCategory) {
        super(name);
        this.products = new ArrayList<>();
        this.productCategory = productCategory;
        this.productCategory.addSubcategory(this);
    }

    public ProductCategory getProductCategory() { return productCategory; }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %1$d,\"name\": \"%2$s\",\"category\": \"%3$s\",\"description\":\"%4$s\"}",
                this.id, this.name, this.productCategory.getName(), this.description);
    }
}