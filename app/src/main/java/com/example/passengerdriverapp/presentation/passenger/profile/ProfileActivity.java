package com.example.passengerdriverapp.presentation.passenger.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.auth.LoginActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);

        LinearLayout personalInfoRow = findViewById(R.id.passengerPersonalInfoRow);
        if (personalInfoRow != null) {
            personalInfoRow.setOnClickListener(view -> {
                startActivity(new Intent(this, PassengerPersonalInformationActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }

        LinearLayout logoutButton = findViewById(R.id.passengerLogoutButton);
        if (logoutButton != null) {
            logoutButton.setOnClickListener(view -> {
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                LoginActivity.logoutAndGoToLogin(this);
            });
        }
    }
}
