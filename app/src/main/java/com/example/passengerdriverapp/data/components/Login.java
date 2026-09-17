package com.example.passengerdriverapp.data.components;

public class Login {
    public static final String DEMO_DRIVER_EMAIL = "driver@demo.com";
    public static final String DEMO_DRIVER_PASSWORD = "driver123";
    public static final String DEMO_PASSENGER_EMAIL = "passenger@demo.com";
    public static final String DEMO_PASSENGER_PASSWORD = "passenger123";

    private final String email;
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDriverAccount() {
        return DEMO_DRIVER_EMAIL.equalsIgnoreCase(email) && DEMO_DRIVER_PASSWORD.equals(password);
    }

    public boolean isPassengerAccount() {
        return DEMO_PASSENGER_EMAIL.equalsIgnoreCase(email) && DEMO_PASSENGER_PASSWORD.equals(password);
    }

    public boolean isValid() {
        return isDriverAccount() || isPassengerAccount() ||
                (email != null && email.contains("@") && password != null && password.length() >= 6);
    }
}
