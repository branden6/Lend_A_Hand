package com.example.lendahand;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WriteReviewActivity extends AppCompatActivity {

    private static final String TAG = "WriteReviewActivity";

    private EditText hostReviewEditText, toolReviewEditText;
    private RatingBar hostRatingBar, toolRatingBar;
    private Button publishButton, skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        // Find all views in the layout
        initializeViews();

        // Check if views are linked correctly
        if (publishButton == null || skipButton == null) {
            Log.e(TAG, "Buttons not found in layout. Check activity_write_review.xml for missing IDs.");
            return;
        }

        // Publish Button: Save review data and navigate to your friend's MainActivity
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hostReview = hostReviewEditText.getText().toString().trim();
                String toolReview = toolReviewEditText.getText().toString().trim();
                float hostRating = hostRatingBar.getRating();
                float toolRating = toolRatingBar.getRating();

                // Debugging: Log the entered data
                Log.d(TAG, "Host Review: " + hostReview);
                Log.d(TAG, "Tool Review: " + toolReview);
                Log.d(TAG, "Host Rating: " + hostRating);
                Log.d(TAG, "Tool Rating: " + toolRating);

                Toast.makeText(WriteReviewActivity.this, "Review Published!", Toast.LENGTH_SHORT).show();

                // Redirect to friend's MainActivity
                redirectToFriendsMain();
            }
        });

        // Skip Button: Navigate to your friend's MainActivity without saving data
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Skip button clicked. Navigating to friend's MainActivity.");
                redirectToFriendsMain();
            }
        });
    }

    /**
     * Redirects the user to the friend's MainActivity
     */
    private void redirectToFriendsMain() {
        try {
            // Create an explicit intent to navigate to your friend's MainActivity
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                    "com.example.lendahand", // Friend's package name
                    "com.example.lendahand.MainActivity" // Fully qualified class name
            ));
            startActivity(intent);
            finish(); // Close WriteReviewActivity
        } catch (Exception e) {
            Log.e(TAG, "Error redirecting to friend's MainActivity", e);
            Toast.makeText(this, "Failed to redirect to the main screen.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to find all views in the layout and initialize them
     */
    private void initializeViews() {
        hostReviewEditText = findViewById(R.id.editTextHostReview);
        toolReviewEditText = findViewById(R.id.editTextToolReview);
        hostRatingBar = findViewById(R.id.ratingBarHost);
        toolRatingBar = findViewById(R.id.toolRatingBar);
        publishButton = findViewById(R.id.btnPublish);
        skipButton = findViewById(R.id.btnSkip);

        // Debugging: Log if views are null
        if (hostReviewEditText == null) Log.e(TAG, "Host Review EditText is null");
        if (toolReviewEditText == null) Log.e(TAG, "Tool Review EditText is null");
        if (hostRatingBar == null) Log.e(TAG, "Host RatingBar is null");
        if (toolRatingBar == null) Log.e(TAG, "Tool RatingBar is null");
    }
}