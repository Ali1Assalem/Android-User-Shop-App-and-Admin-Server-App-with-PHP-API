package com.example.adminshopserver.Model;

public class Token  {
    public String email;
    public String token;
    public int isServerToken;

    public Token() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIsServerToken() {
        return isServerToken;
    }

    public void setIsServerToken(int isServerToken) {
        this.isServerToken = isServerToken;
    }
}

