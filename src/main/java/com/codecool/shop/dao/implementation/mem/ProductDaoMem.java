package com.codecool.shop.dao.implementation.mem;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.models.ProductModel;
import com.codecool.shop.models.ProductSubcategoryModel;
import com.codecool.shop.models.SupplierModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<ProductModel> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductModel product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public ProductModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductModel> getAll() {
        return data;
    }

    @Override
    public List<ProductModel> getBy(SupplierModel supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> getBy(ProductSubcategoryModel productSubcategory) {
        return data.stream().filter(t -> t.getProductSubcategory().equals(productSubcategory)).collect(Collectors.toList());
    }
}
