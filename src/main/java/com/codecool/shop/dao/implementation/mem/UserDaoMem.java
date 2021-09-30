package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.models.UserModel;
import com.google.gson.reflect.TypeToken;

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
        var dataFromJson = getDataFromJson(new TypeToken<List<UserModel>>() {},"customers.json");
        data = dataFromJson == null ? new ArrayList<>() : dataFromJson;
    }

    @Override
    public void add(UserModel customer) {
        customer.setId(data.size() + 1);
        data.add(customer);
        try {
            saveDataToJson(data, "customers.json");
        } catch (Exception e){
            throw new RuntimeException("Error while writing to json file: " + "customers.json", e);
        }
    }

    @Override
    public void remove(UserModel customer) {
        data.remove(customer);
    }

    @Override
    public void update(UserModel user) {
        var userData = data.get(data.indexOf(find(user.getId())));
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPhoneNumber(user.getPhoneNumber());
        userData.setCountry(user.getCountry());
        userData.setState(user.getState());
        userData.setCity(user.getCity());
        userData.setAddress(user.getAddress());
        userData.setZipcode(user.getZipcode());
        try {
            saveDataToJson(data, "customers.json");
        } catch (Exception e){
            throw new RuntimeException("Error while writing to json file: " + "shoppingCarts.json", e);
        }
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
    public List<UserModel> getAll() { return data; }
}
