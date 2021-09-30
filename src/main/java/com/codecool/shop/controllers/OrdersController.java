package com.codecool.shop.controllers;

import com.codecool.shop.models.OrderModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "orderServlet", urlPatterns = {"/api/order"})
public class OrdersController extends MainController{

    public OrdersController() throws SQLException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        var status = new JsonObject();
        OrderModel orderModel = new Gson().fromJson(request.getReader(), OrderModel.class);
        List<List<String>> productList = new ArrayList<>();
        var shoppingCartModels = service.getCustomerShoppingCart(orderModel.getCustomerId());
        for (JsonObject shoppingCartModel : shoppingCartModels) {
            List<String> product = new ArrayList<>();
            product.add(shoppingCartModel.get("productName").getAsString());
            product.add(shoppingCartModel.get("productPrice").getAsString());
            product.add("QTY: "+shoppingCartModel.get("quantity").getAsString());
            productList.add(product);
        }
        orderModel.setProductList(productList);

        service.add(orderModel);

        status.addProperty("status", "true");

        out.println(status);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var idParameter = request.getParameter("userId");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        var orders = gson.toJson(service.getAllOrdersBy(idParameter));
        out.println(orders);
        out.flush();
    }
}
