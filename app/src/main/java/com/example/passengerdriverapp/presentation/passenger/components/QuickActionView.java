package com.example.passengerdriverapp.presentation.passenger.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;

public class QuickActionView extends LinearLayout {
    public QuickActionView(Context context, int icon, String label, int iconColor) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setPadding(dp(8), dp(8), dp(8), dp(8));
        setBackground(round(Color.WHITE, 16));
        setElevation(dp(2));

        ImageView iconView = new ImageView(context);
        iconView.setImageResource(icon);
        iconView.setColorFilter(ContextCompat.getColor(context, iconColor));
        iconView.setScaleType(ImageView.ScaleType.CENTER);
        iconView.setContentDescription(label);

        TextView labelView = new TextView(context);
        labelView.setText(label);
        labelView.setTextSize(11);
        labelView.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        labelView.setTypeface(null, Typeface.BOLD);
        labelView.setGravity(Gravity.CENTER);
        labelView.setPadding(0, dp(4), 0, 0);

        addView(iconView, new LayoutParams(-1, dp(30)));
        addView(labelView, new LayoutParams(-1, dp(24)));
    }

    private GradientDrawable round(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));
        return drawable;
    }

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
