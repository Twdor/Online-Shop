package com.codecool.shop.dao;

import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.SupplierModel;
import com.codecool.shop.model.ProductSubcategoryModel;

import java.util.List;

public interface ProductDao {

    void add(ProductModel product);
    ProductModel find(int id);
    void remove(int id);

    List<ProductModel> getAll();
    List<ProductModel> getBy(SupplierModel supplier);
    List<ProductModel> getBy(ProductSubcategoryModel productSubcategory);

}
