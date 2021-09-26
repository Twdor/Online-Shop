package com.codecool.shop.controllers;

import com.codecool.shop.service.Service;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class MainController extends HttpServlet {
    Service service = new Service(true);

    public MainController() throws SQLException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        var userIdCookie = readServletCookie(req, "userId");
        var guestIdCookie = readServletCookie(req, "guestId");
        //            int guestId = Integer.parseInt(guestIdCookie.get());
        if (userIdCookie != null) {
//            int customerId = Integer.parseInt(userIdCookie.get());
            context.setVariable("customerData", service.getUser(Integer.parseInt(userIdCookie)));
            context.setVariable("shoppingCart", service.getCustomerShoppingCart(userIdCookie));
        } else if (guestIdCookie != null) {
            context.setVariable("shoppingCart", service.getCustomerShoppingCart(guestIdCookie));
            context.setVariable("guestId", guestIdCookie);
        }

        context.setVariable("categories", service.getAllCategories());

        context.setVariable("subcategory", service.getProductSubcategory(46));
        context.setVariable("products", service.getProductsForSubcategory(46));

        engine.process("product/index.html", context, resp.getWriter());
    }

    protected String readServletCookie(HttpServletRequest request, String name){
        try {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> name.equals(cookie.getName()))
                    .map(Cookie::getValue).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

}
