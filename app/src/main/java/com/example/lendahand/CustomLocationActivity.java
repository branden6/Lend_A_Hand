package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CustomLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng kelowna = new LatLng(49.8801, -119.4436);
        mMap.addMarker(new MarkerOptions().position(kelowna).title("Kelowna"));

        LatLng UBCO = new LatLng(49.9394, -119.3948);
        mMap.addMarker(new MarkerOptions().position(UBCO).title("UBCO"));

        LatLng LakeCountry = new LatLng(50.0537, -119.4106);
        mMap.addMarker(new MarkerOptions().position(LakeCountry).title("Lake Country"));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kelowna, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        }

}