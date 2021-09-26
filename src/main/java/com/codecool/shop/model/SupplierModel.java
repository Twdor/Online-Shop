package com.codecool.shop.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Supplier extends BaseModel {
    private List<Product> products;

    public Supplier(String name, String description){
        super(name, description);
        this.products = new ArrayList<>();
//        saveJson();
    }

//    private void saveJson() throws IOException {
//        Writer writer = new FileWriter("adi.json");
//        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
//        gson.toJson(this, writer);
//        writer.flush();
//        writer.close();
//    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

//    @Override
//    public String toString() {
//        return String.format("id: %1$d, " +
//                        "name: %2$s, " +
//                        "description: %3$s",
//                this.id,
//                this.name,
//                this.description
//        );
//    }
}