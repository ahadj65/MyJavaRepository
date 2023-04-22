package com.example.demo3forsdk20.model;

public class AdminModel {
    private String username;
    private String password;

    public AdminModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
