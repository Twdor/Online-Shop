package com.codecool.shop.dao;

import com.codecool.shop.models.SupplierModel;

import java.util.List;

public interface SupplierDao {

    void add(SupplierModel supplier);
    SupplierModel find(int id);
    void remove(int id);

    List<SupplierModel> getAll();
}
