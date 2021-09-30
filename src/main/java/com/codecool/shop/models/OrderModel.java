package com.codecool.shop.models;

import java.util.List;


public class OrderModel<T> {
    private String orderId;
    private String customerId;
    private String customerType;
    private String orderDate;
    private String orderStatus;
    private String totalPrice;
    private List<T> productList;

    public OrderModel
            (
                    String orderId,
                    String customerId,
                    String customerType,
                    String orderDate,
                    String orderStatus,
                    String totalPrice
                    )
    {
        this.customerId = customerId;
        this.customerType = customerType;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() { return orderStatus; }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }
}
