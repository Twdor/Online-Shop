package com.codecool.shop.controllers;

import com.codecool.shop.models.UserModel;
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

@WebServlet(name = "userSignUpServlet", urlPatterns = {"/api/user-signUp"})
public class UserSignUpController extends MainController{

    public UserSignUpController() throws SQLException {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        var badStatus = new JsonObject();

        UserModel customer = new Gson().fromJson(request.getReader(), UserModel.class);
        boolean isEmailAlreadyRegister = service.getUser(customer.getEmail()) != null;

        if (isEmailAlreadyRegister) {
            badStatus.addProperty("emailAlreadyRegister", true);
            out.println(badStatus);
            out.flush();
            return;
        }

        Process process = Runtime.getRuntime().exec(String.format("python3 hash_password.py %s", customer.getPassword()));
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        var hashPassword = in.readLine();
        service.add(new UserModel(customer.getName(), customer.getEmail(), hashPassword));

        out.println(service.getUser(customer.getEmail()));
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
