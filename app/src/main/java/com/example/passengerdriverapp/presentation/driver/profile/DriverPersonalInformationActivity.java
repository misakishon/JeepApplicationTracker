package com.example.passengerdriverapp.presentation.driver.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;

public class DriverPersonalInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_personal_information);

        TextView name = findViewById(R.id.driverInfoName);
        TextView email = findViewById(R.id.driverInfoEmail);
        TextView mobile = findViewById(R.id.driverInfoMobile);
        TextView address = findViewById(R.id.driverInfoAddress);
        TextView license = findViewById(R.id.driverInfoLicense);
        TextView plate = findViewById(R.id.driverInfoPlate);

        name.setText("Juan Dela Cruz");
        email.setText("juan.driver@gmail.com");
        mobile.setText("+63 917 765 4321");
        address.setText("456 Rizal Avenue, Davao City");
        license.setText("N06-2023-00452");
        plate.setText("ABC 1234");
    }
}
