package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategoryModel;

import java.util.List;

public interface ProductCategoryDao {
    void add(ProductCategoryModel category);
    ProductCategoryModel find(int id);
    void remove(int id);

    List<ProductCategoryModel> getAll();
}
