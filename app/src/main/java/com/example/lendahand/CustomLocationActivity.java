package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button setLocation;
    private GoogleMap mMap;
    private Marker marker; //marker for custom location
    private Circle circle; //circle for custom radius
    private double initRadius = 1000;
    boolean getCheckedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_location);

        getCheckedButton = getIntent().getBooleanExtra("getCheckedButton", false);

        setLocation = findViewById(R.id.buttonSetLocation);
        setLocation.setOnClickListener(v -> {
            Intent intent = new Intent(CustomLocationActivity.this, SearchFilterActivity.class);

            if (marker != null){
                LatLng position = marker.getPosition();
                intent.putExtra("latitude", position.latitude);
                intent.putExtra("longitude", position.longitude);
            }
            intent.putExtra("getCheckedButton", true);
            startActivity(intent);
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        if (marker != null)
            marker.remove();
        if (circle != null)
            circle.remove();

        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);


        LatLng kelowna = new LatLng(49.8801, -119.4436);
        LatLng location;

        if (latitude == 0.0 && longitude == 0.0)
            location = kelowna;
        else
            location = new LatLng(latitude, longitude);


        marker = googleMap.addMarker(new MarkerOptions().position(location).title("Selected Location"));
        circle = drawCircle(location, 1000);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener( latLng -> { //Allowing user to click and select a location
            if (marker != null)
                marker.remove();
            if (circle != null)
                circle.remove();

            marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
            circle = drawCircle(latLng, initRadius);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        });

        mMap.setOnCameraIdleListener(() -> {
            if (circle != null){
            float zoomLevel = mMap.getCameraPosition().zoom;
            double newRadius = newRadius(zoomLevel);
            circle.setRadius(newRadius);
            }

        });

    }
    private double newRadius(float zoomLevel){
        double scale = Math.pow(2, 14-zoomLevel);
        return initRadius * scale;
    }
    private Circle drawCircle( LatLng center, double radius) {
        return mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(radius)
                .strokeWidth(5));
    }

}