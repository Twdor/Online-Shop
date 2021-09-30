package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.models.ShoppingCartModel;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShoppingCartDaoMem extends MemManager implements ShoppingCartDao {
    private static List<ShoppingCartModel> data;
    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        setData();
        return instance;
    }

    private static void setData() {
        var dataFromJson = getDataFromJson(new TypeToken<List<ShoppingCartModel>>() {},"shoppingCarts.json");
        data = dataFromJson == null ? new ArrayList<>() : dataFromJson;
    }

    private void saveMemData() {
        try {
            saveDataToJson(data, "shoppingCarts.json");
        } catch (Exception ignored){}
    }

    @Override
    public void add(ShoppingCartModel shoppingCartModel) {
        shoppingCartModel.setId(data.size() + 1);
        data.add(shoppingCartModel);
        saveMemData();
    }

    @Override
    public void remove(int id) {
        if (!data.isEmpty()) {
            data.remove(data.stream().filter(cart -> Objects.equals(cart.getId(), id)).findFirst().orElse(null));
            saveMemData();
        }
    }

    @Override
    public void update(ShoppingCartModel shoppingCart) {
        data.get(data.indexOf(find(shoppingCart.getCustomerId(), shoppingCart.getProductId()))).setQuantity(shoppingCart.getQuantity());
        saveMemData();
    }

    @Override
    public ShoppingCartModel find(String customerId, int productId) {
        return data.stream().filter(cart -> cart.getCustomerId().equals(customerId) && cart.getProductId() == productId).findFirst().orElse(null);
    }

    @Override
    public List<ShoppingCartModel> getAll(String customerId) {
        return data.stream().filter(cart -> cart.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
