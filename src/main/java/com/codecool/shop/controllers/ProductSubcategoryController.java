package com.codecool.shop.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "productSubcategoryServlet", urlPatterns = {"/api/productSubcategory"})
public class ProductSubcategoryController extends MainController {

    public ProductSubcategoryController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var idParameter = request.getParameter("id");
        var id = Integer.parseInt(idParameter);

        PrintWriter out = response.getWriter();

        out.println(service.getProductSubcategoryByCategoryId(service.getProductCategoryById(id)));
        out.flush();
    }
}
