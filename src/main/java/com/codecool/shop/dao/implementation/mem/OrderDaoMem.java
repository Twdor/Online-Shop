package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.models.OrderModel;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoMem extends MemManager implements OrderDao {
    private static List<OrderModel> data;
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        setData();
        return instance;
    }

    private static void setData() {
        var dataFromJson = getDataFromJson(new TypeToken<List<OrderModel>>() {},"orders.json");
        data = dataFromJson == null ? new ArrayList<>() : dataFromJson;
    }

    private void saveMemData() {
        try {
            saveDataToJson(data, "orders.json");
        } catch (Exception e){
            throw new RuntimeException("Error while writing to json file: " + "orders.json", e);
        }
    }

    @Override
    public void add(OrderModel orderModel) {
        data.add(orderModel);
        saveMemData();
    }

    @Override
    public List<OrderModel> getAll(String customerId) { return data.stream().filter(c -> c.getCustomerId().equals(customerId)).collect(Collectors.toList());}

}
