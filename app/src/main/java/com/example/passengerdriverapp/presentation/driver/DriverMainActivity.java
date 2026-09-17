package com.example.passengerdriverapp.presentation.driver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.driver.history.DriverHistoryFragment;
import com.example.passengerdriverapp.presentation.driver.home.DriverHomeFragment;
import com.example.passengerdriverapp.presentation.driver.profile.DriverProfileFragment;
import com.example.passengerdriverapp.presentation.driver.trips.DriverTripsFragment;

public class DriverMainActivity extends AppCompatActivity {
    private int activeColor;
    private int inactiveColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        activeColor = ContextCompat.getColor(this, R.color.green);
        inactiveColor = ContextCompat.getColor(this, R.color.text_secondary);
        setNavigationListeners();
        showScreen(new DriverHomeFragment(), R.id.navDriverHome);
    }

    private void setNavigationListeners() {
        findViewById(R.id.navDriverHome)
                .setOnClickListener(view -> showScreen(new DriverHomeFragment(), R.id.navDriverHome));
        findViewById(R.id.navDriverTrips)
                .setOnClickListener(view -> showScreen(new DriverTripsFragment(), R.id.navDriverTrips));
        findViewById(R.id.navDriverHistory)
                .setOnClickListener(view -> showScreen(new DriverHistoryFragment(), R.id.navDriverHistory));
        findViewById(R.id.navDriverProfile)
                .setOnClickListener(view -> showScreen(new DriverProfileFragment(), R.id.navDriverProfile));
    }

    private void showScreen(Fragment fragment, int selectedId) {
        getSupportFragmentManager().beginTransaction().replace(R.id.driverContainer, fragment).commit();
        int[] navigationIds = { R.id.navDriverHome, R.id.navDriverTrips, R.id.navDriverHistory, R.id.navDriverProfile };
        for (int navigationId : navigationIds) {
            View item = findViewById(navigationId);
            boolean selected = navigationId == selectedId;
            ImageView icon = (ImageView) ((android.widget.LinearLayout) item).getChildAt(0);
            TextView label = (TextView) ((android.widget.LinearLayout) item).getChildAt(1);
            icon.setColorFilter(selected ? activeColor : inactiveColor);
            label.setTextColor(selected ? activeColor : inactiveColor);
            label.setTypeface(null, selected ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
    }
}
