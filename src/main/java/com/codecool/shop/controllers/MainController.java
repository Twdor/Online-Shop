package com.codecool.shop.controllers;

import com.codecool.shop.model.CustomerModel;
import com.codecool.shop.service.Service;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
//    ProductDao productDataStore = ProductDaoMem.getInstance();
//    ProductSubcategoryDao productSubcategoryDataStore = ProductSubcategoryDaoMem.getInstance();
//    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//    ProductService productService = new ProductService(productDataStore,productSubcategoryDataStore,productCategoryDataStore);

    Service service = new Service(true);
//    ProductService productService = new ProductService();

    public ProductController() throws SQLException {}
//    ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", service.getAllCategories());

        context.setVariable("subcategory", service.getProductSubcategory(1));
//        context.setVariable("products", productService.getProductsForSubcategory(46));
        context.setVariable("products", service.getAllProducts());

        engine.process("product/index.html", context, resp.getWriter());
    }
}
