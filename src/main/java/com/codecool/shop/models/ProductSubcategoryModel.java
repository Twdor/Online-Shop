package com.codecool.shop.models;

import java.util.ArrayList;
import java.util.List;

public class ProductSubcategoryModel extends BaseModel {
    private ProductCategoryModel productCategory;
    private List<ProductModel> products;

    public ProductSubcategoryModel(String name, ProductCategoryModel productCategory) {
        super(name);
        this.products = new ArrayList<>();
        this.productCategory = productCategory;
        this.productCategory.addSubcategory(this);
    }

    public ProductCategoryModel getProductCategory() { return productCategory; }

    public void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return this.products;
    }

    public void addProduct(ProductModel product) {
        this.products.add(product);
    }

    public void addProducts(List<ProductModel> products) { this.products = products; }

    @Override
    public String toString() {
        return String.format("" +
                        "{\"id\": %1$d," +
                        "\"name\": \"%2$s\"," +
                        "\"category\": \"%3$s\"," +
                        "\"description\":\"%4$s\"," +
                        "\"productsNumber\": %5$d}",
                this.id,
                this.name,
                this.productCategory.getName(),
                this.description,
                this.products.size());
    }
}