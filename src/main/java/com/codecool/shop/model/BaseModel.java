package com.codecool.shop.model;


import java.lang.reflect.Field;

public class BaseModel {

    protected int id;
    protected String name;
    protected String description;

    public BaseModel(String name) {
        this.name = name;
    }

    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("{");
//        for (Field field : this.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            Object value;
//            try {
//                value = field.get(this);
//                if (value != null) {
////                    sb.append("\"").append(field.getName()).append("\"").append(":").append("\"").append(value).append("\"").append(",");
//                    sb.append(String.format("\"%s\": \"%s\"", field.getName(), value));
////                    sb.append(field.getName()).append(":").append(value).append(",");
//                }
//            } catch (IllegalAccessException ignored) {}
//        }
//        sb.append("}");
//        return sb.toString();
//    }
//            return String.format("{\"id\": %1$d,\"name\": \"%2$s\",\"department\": \"%3$s\",\"description\":\"%4$s\"}",
//                    this.id, this.name, this.department, this.description);

}
