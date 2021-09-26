package com.codecool.shop.dao;

import com.codecool.shop.model.UserModel;

import java.util.List;

public interface UserDao {
    void add(UserModel customer);
    void remove(UserModel customer);
    void update(UserModel customer);

    UserModel find(int id);
    UserModel find(String email);
    List<UserModel> getAll();
}
