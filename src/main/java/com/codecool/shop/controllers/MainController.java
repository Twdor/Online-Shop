package com.codecool.shop.controllers;

import com.codecool.shop.service.Service;
import com.codecool.shop.config.TemplateEngineUtil;
import com.google.gson.JsonParser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class MainController extends HttpServlet {
    Service service = new Service(true);

    public MainController() throws SQLException {}

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Object countryAndStates = new JsonParser().parse(new FileReader("country-states.json"));

        PrintWriter out = response.getWriter();
        out.println(countryAndStates);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        var userIdCookie = readServletCookie(req, "userId");
        var guestIdCookie = readServletCookie(req, "guestId");
        if (userIdCookie != null) {
            context.setVariable("customerData", service.getUser(Integer.parseInt(userIdCookie)));
            context.setVariable("shoppingCartSize", service.getShoppingCartSize(userIdCookie));
        } else if (guestIdCookie != null) {
            context.setVariable("shoppingCartSize", service.getShoppingCartSize(guestIdCookie));
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
