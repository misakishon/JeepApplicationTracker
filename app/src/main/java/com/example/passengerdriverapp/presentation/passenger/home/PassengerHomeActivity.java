package com.example.passengerdriverapp.presentation.passenger.home;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.data.model.Route;
import com.example.passengerdriverapp.data.repository.AppRepository;
import com.example.passengerdriverapp.presentation.passenger.components.FooterNavBarView;
import com.example.passengerdriverapp.presentation.passenger.components.HeaderView;
import com.example.passengerdriverapp.presentation.passenger.components.NearbyJeepCardView;
import com.example.passengerdriverapp.presentation.passenger.components.SearchBarView;

import java.util.List;

public class PassengerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView root = new ScrollView(this);
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(dp(20), dp(20), dp(20), dp(20));
        container.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        HeaderView header = new HeaderView(this, "Good morning!", "Where are you going?");
        SearchBarView searchBar = new SearchBarView(this, "Search route or destination");

        LinearLayout titleRow = new LinearLayout(this);
        titleRow.setOrientation(LinearLayout.HORIZONTAL);
        titleRow.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        titleRow.setPadding(0, dp(22), 0, 0);

        com.example.passengerdriverapp.presentation.passenger.components.HeaderView sectionTitle = new com.example.passengerdriverapp.presentation.passenger.components.HeaderView(
                this, "Nearby Jeepneys", "");

        titleRow.addView(sectionTitle);

        container.addView(header);
        container.addView(searchBar);
        container.addView(titleRow);

        AppRepository repository = new AppRepository();
        List<Route> routes = repository.getRoutes();
        for (Route route : routes) {
            container.addView(new NearbyJeepCardView(this, route));
        }

        root.addView(container);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        page.addView(root);
        page.addView(new FooterNavBarView(this));

        setContentView(page);
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
