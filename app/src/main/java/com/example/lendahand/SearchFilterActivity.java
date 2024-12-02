package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
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

    Button seeResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_filter);

        //addDollarSignToPrices(); //doesnt work, Just adding a dollar sign on the entered values for min/max price

        SelectedRadioLocations(); //if custom radius is selected, starts a new Google Maps view activity
        filterBackButton(); //if the back button is clicked while in the filters, it goes back to the homescreen

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
    public void addDollarSignToPrices(){
        editTextMinPrice = findViewById(R.id.editTextMinPrice);
        editTextMaxPrice = findViewById(R.id.editTextMaxPrice);

        editTextMinPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                editTextMaxPrice.setText("$" + editTextMaxPrice.getText().toString());
                editTextMinPrice.setText("$" + editTextMinPrice.getText().toString());
            }
        });
    }
}