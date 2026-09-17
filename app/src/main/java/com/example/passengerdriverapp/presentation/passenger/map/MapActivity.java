package com.example.passengerdriverapp.presentation.passenger.map;

import android.annotation.SuppressLint;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.passengerdriverapp.data.api.JeepneyData;
import com.example.passengerdriverapp.data.api.JeepneyRoute;
import com.example.passengerdriverapp.data.api.MapBox;
import com.example.passengerdriverapp.R;
import com.example.passengerdriverapp.presentation.passenger.components.FooterNavBarView;

public class MapActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST = 1001;
    private WebView mapView;
    private LocationManager locationManager;
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showCurrentLocation(location);
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(ContextCompat.getColor(this, R.color.background));

        FrameLayout mapShell = new FrameLayout(this);
        page.addView(mapShell, new LinearLayout.LayoutParams(-1, 0, 1));

        mapView = new WebView(this);
        mapView.setBackgroundColor(Color.WHITE);
        WebSettings settings = mapView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        mapView.loadDataWithBaseURL(
                "https://api.mapbox.com/",
                buildMapPage(),
                "text/html",
                "UTF-8",
                null);
        mapShell.addView(mapView, new FrameLayout.LayoutParams(-1, -1));
        addSearchBar(mapShell);
        addMapControls(mapShell);
        addLocationButton(mapShell);
        addNearbySheet(mapShell);
        requestLocationUpdates();

        page.addView(new FooterNavBarView(this, "Map"));
        setContentView(page);
    }

    private void addSearchBar(FrameLayout parent) {
        LinearLayout search = new LinearLayout(this);
        search.setGravity(Gravity.CENTER_VERTICAL);
        search.setPadding(dp(16), 0, dp(12), 0);
        search.setBackground(round(Color.WHITE, 28));
        search.setElevation(dp(5));

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_search);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.text_secondary));
        icon.setContentDescription("Search routes");
        search.addView(icon, new LinearLayout.LayoutParams(dp(34), -1));

        EditText input = new EditText(this);
        input.setHint("Search route or destination");
        input.setTextSize(16);
        input.setSingleLine(true);
        input.setTextColor(ContextCompat.getColor(this, R.color.text_primary));
        input.setHintTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        input.setBackgroundColor(Color.TRANSPARENT);
        input.setPadding(0, 0, 0, 0);
        search.addView(input, new LinearLayout.LayoutParams(0, -1, 1));

        ImageView filter = new ImageView(this);
        filter.setImageResource(R.drawable.ic_tune);
        filter.setScaleType(ImageView.ScaleType.CENTER);
        filter.setContentDescription("Filter routes");
        search.addView(filter, new LinearLayout.LayoutParams(dp(38), -1));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, dp(58), Gravity.TOP);
        params.setMargins(dp(16), dp(18), dp(16), 0);
        parent.addView(search, params);
    }

    private void addMapControls(FrameLayout parent) {
        ImageButton layers = floatingButton(R.drawable.ic_layers, "Map layers");
        FrameLayout.LayoutParams layersParams = new FrameLayout.LayoutParams(dp(52), dp(52),
                Gravity.END | Gravity.CENTER_VERTICAL);
        layersParams.setMargins(0, 0, dp(18), dp(270));
        parent.addView(layers, layersParams);
    }

    private void addLocationButton(FrameLayout parent) {
        ImageButton location = new ImageButton(this);
        location.setImageResource(R.drawable.ic_my_location);
        location.setBackground(round(Color.WHITE, 50));
        location.setElevation(dp(5));
        location.setContentDescription("Show my location");
        location.setOnClickListener(view -> requestLocationUpdates());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dp(52), dp(52),
                Gravity.END | Gravity.CENTER_VERTICAL);
        params.setMargins(0, 0, dp(18), dp(204));
        parent.addView(location, params);
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION }, LOCATION_PERMISSION_REQUEST);
            return;
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider == null) {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        locationManager.requestLocationUpdates(provider, 2000L, 5f, locationListener, getMainLooper());
        Location lastLocation = locationManager.getLastKnownLocation(provider);
        if (lastLocation != null) {
            showCurrentLocation(lastLocation);
        }
    }

    private void showCurrentLocation(Location location) {
        if (mapView == null) {
            return;
        }
        String script = "setCurrentLocation(" + location.getLongitude() + "," + location.getLatitude() + ");";
        mapView.evaluateJavascript(script, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates();
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
        super.onDestroy();
    }

    private void addNearbySheet(FrameLayout parent) {
        LinearLayout sheet = new LinearLayout(this);
        sheet.setOrientation(LinearLayout.VERTICAL);
        sheet.setPadding(dp(18), dp(10), dp(18), dp(12));
        sheet.setBackground(round(Color.WHITE, 22));
        sheet.setElevation(dp(8));

        View handle = new View(this);
        handle.setBackground(round(Color.parseColor("#D8DCE3"), 8));
        handle.setContentDescription("Collapse or expand nearby jeepneys");
        LinearLayout.LayoutParams handleParams = new LinearLayout.LayoutParams(dp(42), dp(5));
        handleParams.gravity = Gravity.CENTER_HORIZONTAL;
        sheet.addView(handle, handleParams);

        LinearLayout titleRow = new LinearLayout(this);
        titleRow.setPadding(0, dp(12), 0, dp(6));
        titleRow.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = text("Nearby Jeepneys", 15, R.color.text_primary, true);
        titleRow.addView(title, new LinearLayout.LayoutParams(0, -2, 1));
        TextView count = text("● LIVE", 11, R.color.green, true);
        titleRow.addView(count);
        sheet.addView(titleRow);

        LinearLayout details = new LinearLayout(this);
        details.setOrientation(LinearLayout.VERTICAL);
        for (int index = 0; index < 3; index++) {
            JeepneyRoute route = JeepneyData.ROUTES.get(index);
            details.addView(routeRow(route), new LinearLayout.LayoutParams(-1, dp(72)));
        }

        LinearLayout track = new LinearLayout(this);
        track.setOrientation(LinearLayout.HORIZONTAL);
        track.setGravity(Gravity.CENTER);
        track.setBackground(round(Color.parseColor("#A8ACB8"), 22));
        track.setClickable(true);
        track.setFocusable(true);

        ImageView trackIcon = new ImageView(this);
        trackIcon.setImageResource(R.drawable.ic_route);
        trackIcon.setContentDescription("Track route");
        track.addView(trackIcon, new LinearLayout.LayoutParams(dp(22), dp(22)));

        TextView trackLabel = text("Track Selected Route", 15, R.color.text_primary, true);
        trackLabel.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(-2, -2);
        labelParams.setMargins(dp(8), 0, 0, 0);
        track.addView(trackLabel, labelParams);

        LinearLayout.LayoutParams trackParams = new LinearLayout.LayoutParams(-1, dp(58));
        trackParams.setMargins(0, dp(12), 0, 0);
        details.addView(track, trackParams);
        sheet.addView(details);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, dp(364), Gravity.BOTTOM);
        params.setMargins(dp(8), 0, dp(8), dp(8));
        parent.addView(sheet, params);

        handle.setOnClickListener(view -> {
            boolean expanded = details.getVisibility() == View.VISIBLE;
            details.setVisibility(expanded ? View.GONE : View.VISIBLE);
            ViewGroup.LayoutParams currentParams = sheet.getLayoutParams();
            currentParams.height = dp(expanded ? 78 : 364);
            sheet.setLayoutParams(currentParams);
            handle.setContentDescription(expanded ? "Expand nearby jeepneys" : "Collapse nearby jeepneys");
        });
    }

    private LinearLayout routeRow(JeepneyRoute route) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(12), 0, dp(12), 0);
        row.setBackground(round(Color.WHITE, 18));
        row.setClickable(true);
        row.setFocusable(true);
        row.setContentDescription("Open " + route.getTitle());
        row.setOnClickListener(view -> focusTerminal(route));

        ImageView bus = new ImageView(this);
        bus.setImageResource(R.drawable.ic_directions_bus);
        bus.setColorFilter(ContextCompat.getColor(this, R.color.text_secondary));
        bus.setBackground(round(Color.parseColor("#F1F3F7"), 14));
        bus.setPadding(dp(12), dp(12), dp(12), dp(12));
        row.addView(bus, new LinearLayout.LayoutParams(dp(52), dp(52)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setPadding(dp(12), 0, 0, 0);
        copy.addView(text(route.getTitle(), 15, R.color.text_primary, true));
        copy.addView(text(route.getFrom(), 12, R.color.text_secondary, false));
        row.addView(copy, new LinearLayout.LayoutParams(0, -2, 1));

        LinearLayout eta = new LinearLayout(this);
        eta.setGravity(Gravity.END);
        eta.setOrientation(LinearLayout.VERTICAL);
        eta.addView(text(route.getEtaMinutes(), 16, R.color.blue, true));
        TextView label = text("ETA", 11, R.color.text_secondary, false);
        label.setGravity(Gravity.END);
        eta.addView(label);
        row.addView(eta);
        return row;
    }

    private void focusTerminal(JeepneyRoute route) {
        if (mapView == null) {
            return;
        }
        String script = "focusTerminal(" + route.getLng() + "," + route.getLat() + ",'"
                + escapeJavaScript(route.getTitle()) + "','" + escapeJavaScript(route.getFrom()) + "','"
                + escapeJavaScript(route.getEtaMinutes()) + "');";
        mapView.evaluateJavascript(script, null);
    }

    private ImageButton floatingButton(int icon, String description) {
        ImageButton button = new ImageButton(this);
        button.setImageResource(icon);
        button.setBackground(round(Color.WHITE, 50));
        button.setElevation(dp(5));
        button.setContentDescription(description);
        return button;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(ContextCompat.getColor(this, color));
        view.setTypeface(null, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
    }

    private android.graphics.drawable.GradientDrawable round(int color, float radius) {
        android.graphics.drawable.GradientDrawable drawable = new android.graphics.drawable.GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));
        return drawable;
    }

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }

    private String buildMapPage() {
        StringBuilder markers = new StringBuilder();
        for (JeepneyRoute route : JeepneyData.ROUTES) {
            markers.append("new mapboxgl.Marker({element: jeepneyMarker()})")
                    .append(".setLngLat([")
                    .append(route.getLng())
                    .append(",")
                    .append(route.getLat())
                    .append("])")
                    .append(".setPopup(new mapboxgl.Popup().setHTML('<strong>")
                    .append(escapeHtml(route.getTitle()))
                    .append("</strong><br>")
                    .append(escapeHtml(route.getFrom()))
                    .append("<br>")
                    .append(escapeHtml(route.getEtaMinutes()))
                    .append("'))")
                    .append(".addTo(map);\n");
        }

        return "<!doctype html>"
                + "<html><head><meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no'>"
                + "<link href='https://api.mapbox.com/mapbox-gl-js/v3.15.0/mapbox-gl.css' rel='stylesheet'>"
                + "<style>html,body,#map{height:100%;margin:0}.jeepney-marker{width:42px;height:42px;border-radius:50%;background:#19B6C9;border:3px solid #FFFFFF;box-shadow:0 2px 6px #17202A55;display:flex;align-items:center;justify-content:center}.jeepney-marker svg{width:24px;height:24px}.current-location{width:22px;height:22px;border-radius:50%;background:#398BE5;border:4px solid #FFFFFF;box-shadow:0 0 0 7px #398BE533,0 2px 5px #17202A55}</style>"
                + "</head><body><div id='map'></div>"
                + "<script src='https://api.mapbox.com/mapbox-gl-js/v3.15.0/mapbox-gl.js'></script>"
                + "<script>mapboxgl.accessToken='"
                + escapeJavaScript(MapBox.getAccessToken())
                + "';var currentMarker=null;function setCurrentLocation(lng,lat){if(currentMarker){currentMarker.setLngLat([lng,lat]);}else{var el=document.createElement('div');el.className='current-location';currentMarker=new mapboxgl.Marker({element:el}).setLngLat([lng,lat]).addTo(map);}map.flyTo({center:[lng,lat],zoom:13.5,duration:700});}function focusTerminal(lng,lat,title,from,eta){map.flyTo({center:[lng,lat],zoom:14.5,duration:700});new mapboxgl.Popup({offset:24}).setLngLat([lng,lat]).setHTML('<strong>'+title+'</strong><br>'+from+'<br>'+eta+' away').addTo(map);}function jeepneyMarker(){var el=document.createElement('div');el.className='jeepney-marker';el.innerHTML='<svg viewBox=\"0 0 24 24\" aria-hidden=\"true\"><path fill=\"white\" d=\"M4 16c0 .9.4 1.7 1 2.2V20c0 .6.4 1 1 1h1c.6 0 1-.4 1-1v-1h8v1c0 .6.4 1 1 1h1c.6 0 1-.4 1-1v-1.8c.6-.5 1-1.3 1-2.2V6c0-3.5-3.6-4-8-4S4 2.5 4 6v10zm3.5 2c-.8 0-1.5-.7-1.5-1.5S6.7 15 7.5 15s1.5.7 1.5 1.5S8.3 18 7.5 18zm9 0c-.8 0-1.5-.7-1.5-1.5s.7-1.5 1.5-1.5 1.5.7 1.5 1.5-.7 1.5-1.5 1.5zM6 13V6h12v7H6z\"/></svg>';return el;}var map=new mapboxgl.Map({container:'map',style:'mapbox://styles/mapbox/streets-v12',center:[120.57,18.12],zoom:10.4});"
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
