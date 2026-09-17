package com.example.passengerdriverapp.presentation.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.data.components.RegisterAccount;
import com.example.passengerdriverapp.presentation.driver.DriverMainActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner accountType = findViewById(R.id.accountType);
        accountType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[] { "Select account type", "Passenger", "Driver" }));
        findViewById(R.id.registerButton).setOnClickListener(view -> register());
        findViewById(R.id.signInLink).setOnClickListener(view -> finish());
    }

    private void register() {
        RegisterAccount account = new RegisterAccount(
                value(R.id.registerName), value(R.id.registerEmail), value(R.id.registerPhone),
                ((Spinner) findViewById(R.id.accountType)).getSelectedItem().toString(), value(R.id.registerPassword));
        boolean terms = ((android.widget.CheckBox) findViewById(R.id.termsCheck)).isChecked();
        if (!account.isValid(value(R.id.registerConfirmPassword), terms)) {
            Toast.makeText(this, "Complete the form, accept the terms, and use matching passwords", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        startActivity(new Intent(this, DriverMainActivity.class));
        finishAffinity();
    }

    private String value(int id) {
        return ((EditText) findViewById(id)).getText().toString().trim();
    }
}
