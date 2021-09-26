package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.SupplierModel;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<SupplierModel> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(SupplierModel supplier) {
        supplier.setId(data.size() + 1);
        data.add(supplier);
    }

    @Override
    public SupplierModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<SupplierModel> getAll() {
        return data;
    }
}
