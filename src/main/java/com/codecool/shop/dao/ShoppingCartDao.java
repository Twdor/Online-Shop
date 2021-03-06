package com.codecool.shop.dao;

import com.codecool.shop.models.ShoppingCartModel;

import java.util.List;

public interface ShoppingCartDao {
    void add(ShoppingCartModel shoppingCartModel);
    void remove(int id);
    void update(ShoppingCartModel shoppingCartModel);

    ShoppingCartModel find(String customerId, int productId);
    List<ShoppingCartModel> getAll(String customerId);
}
