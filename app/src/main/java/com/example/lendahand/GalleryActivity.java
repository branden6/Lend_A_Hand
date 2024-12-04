package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Set click listeners for each image
        findViewById(R.id.image_bobcat).setOnClickListener(v -> returnSelectedImage(R.drawable.bobcat));
        findViewById(R.id.image_hammerdrill).setOnClickListener(v -> returnSelectedImage(R.drawable.hammerdrill));
        findViewById(R.id.image_laserlevel).setOnClickListener(v -> returnSelectedImage(R.drawable.laserlevel));
        findViewById(R.id.image_skillsaw).setOnClickListener(v -> returnSelectedImage(R.drawable.skillsaw));
        findViewById(R.id.image_welder).setOnClickListener(v -> returnSelectedImage(R.drawable.welder));
        findViewById(R.id.image_weldingmask).setOnClickListener(v -> returnSelectedImage(R.drawable.weldingmask));
    }

    private void returnSelectedImage(int imageResId) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedImage", imageResId);
        setResult(RESULT_OK, resultIntent);
        finish(); // Close the gallery
    }
}
