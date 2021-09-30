package com.codecool.shop.dao;

import com.codecool.shop.models.OrderModel;

import java.util.List;

public interface OrderDao {
    void add(OrderModel orderModel);
    List<OrderModel> getAll(String customerId);
}
