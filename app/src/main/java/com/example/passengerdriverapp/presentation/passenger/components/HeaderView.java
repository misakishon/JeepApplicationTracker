package com.example.passengerdriverapp.presentation.passenger.components;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;

public class HeaderView extends LinearLayout {
    public HeaderView(Context context, String title, String subtitle) {
        super(context);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(24f);
        titleView.setTypeface(null, android.graphics.Typeface.BOLD);
        titleView.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        titleView.setGravity(Gravity.START);

        TextView subtitleView = new TextView(context);
        subtitleView.setText(subtitle);
        subtitleView.setTextSize(14f);
        subtitleView.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
        subtitleView.setGravity(Gravity.START);
        subtitleView.setPadding(0, 4, 0, 0);

        addView(titleView);
        addView(subtitleView);
    }
}
