package com.example.passengerdriverapp.data.model;

public class Route {
    public String id;
    public String routeName;
    public String destination;
    public String time;
    public String status;

    public Route(String id, String routeName, String destination, String time, String status) {
        this.id = id;
        this.routeName = routeName;
        this.destination = destination;
        this.time = time;
        this.status = status;
    }
}
