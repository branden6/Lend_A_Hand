package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListingsActivity extends AppCompatActivity {

    private static List<Listing> listings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        LinearLayout listingsContainer = findViewById(R.id.listingsContainer);
        Button backToMainPageButton = findViewById(R.id.backToMainPageButton);

        // Go back to the main page
        backToMainPageButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Display listings
        for (int i = 0; i < listings.size(); i++) {
            Listing listing = listings.get(i);
            View listingView = createListingView(listing, i);
            listingsContainer.addView(listingView);
        }
    }

    private View createListingView(Listing listing, int index) {
        LinearLayout listingView = new LinearLayout(this);
        listingView.setOrientation(LinearLayout.HORIZONTAL);
        listingView.setPadding(16, 16, 16, 16);

        // Image
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        imageView.setImageResource(listing.getImageResId());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Info Container
        LinearLayout infoContainer = new LinearLayout(this);
        infoContainer.setOrientation(LinearLayout.VERTICAL);
        infoContainer.setPadding(16, 0, 0, 0);
        infoContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        ));

        // Title
        TextView titleView = new TextView(this);
        titleView.setText(listing.getTitle());
        titleView.setTextSize(18);
        titleView.setPadding(0, 0, 0, 8);

        // Price
        TextView priceView = new TextView(this);
        priceView.setText("Price: " + listing.getPrice() + " " + listing.getPriceUnit());

        // Add title and price to info container
        infoContainer.addView(titleView);
        infoContainer.addView(priceView);

        // Button Container
        LinearLayout buttonContainer = new LinearLayout(this);
        buttonContainer.setOrientation(LinearLayout.VERTICAL);
        buttonContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        // Edit Button
        Button editButton = new Button(this);
        editButton.setText("Edit");
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListingsActivity.this, CreateListingActivity.class);
            intent.putExtra("listingIndex", index);
            startActivity(intent);
        });
        editButton.setTextSize(12);
        editButton.setPadding(8, 4, 8, 4);

        // Delete Button
        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(v -> {
            listings.remove(index);
            recreate(); // Refresh the activity
        });
        deleteButton.setTextSize(12);
        deleteButton.setPadding(8, 4, 8, 4);

        // Add buttons to the button container
        buttonContainer.addView(editButton);
        buttonContainer.addView(deleteButton);

        // Add image, info container, and button container to the listing view
        listingView.addView(imageView);
        listingView.addView(infoContainer);
        listingView.addView(buttonContainer);

        return listingView;
    }

    public static void addListing(Listing listing) {
        listings.add(listing);
    }

    public static void updateListing(int index, Listing updatedListing) {
        listings.set(index, updatedListing);
    }

    public static Listing getListing(int index) {
        return listings.get(index);
    }
}
