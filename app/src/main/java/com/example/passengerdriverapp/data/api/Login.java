package com.example.passengerdriverapp.data.api;

public class Login {
    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
