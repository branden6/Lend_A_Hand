package com.example.lendahand;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find buttons in the layout
        Button writeReviewButton = findViewById(R.id.btnWriteReviewPrompt);
        Button skipButton = findViewById(R.id.btnSkip);

        // Check if the buttons are correctly linked
        if (writeReviewButton == null || skipButton == null) {
            Log.e(TAG, "Buttons not found in layout. Check activity_main2.xml for missing IDs.");
            return;
        }

        // Write Review button functionality
        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Write Review button clicked.");
                Intent intent = new Intent(MainActivity2.this, WriteReviewActivity.class);
                startActivity(intent);
                finish(); // Close MainActivity2
            }
        });

        // Skip button functionality: Redirect to friend's MainActivity
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Skip button clicked. Redirecting to friend's MainActivity.");
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
            finish(); // Close MainActivity2
        } catch (Exception e) {
            Log.e(TAG, "Error redirecting to friend's MainActivity", e);
        }
    }
}