package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MemManager {

    protected void saveJson(List<UserModel> model, String jasonFile) throws IOException {
        Writer writer = new FileWriter(jasonFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(model, writer);
        writer.flush();
        writer.close();
    }

    protected void saveJson(UserModel model, String jasonFile) throws IOException {
        Writer writer = new FileWriter(jasonFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(model, writer);
        writer.flush();
        writer.close();
    }

    protected static List<UserModel> getCustomers() {
        List<UserModel> customers = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("customers.json"));
            customers = new Gson().fromJson(reader, new TypeToken<List<UserModel>>() {}.getType());
            reader.close();
        } catch (Exception ignored) {}
        return customers;
    }
}
