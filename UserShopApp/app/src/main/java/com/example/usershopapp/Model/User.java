package com.example.usershopapp.Model;

public class User {
    private String email;
    private String name;
    private String address;
    private String error_msg;
    private String avatarUrl;
    public User() {
    }

    public User(String email, String name, String address, String error_msg, String avatarUrl) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.error_msg = error_msg;
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}


