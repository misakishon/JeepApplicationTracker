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

public class RecommendationCardView extends LinearLayout {
    public RecommendationCardView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(14), dp(10), dp(14), dp(10));
        setBackground(round(Color.WHITE, 16));

        LinearLayout copy = new LinearLayout(context);
        copy.setOrientation(VERTICAL);
        copy.addView(text(context, "Route 02 - Quiapo → Cubao", 13, R.color.text_primary, true));
        copy.addView(text(context, "5 min away  ·  Est. trip 32 min", 11, R.color.text_secondary, false));
        addView(copy, new LayoutParams(0, -2, 1));

        ImageView heart = new ImageView(context);
        heart.setImageResource(R.drawable.ic_favorite);
        heart.setColorFilter(ContextCompat.getColor(context, R.color.red));
        heart.setScaleType(ImageView.ScaleType.CENTER);
        heart.setContentDescription("Add route to favorites");
        addView(heart, new LayoutParams(dp(40), dp(48)));
    }

    private TextView text(Context context, String value, int size, int color, boolean bold) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(ContextCompat.getColor(context, color));
        view.setTypeface(null, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
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
