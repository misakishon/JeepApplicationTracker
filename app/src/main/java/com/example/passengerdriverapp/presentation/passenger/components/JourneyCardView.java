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

public class JourneyCardView extends LinearLayout {
    public JourneyCardView(Context context, String title, String subtitle, String eta, String detail, int accent,
            int icon) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(14), dp(10), dp(14), dp(10));
        setBackground(round(Color.WHITE, 16));
        setElevation(dp(2));

        ImageView routeIcon = new ImageView(context);
        routeIcon.setImageResource(icon);
        routeIcon.setColorFilter(ContextCompat.getColor(context, accent));
        routeIcon.setScaleType(ImageView.ScaleType.CENTER);
        routeIcon.setContentDescription(title);
        routeIcon.setBackground(round(tint(accent), 13));
        addView(routeIcon, new LayoutParams(dp(48), dp(48)));

        LinearLayout copy = new LinearLayout(context);
        copy.setOrientation(VERTICAL);
        copy.setPadding(dp(12), 0, dp(6), 0);
        TextView titleView = text(context, title, 16, R.color.text_primary, true);
        TextView subtitleView = text(context, subtitle, 13, R.color.text_secondary, false);
        copy.addView(titleView);
        copy.addView(subtitleView);
        addView(copy, new LayoutParams(0, -2, 1));

        LinearLayout status = new LinearLayout(context);
        status.setOrientation(VERTICAL);
        status.setGravity(Gravity.END);
        TextView etaView = text(context, eta, 15, accent, true);
        TextView detailView = text(context, detail, 11, R.color.text_secondary, false);
        status.addView(etaView);
        status.addView(detailView);
        addView(status);
    }

    private TextView text(Context context, String value, int size, int color, boolean bold) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(ContextCompat.getColor(context, color));
        view.setTypeface(null, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
    }

    private int tint(int colorRes) {
        int color = ContextCompat.getColor(getContext(), colorRes);
        return Color.argb(28, Color.red(color), Color.green(color), Color.blue(color));
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
