package com.example.passengerdriverapp.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.data.api.MapBox;
import com.example.passengerdriverapp.presentation.auth.LoginActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String accessToken = MapBox.getAccessToken();
        if (accessToken.isEmpty()) {
            throw new IllegalStateException("MAPBOX_ACCESS_TOKEN is missing from .env");
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}