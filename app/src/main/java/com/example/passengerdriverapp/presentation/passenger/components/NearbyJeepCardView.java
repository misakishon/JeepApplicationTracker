package com.example.passengerdriverapp.presentation.passenger.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.data.model.Route;

public class NearbyJeepCardView extends LinearLayout {
    public NearbyJeepCardView(Context context, Route route) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(context, 16), dp(context, 16), dp(context, 16), dp(context, 16));
        setBackground(createRoundedBackground(context, Color.WHITE));
        setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        int marginTop = dp(context, 10);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = marginTop;
        setLayoutParams(params);

        ImageView marker = new ImageView(context);
        marker.setImageResource(R.drawable.ic_directions_bus);
        marker.setColorFilter(getStatusColor(context, route.status));
        marker.setBackground(createRoundedBackground(context, getStatusBackgroundColor(context, route.status)));
        marker.setPadding(dp(context, 10), dp(context, 10), dp(context, 10), dp(context, 10));
        marker.setContentDescription(route.routeName + " vehicle");

        LinearLayout textContainer = new LinearLayout(context);
        textContainer.setOrientation(VERTICAL);

        TextView title = new TextView(context);
        title.setText(route.routeName + "     " + route.time);
        title.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        title.setTextSize(16f);
        title.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView destination = new TextView(context);
        destination.setText(route.destination);
        destination.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
        destination.setTextSize(13f);
        destination.setPadding(0, dp(context, 4), 0, 0);

        textContainer.addView(title);
        textContainer.addView(destination);

        addView(marker, new LayoutParams(dp(context, 46), dp(context, 46)));
        addView(textContainer);
    }

    private int getStatusColor(Context context, String status) {
        if ("In Progress".equals(status))
            return ContextCompat.getColor(context, R.color.green);
        if ("Upcoming".equals(status))
            return ContextCompat.getColor(context, R.color.blue);
        return ContextCompat.getColor(context, R.color.yellow);
    }

    private int getStatusBackgroundColor(Context context, String status) {
        if ("In Progress".equals(status))
            return Color.argb(28, 10, 168, 107);
        if ("Upcoming".equals(status))
            return Color.argb(28, 45, 127, 249);
        return Color.argb(35, 246, 184, 0);
    }

    private GradientDrawable createRoundedBackground(Context context, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(context, 14));
        return drawable;
    }

    private int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density);
    }
}
