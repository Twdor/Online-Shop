package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.model.ProductSubcategoryModel;

import java.util.List;

public interface ProductSubcategoryDao {

    void add(ProductSubcategoryModel category);
    ProductSubcategoryModel find(int id);
    void remove(int id);

    List<ProductSubcategoryModel> getAll();
    List<ProductSubcategoryModel> getAllByProductCategory(ProductCategoryModel productCategory);

}
