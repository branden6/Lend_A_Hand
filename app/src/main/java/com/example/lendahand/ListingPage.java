package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ListingPage extends AppCompatActivity {
    Button contactRenter;
    Button viewRenterRatings;
    Button x;
    Button checkAvailability;
    ImageButton openMap;
    ImageView listerProfile;

    double dayPrice;
    double hourPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listing_page);

        dayPrice = 35;
        hourPrice = 10;

        checkAvailability = findViewById(R.id.checkAvailability);
        contactRenter = findViewById(R.id.contactRenter);
        viewRenterRatings = findViewById(R.id.viewRenterRatings);
        openMap = findViewById(R.id.openMap);
        x = findViewById(R.id.x2);
        listerProfile = findViewById(R.id.listerProfile);

        listerProfile.setImageResource(R.drawable.user);

        x.setOnClickListener(v -> {
           finish();
        });

        checkAvailability.setOnClickListener(v -> {
            // Handle the click event for checkAvailability button
            Intent intent = new Intent(ListingPage.this, SelectDates.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("dayPrice", dayPrice);
            bundle.putDouble("hourPrice", hourPrice);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        contactRenter.setOnClickListener(v -> {
            // Handle the click event for contactRenter button
            Toast.makeText(this, "Contacting Renter", Toast.LENGTH_SHORT).show();
        });

        viewRenterRatings.setOnClickListener(v -> {
            // Handle the click event for viewRenterRatings button
            Toast.makeText(this, "Viewing Renter Ratings", Toast.LENGTH_SHORT).show();
        });

        openMap.setOnClickListener(v -> {
            // Handle the click event for openMap button
            Toast.makeText(this, "Opening Map", Toast.LENGTH_SHORT).show();
        });




    }
}