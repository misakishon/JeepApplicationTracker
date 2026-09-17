package com.example.passengerdriverapp.data.repository;

import com.example.passengerdriverapp.data.model.Route;
import com.example.passengerdriverapp.data.model.User;
import com.example.passengerdriverapp.data.model.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class AppRepository {

    public User getPassenger() {
        return new User("Maria Lopez", "maria.lopez@gmail.com", "Passenger");
    }

    public User getDriver() {
        return new User("Juan Dela Cruz", "juan.driver@gmail.com", "Driver");
    }

    public Vehicle getAssignedVehicle() {
        return new Vehicle("PAU-1024", "PUJ - Public Utility Jeepney", "Ready");
    }

    public List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route("Route 01", "Route 01", "Bacarra → 5th Fairview", "3 min away", "In Progress"));
        routes.add(new Route("Route 02", "Route 02", "Quiaipo → Cubao", "9 min away", "Upcoming"));
        routes.add(new Route("Route 04", "Route 04", "Cubao → Baclaran", "6 min away", "Upcoming"));
        return routes;
    }
}
