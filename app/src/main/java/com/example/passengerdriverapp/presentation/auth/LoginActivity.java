package com.example.passengerdriverapp.presentation.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.data.components.Login;
import com.example.passengerdriverapp.presentation.driver.DriverMainActivity;
import com.example.passengerdriverapp.presentation.passenger.UserHomePageActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.signInButton).setOnClickListener(view -> signIn());
        findViewById(R.id.createAccountLink)
                .setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));

        EditText email = findViewById(R.id.loginEmail);
        EditText password = findViewById(R.id.loginPassword);

        email.setText(Login.DEMO_DRIVER_EMAIL);
        password.setText(Login.DEMO_DRIVER_PASSWORD);
    }

    private void signIn() {
        EditText email = findViewById(R.id.loginEmail);
        EditText password = findViewById(R.id.loginPassword);
        Login login = new Login(email.getText().toString().trim(), password.getText().toString());

        if (!login.isValid()) {
            Toast.makeText(this, "Use a valid demo account or enter a valid email and 6+ character password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (login.isDriverAccount()) {
            startActivity(new Intent(this, DriverMainActivity.class));
        } else if (login.isPassengerAccount()) {
            startActivity(new Intent(this, UserHomePageActivity.class));
        } else {
            startActivity(new Intent(this, UserHomePageActivity.class));
        }

        finish();
    }

    public static void logoutAndGoToLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }
}
