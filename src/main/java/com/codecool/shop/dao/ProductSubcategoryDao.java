package com.codecool.shop.dao;

import com.codecool.shop.models.ProductCategoryModel;
import com.codecool.shop.models.ProductSubcategoryModel;

import java.util.List;

public interface ProductSubcategoryDao {

    void add(ProductSubcategoryModel category);
    ProductSubcategoryModel find(int id);
    void remove(int id);

    List<ProductSubcategoryModel> getAll();
    List<ProductSubcategoryModel> getAllByProductCategory(ProductCategoryModel productCategory);

}
