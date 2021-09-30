package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.models.ProductCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {
    private List<ProductCategoryModel> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategoryModel category) {
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategoryModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategoryModel> getAll() {
        return data;
    }
}
