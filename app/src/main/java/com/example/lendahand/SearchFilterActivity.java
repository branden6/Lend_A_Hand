package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SearchFilterActivity extends AppCompatActivity {
    EditText editTextMinPrice, editTextMaxPrice;
    ImageView imageViewBack;
    RadioGroup locationGroup;
    RadioButton radioButtonCurrentLocation, radioButtonCustomLocation;
    CheckBox concrete, welding, carpentry, roofing, electrical, painting, drills, hammers;

    Button seeResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_filter);
        setupButtons();

        seeResults = findViewById(R.id.buttonSeeResults);
        seeResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFilterActivity.this, ResultsPage.class);
                intent.putExtra("Concrete", concrete.isChecked());
                intent.putExtra("Welding", welding.isChecked());
                intent.putExtra("Carpentry", carpentry.isChecked());
                intent.putExtra("Roofing", roofing.isChecked());
                intent.putExtra("Electrical", electrical.isChecked());
                intent.putExtra("Painting", painting.isChecked());
                intent.putExtra("Drills", drills.isChecked());
                intent.putExtra("Hammers", hammers.isChecked());
                intent.putExtra("MinPrice", editTextMinPrice.getText().toString());
                intent.putExtra("MaxPrice", editTextMaxPrice.getText().toString());
                startActivity(intent);
            }
        });



        SelectedRadioLocations(); //if custom radius is selected, starts a new Google Maps view activity
        filterBackButton(); //if the back button is clicked while in the filters, it goes back to the homescreen

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void setupButtons(){
        concrete = findViewById(R.id.checkBoxConcrete);
        welding = findViewById(R.id.checkBoxWelding);
        carpentry = findViewById(R.id.checkBoxCarpentry);
        roofing = findViewById(R.id.checkBoxRoofing);
        electrical = findViewById(R.id.checkBoxElectrical);
        painting = findViewById(R.id.checkBoxPainting);
        drills = findViewById(R.id.checkBoxDrills);
        hammers = findViewById(R.id.checkBoxHammers);
    }
    public void filterBackButton(){
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(v -> { //if the back button is clicked while in the filters, it goes back to the homescreen
            Intent intent = new Intent(SearchFilterActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void SelectedRadioLocations(){
        radioButtonCustomLocation = findViewById(R.id.radioButtonCustomLocation);
        locationGroup = findViewById(R.id.radioGroupLocation); //getting radioGroup for location
        locationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //if custom radius is selected, starts a new Google Maps view activity
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonCustomLocation) {
                    Intent intent = new Intent(SearchFilterActivity.this, CustomLocationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}