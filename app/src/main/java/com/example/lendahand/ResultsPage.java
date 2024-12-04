package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultsPage extends AppCompatActivity {

    Button clearFilters, filterBackButton;
    TextView textView1, textView2, textView3;
    ImageView img1, img2, img3;
    RatingBar ratingBar1, ratingBar2, ratingBar3;
    boolean concreteSelected, weldingSelected, carpentrySelected, roofingSelected, electricalSelected, paintingSelected, drillSelected, hammerSelected;
    String minPrice, maxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results_page);

        retrieveData();
        showData();
        backToFilter();

        filterBackButton = findViewById(R.id.filterButton2);
        filterBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsPage.this, SearchFilterActivity.class);
            intent.putExtra("Concrete", concreteSelected);
            intent.putExtra("Welding", weldingSelected);
            intent.putExtra("Carpentry", carpentrySelected);
            intent.putExtra("Roofing", roofingSelected);
            intent.putExtra("Electrical", electricalSelected);
            intent.putExtra("Painting", paintingSelected);
            intent.putExtra("Drills", drillSelected);
            intent.putExtra("Hammers", hammerSelected);
            if (!minPrice.isEmpty())
                intent.putExtra("MinPrice", minPrice);
            if (!maxPrice.isEmpty())
                intent.putExtra("MaxPrice", maxPrice);
            startActivity(intent);
        });

        clearFilters = findViewById(R.id.buttonClearFilters);
        clearFilters.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsPage.this, MainActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void backToFilter() {

    }

    private void showData() {
        textView1 = findViewById(R.id.textView9);
        textView1.setText("$50 - Suitcase Welder");
        img1 = findViewById(R.id.imageView2);
        img1.setImageResource(R.drawable.welder);
        ratingBar1 = findViewById(R.id.ratingBar7);
        ratingBar1.setRating(4.0f);

        textView2 = findViewById(R.id.textView10);
        textView2.setText("$20 - Skill Saw");
        img2 = findViewById(R.id.imageView3);
        img2.setImageResource(R.drawable.skillsaw);
        ratingBar2 = findViewById(R.id.ratingBar8);
        ratingBar2.setRating(4.5f);

        textView3 = findViewById(R.id.textView11);
        textView3.setText("$10 - Welding Mask");
        img3 = findViewById(R.id.imageView4);
        img3.setImageResource(R.drawable.weldingmask);
        ratingBar3 = findViewById(R.id.ratingBar9);
        ratingBar3.setRating(5.0f);

    }

    public void retrieveData() {
        Intent intent = getIntent();
        concreteSelected = intent.getBooleanExtra("Concrete", false);
        weldingSelected = intent.getBooleanExtra("Welding", false);
        carpentrySelected = intent.getBooleanExtra("Carpentry", false);
        roofingSelected = intent.getBooleanExtra("Roofing", false);
        electricalSelected = intent.getBooleanExtra("Electrical", false);
        paintingSelected = intent.getBooleanExtra("Painting", false);
        drillSelected = intent.getBooleanExtra("Drills", false);
        hammerSelected = intent.getBooleanExtra("Hammer", false);
        minPrice = intent.getStringExtra("MinPrice");
        maxPrice = intent.getStringExtra("MaxPrice");
    }
}