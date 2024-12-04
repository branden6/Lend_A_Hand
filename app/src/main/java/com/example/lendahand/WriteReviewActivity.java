package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WriteReviewActivity extends AppCompatActivity {

    private EditText hostReviewEditText, toolReviewEditText;
    private RatingBar hostRatingBar, toolRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        hostReviewEditText = findViewById(R.id.editTextHostReview);
        toolReviewEditText = findViewById(R.id.editTextToolReview);
        hostRatingBar = findViewById(R.id.ratingBarHost);
        toolRatingBar = findViewById(R.id.toolRatingBar);
        Button publishButton = findViewById(R.id.btnPublish);
        Button skipButton = findViewById(R.id.btnSkip);


        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(WriteReviewActivity.this, "Review Published!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(WriteReviewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WriteReviewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
