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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button setLocation;
    boolean getCheckedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_location);

        getCheckedButton = getIntent().getBooleanExtra("getCheckedButton", false);

        setLocation = findViewById(R.id.buttonSetLocation);
        setLocation.setOnClickListener(v -> {
            Intent intent = new Intent(CustomLocationActivity.this, SearchFilterActivity.class);
            intent.putExtra("getCheckedButton", getCheckedButton);
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

        LatLng kelowna = new LatLng(49.8801, -119.4436);
        googleMap.addMarker(new MarkerOptions().position(kelowna).title("Kelowna"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kelowna, 14));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

}