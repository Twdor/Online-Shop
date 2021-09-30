package com.codecool.shop.controllers;

import com.codecool.shop.models.ShoppingCartModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "shoppingCartServlet", urlPatterns = {"/api/shoppingCart"})
public class ShoppingCartController extends MainController {

    public ShoppingCartController() throws SQLException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        var status = new JsonObject();
        ShoppingCartModel shoppingCartModel = new Gson().fromJson(request.getReader(), ShoppingCartModel.class);

        int pastQuantity = getPastQuantity(shoppingCartModel);

        switch (shoppingCartModel.getOption()) {
            case "add to guest":
                var guestCookie = readServletCookie(request, "guestId");
                String guestId;
                if (guestCookie != null){
                    guestId = guestCookie;
                } else {
                    guestId = String.valueOf(UUID.randomUUID());
                    createCookie(response, guestId);
                }
                shoppingCartModel.setCustomerId(guestId);

                pastQuantity = getPastQuantity(shoppingCartModel);
                if (pastQuantity == 0) {
                    service.add(shoppingCartModel);
                } else {
                    shoppingCartModel.setQuantity(pastQuantity + shoppingCartModel.getQuantity());
                    service.update(shoppingCartModel);
                }
                break;
            case "add to user":
                if (pastQuantity == 0) {
                    service.add(shoppingCartModel);
                } else {
                    shoppingCartModel.setQuantity(pastQuantity + shoppingCartModel.getQuantity());
                    service.update(shoppingCartModel);
                }
                break;
            case "decrease":
                shoppingCartModel.setQuantity(pastQuantity - shoppingCartModel.getQuantity());
                service.update(shoppingCartModel);
                break;
            case "remove":
                service.deleteShoppingCart(shoppingCartModel.getId());
                break;
        }

        status.addProperty("status", "true");

        out.println(status);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var idParameter = request.getParameter("id");

        PrintWriter out = response.getWriter();
        var shoppingCart = service.getCustomerShoppingCart(idParameter);
        out.println(shoppingCart);
        out.flush();
    }

    private int getPastQuantity(ShoppingCartModel shoppingCartModel) {
        int pastQuantity;
        try {
            pastQuantity = service.getShoppingCartBy(shoppingCartModel.getCustomerId(), shoppingCartModel.getProductId()).getQuantity();
        } catch (Exception e) {
            pastQuantity = 0;
        }
        return pastQuantity;
    }

    private void createCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie("guestId", value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
