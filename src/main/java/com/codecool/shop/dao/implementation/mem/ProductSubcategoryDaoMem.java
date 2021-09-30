package com.codecool.shop.dao.implementation.mem;


import com.codecool.shop.dao.ProductSubcategoryDao;
import com.codecool.shop.models.ProductCategoryModel;
import com.codecool.shop.models.ProductSubcategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSubcategoryDaoMem implements ProductSubcategoryDao {

    private List<ProductSubcategoryModel> data = new ArrayList<>();
    private static ProductSubcategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductSubcategoryDaoMem() {
    }

    public static ProductSubcategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductSubcategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductSubcategoryModel subcategory) {
        subcategory.setId(data.size() + 1);
        data.add(subcategory);
    }

    @Override
    public ProductSubcategoryModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductSubcategoryModel> getAll() { return data; }

    @Override
    public List<ProductSubcategoryModel> getAllByProductCategory(ProductCategoryModel productCategory) {
        return data.stream().filter(p -> p.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
