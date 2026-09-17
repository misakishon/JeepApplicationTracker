package com.example.passengerdriverapp.data.api;

public class JeepneyRoute {
    private final String id;
    private final String title;
    private final String label;
    private final String from;
    private final String etaMinutes;
    private final double lat;
    private final double lng;

    public JeepneyRoute(String id, String title, String label, String from, String etaMinutes, double lat, double lng) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.from = from;
        this.etaMinutes = etaMinutes;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public String getFrom() {
        return from;
    }

    public String getEtaMinutes() {
        return etaMinutes;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
