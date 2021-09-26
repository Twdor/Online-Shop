package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;


public class SupplierModel extends BaseModel {
    private List<ProductModel> products;

    public SupplierModel(String name, String description){
        super(name, description);
        this.products = new ArrayList<>();
    }

    public void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return this.products;
    }

    public void addProduct(ProductModel product) {
        this.products.add(product);
    }
}