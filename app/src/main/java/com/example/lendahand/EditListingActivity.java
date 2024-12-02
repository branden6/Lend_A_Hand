package com.example.lendahand;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditListingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        EditText priceInput = findViewById(R.id.priceInput);
        EditText availabilityInput = findViewById(R.id.availabilityInput);
        Button saveButton = findViewById(R.id.saveButton);

        Listing listing = (Listing) getIntent().getSerializableExtra("listing");

        if (listing != null) {
            titleInput.setText(listing.getTitle());
            descriptionInput.setText(listing.getDescription());
            priceInput.setText(String.valueOf(listing.getPrice()));
            availabilityInput.setText(listing.getAvailability());
        }

        saveButton.setText("Update");
        saveButton.setOnClickListener(v -> {
            if (listing != null) {
                listing.setTitle(titleInput.getText().toString());
                listing.setDescription(descriptionInput.getText().toString());
                listing.setPrice(Double.parseDouble(priceInput.getText().toString()));
                listing.setAvailability(availabilityInput.getText().toString());

                // Update in database (placeholder for now)
                Toast.makeText(this, "Listing Updated!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
