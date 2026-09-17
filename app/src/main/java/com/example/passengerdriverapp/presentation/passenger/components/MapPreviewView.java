package com.example.passengerdriverapp.presentation.passenger.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.data.api.JeepneyData;
import com.example.passengerdriverapp.data.api.JeepneyRoute;
import com.example.passengerdriverapp.data.api.MapBox;

public class MapPreviewView extends FrameLayout {
    public MapPreviewView(Context context, AttributeSet attrs) {
        this(context);
    }

    public MapPreviewView(Context context) {
        super(context);
        setBackground(round(Color.parseColor("#E7F0FC"), 16));
        setClipToOutline(true);

        WebView map = new WebView(context);
        map.setBackgroundColor(Color.TRANSPARENT);
        WebSettings settings = map.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        map.loadDataWithBaseURL(
                "https://api.mapbox.com/",
                buildMapPage(),
                "text/html",
                "UTF-8",
                null);
        addView(map, new LayoutParams(-1, -1));

        TextView fullMap = new TextView(context);
        fullMap.setText("View Full Map");
        fullMap.setTextSize(11);
        fullMap.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
        fullMap.setGravity(Gravity.CENTER);
        fullMap.setBackground(round(Color.WHITE, 14));
        LayoutParams buttonParams = new LayoutParams(dp(92), dp(32), Gravity.END | Gravity.BOTTOM);
        buttonParams.setMargins(0, 0, dp(12), dp(10));
        addView(fullMap, buttonParams);
        fullMap.setOnClickListener(view -> performClick());
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
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

    private String buildMapPage() {
        StringBuilder markers = new StringBuilder();
        for (int index = 0; index < JeepneyData.ROUTES.size(); index++) {
            JeepneyRoute route = JeepneyData.ROUTES.get(index);
            markers.append("new mapboxgl.Marker({element: jeepneyMarker()})")
                    .append(".setLngLat([").append(route.getLng()).append(",").append(route.getLat()).append("]) ")
                    .append(".setPopup(new mapboxgl.Popup({offset: 24}).setHTML('<strong>")
                    .append(escapeHtml(route.getTitle())).append("</strong><br>")
                    .append(escapeHtml(route.getLabel())).append("<br>")
                    .append(escapeHtml(route.getEtaMinutes())).append(" away'))")
                    .append(".addTo(map);\n");
        }

        return "<!doctype html>"
                + "<html><head><meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no'>"
                + "<link href='https://api.mapbox.com/mapbox-gl-js/v3.15.0/mapbox-gl.css' rel='stylesheet'>"
                + "<style>html,body,#map{height:100%;margin:0}body{overflow:hidden}.jeepney-marker{width:34px;height:34px;border-radius:50%;background:#19B6C9;border:2px solid #FFFFFF;box-shadow:0 2px 5px #17202A55;display:flex;align-items:center;justify-content:center}.jeepney-marker svg{width:19px;height:19px}</style>"
                + "</head><body><div id='map'></div>"
                + "<script src='https://api.mapbox.com/mapbox-gl-js/v3.15.0/mapbox-gl.js'></script>"
                + "<script>mapboxgl.accessToken='"
                + escapeJavaScript(MapBox.getAccessToken())
                + "';function jeepneyMarker(){var el=document.createElement('div');el.className='jeepney-marker';el.innerHTML='<svg viewBox=\"0 0 24 24\" aria-hidden=\"true\"><path fill=\"white\" d=\"M4 16c0 .9.4 1.7 1 2.2V20c0 .6.4 1 1 1h1c.6 0 1-.4 1-1v-1h8v1c0 .6.4 1 1 1h1c.6 0 1-.4 1-1v-1.8c.6-.5 1-1.3 1-2.2V6c0-3.5-3.6-4-8-4S4 2.5 4 6v10zm3.5 2c-.8 0-1.5-.7-1.5-1.5S6.7 15 7.5 15s1.5.7 1.5 1.5S8.3 18 7.5 18zm9 0c-.8 0-1.5-.7-1.5-1.5s.7-1.5 1.5-1.5 1.5.7 1.5 1.5-.7 1.5-1.5 1.5zM6 13V6h12v7H6z\"/></svg>';return el;}var map=new mapboxgl.Map({container:'map',style:'mapbox://styles/mapbox/streets-v12',center:[120.565,18.11],zoom:9.8,interactive:true});"
                + "map.addControl(new mapboxgl.NavigationControl({showCompass:false}), 'top-left');"
                + markers
                + "</script></body></html>";
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&#39;");
    }

    private String escapeJavaScript(String value) {
        return value.replace("\\", "\\\\").replace("'", "\\'");
    }
}
