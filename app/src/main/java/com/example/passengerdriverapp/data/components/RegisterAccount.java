package com.example.passengerdriverapp.data.components;

public class RegisterAccount {
    public final String fullName;
    public final String email;
    public final String phone;
    public final String accountType;
    public final String password;

    public RegisterAccount(String fullName, String email, String phone, String accountType, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.accountType = accountType;
        this.password = password;
    }

    public boolean isValid(String confirmation, boolean acceptedTerms) {
        return fullName != null && !fullName.trim().isEmpty()
                && email != null && email.contains("@")
                && phone != null && !phone.trim().isEmpty()
                && password != null && password.length() >= 6
                && password.equals(confirmation) && acceptedTerms;
    }
}
