package com.example.passengerdriverapp.presentation.passenger.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;

public class SearchBarView extends LinearLayout {
    public SearchBarView(Context context, String hint) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(dp(context, 16), dp(context, 12), dp(context, 16), dp(context, 12));
        setBackground(createRoundedBackground(context, Color.WHITE));
        setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(context, 52)));

        TextView icon = new TextView(context);
        icon.setText("⌕");
        icon.setTextSize(18f);
        icon.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
        icon.setPadding(0, 0, dp(context, 12), 0);

        EditText input = new EditText(context);
        input.setHint(hint);
        input.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        input.setHintTextColor(ContextCompat.getColor(context, R.color.text_secondary));
        input.setBackground(null);
        input.setPadding(0, 0, 0, 0);
        input.setSingleLine(true);

        addView(icon);
        addView(input);
    }

    private GradientDrawable createRoundedBackground(Context context, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(context, 18));
        return drawable;
    }

    private int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density);
    }
}
