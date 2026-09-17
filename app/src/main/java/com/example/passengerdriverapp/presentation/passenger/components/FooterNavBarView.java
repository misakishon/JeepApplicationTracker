package com.example.passengerdriverapp.presentation.passenger.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.passenger.UserHomePageActivity;
import com.example.passengerdriverapp.presentation.passenger.favorites.FavoritesActivity;
import com.example.passengerdriverapp.presentation.passenger.map.MapActivity;
import com.example.passengerdriverapp.presentation.passenger.profile.ProfileActivity;

public class FooterNavBarView extends LinearLayout {
    public FooterNavBarView(Context context, AttributeSet attrs) {
        this(context, context instanceof FavoritesActivity ? "Favorites"
                : context instanceof ProfileActivity ? "Profile" : "Home");
    }

    public FooterNavBarView(Context context) {
        this(context, "Home");
    }

    public FooterNavBarView(Context context, String selectedLabel) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.WHITE);
        setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(context, 70)));

        addItem(context, R.drawable.ic_home, "Home", "Home".equals(selectedLabel));
        addItem(context, R.drawable.ic_map, "Map", "Map".equals(selectedLabel));
        addItem(context, R.drawable.ic_favorite, "Favorites", "Favorites".equals(selectedLabel));
        addItem(context, R.drawable.ic_person, "Profile", "Profile".equals(selectedLabel));
    }

    private void addItem(Context context, int icon, String label, boolean selected) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));

        ImageView iconView = new ImageView(context);
        iconView.setImageResource(icon);
        iconView.setColorFilter(ContextCompat.getColor(context, selected ? R.color.green : R.color.text_secondary));
        iconView.setScaleType(ImageView.ScaleType.CENTER);
        iconView.setContentDescription(label);

        TextView labelView = new TextView(context);
        labelView.setText(label);
        labelView.setTextSize(11f);
        labelView.setGravity(Gravity.CENTER);
        labelView.setTextColor(ContextCompat.getColor(context, selected ? R.color.green : R.color.text_secondary));

        item.addView(iconView, new LayoutParams(-1, dp(context, 30)));
        item.addView(labelView, new LayoutParams(-1, dp(context, 22)));
        item.setClickable(true);
        item.setFocusable(true);
        item.setOnClickListener(view -> {
            if ("Home".equals(label) && !(context instanceof UserHomePageActivity)) {
                context.startActivity(new Intent(context, UserHomePageActivity.class));
                animateForward(context);
                finishCurrentActivity(context);
            } else if ("Map".equals(label) && !(context instanceof MapActivity)) {
                context.startActivity(new Intent(context, MapActivity.class));
                animateForward(context);
            } else if ("Favorites".equals(label) && !(context instanceof FavoritesActivity)) {
                context.startActivity(new Intent(context, FavoritesActivity.class));
                animateForward(context);
                finishCurrentActivity(context);
            } else if ("Profile".equals(label) && !(context instanceof ProfileActivity)) {
                context.startActivity(new Intent(context, ProfileActivity.class));
                animateForward(context);
                finishCurrentActivity(context);
            }
        });
        addView(item);
    }

    private void finishCurrentActivity(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private void animateForward(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density);
    }
}
