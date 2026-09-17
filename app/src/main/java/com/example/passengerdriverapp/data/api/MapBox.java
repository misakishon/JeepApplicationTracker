package com.example.passengerdriverapp.data.api;

import com.example.passengerdriverapp.BuildConfig;

public class MapBox {
    private MapBox() {
    }

    public static String getAccessToken() {
        return BuildConfig.MAPBOX_ACCESS_TOKEN;
    }
}
