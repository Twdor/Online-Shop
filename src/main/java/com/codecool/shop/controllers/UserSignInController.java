package com.codecool.shop.controllers;

import com.codecool.shop.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "customerSignInServlet", urlPatterns = {"/api/customer-signIn"})
public class UserSignInController extends MainController {

    public UserSignInController() throws SQLException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        UserModel tempCustomer = new Gson().fromJson(request.getReader(), UserModel.class);
        var badStatus = new JsonObject();
        badStatus.addProperty("invalidCredentials", true);

        UserModel customerModel = service.getUser(tempCustomer.getEmail());

        if (customerModel == null) {
            out.println(badStatus);
            out.flush();
            return;
        }

        Process process = Runtime.getRuntime().exec(String.format("python3 is_valid_password.py %s %s", tempCustomer.getPassword(), customerModel.getPassword()));
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var isValidPassword = Boolean.parseBoolean(in.readLine());

        if (!isValidPassword) {
            out.println(badStatus);
            out.flush();
            return;
        }

        out.println(customerModel);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        var customerId = Integer.parseInt(request.getParameter("id"));
        UserModel customerModel = service.getUser(customerId);

        out.println(customerModel);
        out.flush();
    }
}
