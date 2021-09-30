package com.codecool.shop.controllers;

import com.codecool.shop.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;


@WebServlet(name = "userServlet", urlPatterns = {"/api/user"})
public class UserController extends MainController{

    public UserController() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        UserModel user = new Gson().fromJson(request.getReader(), UserModel.class);
        var status = new JsonObject();

        service.update(user);

        status.addProperty("status", "success");
        out.println(status);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        var result = new JsonObject();
//        result.addProperty("status", sendCustomerEmail());
        result.addProperty("status", "true");

        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
    }

    private boolean sendCustomerEmail() {

        // email ID of Recipient.
        String recipient = "adrianghitescu0713@gmail.com";
        // email ID of  Sender.
        String sender = "b_elenacristina@yahoo.com";
        // using host as localhost
        String host = "0.0.0.0";
//        String port = "8080";
//        host = host.trim();
//        0.0.0.0:8080
        // Getting system properties
        Properties properties = System.getProperties();
        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.smtp.port", port);
        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);
        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));
            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            // Set Subject: subject of the email
            message.setSubject("This is Subject");
            // set body of the email.
            message.setText("This is a test mail");
            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
            return true;
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
            return false;
        }
    }
}
