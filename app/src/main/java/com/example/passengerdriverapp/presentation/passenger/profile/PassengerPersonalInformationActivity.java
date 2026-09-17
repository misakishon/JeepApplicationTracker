package com.example.passengerdriverapp.presentation.passenger.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;

public class PassengerPersonalInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_personal_information);

        TextView name = findViewById(R.id.personalInfoName);
        TextView email = findViewById(R.id.personalInfoEmail);
        TextView mobile = findViewById(R.id.personalInfoMobile);
        TextView address = findViewById(R.id.personalInfoAddress);
        TextView gender = findViewById(R.id.personalInfoGender);
        TextView dob = findViewById(R.id.personalInfoDob);

        name.setText("Maria Lopez");
        email.setText("maria.lopez@gmail.com");
        mobile.setText("+63 912 345 6789");
        address.setText("123 Bonifacio Street, Cebu City");
        gender.setText("Female");
        dob.setText("April 14, 1995");
    }
}
