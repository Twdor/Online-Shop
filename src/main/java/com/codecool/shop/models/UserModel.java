package com.codecool.shop.model;

public class UserModel extends BaseModel{
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String address;

    public UserModel(String name, String email, String password) {
        super(name);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return String.format("" +
                        "{\"id\": %1$d," +
                        "\"name\": \"%2$s\"," +
                        "\"email\": \"%3$s\"," +
                        "\"phoneNumber\":\"%4$s\"," +
                        "\"country\":\"%5$s\"," +
                        "\"city\":\"%6$s\"," +
                        "\"zipcode\":\"%7$s\"," +
                        "\"address\":\"%8$s\"," +
                        "\"state\":\"%9$s\"}",
                this.id,
                this.name,
                this.email,
                this.phoneNumber,
                this.country,
                this.city,
                this.zipcode,
                this.address,
                this.state);
    }
}
