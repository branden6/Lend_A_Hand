package com.example.lendahand;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteListingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_listing);

        Button confirmDeleteButton = findViewById(R.id.confirmDeleteButton);

        confirmDeleteButton.setOnClickListener(v -> {
            String listingId = getIntent().getStringExtra("listingId");

            // Remove from database (placeholder for now)
            Toast.makeText(this, "Listing Deleted!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
