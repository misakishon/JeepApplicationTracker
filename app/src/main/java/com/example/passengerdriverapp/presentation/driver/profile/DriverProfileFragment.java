package com.example.passengerdriverapp.presentation.driver.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.auth.LoginActivity;

public class DriverProfileFragment extends Fragment {
    public DriverProfileFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_driver_profile, container, false);

        LinearLayout personalInfoRow = root.findViewById(R.id.driverPersonalInfoRow);
        if (personalInfoRow != null) {
            personalInfoRow.setOnClickListener(view -> {
                startActivity(new android.content.Intent(requireContext(), DriverPersonalInformationActivity.class));
            });
        }

        LinearLayout logoutButton = root.findViewById(R.id.driverLogoutButton);
        if (logoutButton != null) {
            logoutButton.setOnClickListener(view -> {
                Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show();
                LoginActivity.logoutAndGoToLogin(requireActivity());
            });
        }

        return root;
    }
}
