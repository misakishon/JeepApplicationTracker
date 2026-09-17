package com.example.passengerdriverapp.presentation.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.passenger.components.MapPreviewView;
import com.example.passengerdriverapp.presentation.passenger.map.MapActivity;

public class UserHomePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        View homeRoot = findViewById(R.id.homeRoot);
        ViewCompat.setOnApplyWindowInsetsListener(homeRoot, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(view.getPaddingLeft(), systemBars.top, view.getPaddingRight(), view.getPaddingBottom());
            return insets;
        });
        ViewCompat.requestApplyInsets(homeRoot);

        View viewMap = findViewById(R.id.viewMapText);
        if (viewMap != null) {
            viewMap.setOnClickListener(view -> openMap());
        }

        View mapPreview = findViewById(R.id.mapPreview);
        if (mapPreview != null) {
            mapPreview.setOnClickListener(view -> openMap());
        }
    }

    private void openMap() {
        startActivity(new Intent(this, MapActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
