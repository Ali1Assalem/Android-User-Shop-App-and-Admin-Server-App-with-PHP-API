package com.example.usershopapp.Model;

public class Token {
    public String email,token,isServerToken;

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

    public String getIsServerToken() {
        return isServerToken;
    }

    public void setIsServerToken(String isServerToken) {
        this.isServerToken = isServerToken;
    }
}
