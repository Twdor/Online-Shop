package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCartModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private final List<ShoppingCartModel> data = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    public ShoppingCartModel getShoppingCartByCustomerId(String customerId) {
        return data.stream().filter(cart -> Objects.equals(cart.getCustomerId(), customerId)).findFirst().orElse(null);
    }


    @Override
    public void add(ShoppingCartModel shoppingCartModel) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(ShoppingCartModel shoppingCartModel) {

    }

    @Override
    public ShoppingCartModel find(String customerId, int productId) {
        return null;
    }

    @Override
    public List<ShoppingCartModel> getAll(String guestId) {
        return null;
    }
}
