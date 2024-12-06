package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity { //homescreen

    RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5, ratingBar6;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;

    ImageView filterButton;
    List<Listing> listings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //setRating for each listing with method (rly a useless method but more for clarity/cleaner code in the onCreate):
        setRatingsOnListings();
        //set the photo for each listing with method:
        setPicturesOnListings();
        //set the listing price and name with method:
        setTextPriceAndListing();

        filter(); //filters for search button



        //When filter button is selected, go to SearchFilterActivity class:
        filterButton = findViewById(R.id.imageViewBurger);
        filterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchFilterActivity.class);
            startActivity(intent);
        });

        Button buttonCreateListing = findViewById(R.id.buttonCreateListing);

        // Set click listener to navigate to CreateListingActivity
        buttonCreateListing.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateListingActivity.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void filter(){
        SearchView searchView = findViewById(R.id.HomeScreenSearchFilter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No action needed for submit
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterListings(newText);
                return true;
            }
        });
    }

    private void filterListings(String query){
        if (textView1.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView1.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.VISIBLE);
            ratingBar1.setVisibility(View.VISIBLE);
        }
        else {
            textView1.setVisibility(View.GONE);
            imageView1.setVisibility(View.GONE);
            ratingBar1.setVisibility(View.GONE);
        }
        if (textView2.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView2.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            ratingBar2.setVisibility(View.VISIBLE);
        }
        else {
            textView2.setVisibility(View.GONE);
            imageView2.setVisibility(View.GONE);
            ratingBar2.setVisibility(View.GONE);
        }
        if (textView3.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView3.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            ratingBar3.setVisibility(View.VISIBLE);
        }
        else {
            textView3.setVisibility(View.GONE);
            imageView3.setVisibility(View.GONE);
            ratingBar3.setVisibility(View.GONE);
        }
        if (textView4.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView4.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.VISIBLE);
            ratingBar4.setVisibility(View.VISIBLE);
        }
        else {
            textView4.setVisibility(View.GONE);
            imageView4.setVisibility(View.GONE);
            ratingBar4.setVisibility(View.GONE);
        }
        if (textView5.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView5.setVisibility(View.VISIBLE);
            imageView5.setVisibility(View.VISIBLE);
            ratingBar5.setVisibility(View.VISIBLE);
        }
        else {
            textView5.setVisibility(View.GONE);
            imageView5.setVisibility(View.GONE);
            ratingBar5.setVisibility(View.GONE);
        }
        if (textView6.getText().toString().toLowerCase().contains(query.toLowerCase())) {
            textView6.setVisibility(View.VISIBLE);
            imageView6.setVisibility(View.VISIBLE);
            ratingBar6.setVisibility(View.VISIBLE);
        }
        else {
            textView6.setVisibility(View.GONE);
            imageView6.setVisibility(View.GONE);
            ratingBar6.setVisibility(View.GONE);
        }

    }
    public void setTextPriceAndListing(){
        textView1 = findViewById(R.id.textView);
        textView1.setText("$20 - Hammer Drill");
        textView2 = findViewById(R.id.textView2);
        textView2.setText("$50 - Suitcase welder");
        textView3 = findViewById(R.id.textView3);
        textView3.setText("$20 - Skill Saw");
        textView4 = findViewById(R.id.textView4);
        textView4.setText("$300 - Bobcat");
        textView5 = findViewById(R.id.textView5);
        textView5.setText("$10 - Welding Mask");
        textView6 = findViewById(R.id.textView6);
        textView6.setText("$100 - Laser Level");
    }

    public void setPicturesOnListings(){
        imageView1 = findViewById(R.id.imageView2);
        imageView1.setImageResource(R.drawable.hammerdrill);
        imageView2 = findViewById(R.id.imageView3);
        imageView2.setImageResource(R.drawable.welder);
        imageView3 = findViewById(R.id.imageView4);
        imageView3.setImageResource(R.drawable.skillsaw);
        imageView4 = findViewById(R.id.imageView5);
        imageView4.setImageResource(R.drawable.bobcat);
        imageView5 = findViewById(R.id.imageView6);
        imageView5.setImageResource(R.drawable.weldingmask);
        imageView6 = findViewById(R.id.imageView7);
        imageView6.setImageResource(R.drawable.laserlevel);
        // Click listener for opening listing
        imageView1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListingPage.class);
            startActivity(intent);
        });
    }

    public void setRatingsOnListings(){
        ratingBar1 = findViewById(R.id.ratingBar);
        ratingBar1.setRating(4.5f);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar2.setRating(4.0f);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar3.setRating(4.5f);
        ratingBar4 = findViewById(R.id.ratingBar4);
        ratingBar4.setRating(2.5f);
        ratingBar5 = findViewById(R.id.ratingBar5);
        ratingBar5.setRating(5.0f);
        ratingBar6 = findViewById(R.id.ratingBar6);
        ratingBar6.setRating(3.75f);
    }

}