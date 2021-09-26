package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryModel extends BaseModel{
    private String department;
    private List<ProductSubcategoryModel> subcategories;

    public ProductCategoryModel(String name, String department) {
    super(name);
    this.department = department;
    this.subcategories = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSubcategories(ArrayList<ProductSubcategoryModel> subcategories) {
        this.subcategories = subcategories;
    }

    public List<ProductSubcategoryModel> getSubcategories() {
        return this.subcategories;
    }

    public void addSubcategory(ProductSubcategoryModel productSubcategory) {
        this.subcategories.add(productSubcategory);
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %1$d,\"name\": \"%2$s\",\"department\": \"%3$s\",\"description\":\"%4$s\"}",
                this.id, this.name, this.department, this.description);
    }
}
