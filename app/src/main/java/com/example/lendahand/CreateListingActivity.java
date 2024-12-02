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

import java.util.UUID;


public class CreateListingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        EditText priceInput = findViewById(R.id.priceInput);
        EditText availabilityInput = findViewById(R.id.availabilityInput);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());
            String availability = availabilityInput.getText().toString();

            Listing newListing = new Listing(
                    UUID.randomUUID().toString(),
                    title,
                    description,
                    price,
                    availability,
                    "" // Placeholder for imageUri
            );

            // Save to database (placeholder for now)
            Toast.makeText(this, "Listing Created!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
