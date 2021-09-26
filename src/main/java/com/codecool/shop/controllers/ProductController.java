package com.codecool.shop.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "productServlet", urlPatterns = {"/api/products"})
public class ProductController extends MainController{

    public ProductController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        var subcategoryId = Integer.parseInt(request.getParameter("subcategoryId"));

        out.println(service.getProductsForSubcategory(subcategoryId));
        out.flush();
    }

}
