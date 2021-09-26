package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoMem extends MemManager implements UserDao {
    private static List<UserModel> data;
    private static UserDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        setData();
        return instance;
    }

    private static void setData() {
        data = getCustomers() == null ? new ArrayList<>() : getCustomers();
    }

    @Override
    public void add(UserModel customer) {
        setData();
        customer.setId(data.size() + 1);
        data.add(customer);
        try {
            saveJson(data, "customers.json");
        } catch (Exception e){
//            throw new RuntimeException("Error while writing to json file: " + String.format("customer-%s.json", customer.getId()), e);
        }
    }

    @Override
    public void remove(UserModel customer) {
        data.remove(customer);
    }

    @Override
    public void update(UserModel customer) {

    }

    @Override
    public UserModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public UserModel find(String email) {
        return data.stream().filter(t -> Objects.equals(t.getEmail(), email)).findFirst().orElse(null);
    }

    @Override
    public List<UserModel> getAll() {
        setData();
//        List<CustomerModel> customerModels = data.stream().map((Function<BaseModel, Object>) BaseModel::toString).collect(Collectors.toList());
        return data;
    }
}
