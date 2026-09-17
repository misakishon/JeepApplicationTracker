package com.example.passengerdriverapp.data.model;

public class Vehicle {
    public String plateNumber;
    public String model;
    public String status;

    public Vehicle(String plateNumber, String model, String status) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.status = status;
    }
}
