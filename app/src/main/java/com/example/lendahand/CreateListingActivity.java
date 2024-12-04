package com.example.lendahand;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateListingActivity extends AppCompatActivity {

    private static final int FAKE_GALLERY_REQUEST_CODE = 1001;

    private Calendar selectedStartDate = null;
    private Calendar selectedEndDate = null;
    private Integer selectedImageResId = null;
    private TextView selectedDatesText;
    private ImageView selectedImageView;
    private String selectedPriceOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        EditText priceInput = findViewById(R.id.priceInput);
        EditText postalCodeInput = findViewById(R.id.postalCodeInput);
        Spinner priceOptionsSpinner = findViewById(R.id.priceOptionsSpinner);
        Button selectDatesButton = findViewById(R.id.selectDatesButton);
        selectedDatesText = findViewById(R.id.selectedDatesText);
        Button publishButton = findViewById(R.id.publishButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button addImagesButton = findViewById(R.id.addImagesButton);
        selectedImageView = findViewById(R.id.selectedImageView);

        // Handle existing listing data if editing
        int listingIndex = getIntent().getIntExtra("listingIndex", -1);
        if (listingIndex != -1) {
            populateFieldsWithListing(listingIndex, titleInput, descriptionInput, priceInput, postalCodeInput);
        }

        // Launch fake gallery for selecting an image
        addImagesButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateListingActivity.this, GalleryActivity.class);
            startActivityForResult(intent, FAKE_GALLERY_REQUEST_CODE);
        });

        // Allow clicking the image to change it
        selectedImageView.setOnClickListener(v -> {
            Intent intent = new Intent(CreateListingActivity.this, GalleryActivity.class);
            startActivityForResult(intent, FAKE_GALLERY_REQUEST_CODE);
        });

        // Handle spinner selection
        priceOptionsSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectedPriceOption = parent.getItemAtPosition(position).toString();
                selectedStartDate = null;
                selectedEndDate = null;
                selectedDatesText.setText("Selected Dates: None");
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                selectedPriceOption = "/day"; // Default option
            }
        });

        // Handle date selection
        selectDatesButton.setOnClickListener(v -> {
            if (selectedPriceOption.equals("/day")) {
                showSingleDatePicker();
            } else if (selectedPriceOption.equals("/week") || selectedPriceOption.equals("/month")) {
                showStartDatePicker();
            }
        });

        publishButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String price = priceInput.getText().toString();
            String postalCode = postalCodeInput.getText().toString();

            if (title.isEmpty() || description.isEmpty() || price.isEmpty() || postalCode.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedStartDate == null) {
                Toast.makeText(this, "Please select a date or range", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedImageResId == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create or update the listing
            Listing listing = new Listing(title, description, selectedImageResId, price, selectedPriceOption, postalCode);

            if (listingIndex == -1) {
                ListingsActivity.addListing(listing);
            } else {
                ListingsActivity.updateListing(listingIndex, listing);
            }

            // Navigate to ListingsActivity
            Intent intent = new Intent(CreateListingActivity.this, ListingsActivity.class);
            startActivity(intent);
            finish();
        });

        cancelButton.setOnClickListener(v -> {
            Toast.makeText(this, "Listing creation canceled", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void showSingleDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedStartDate = Calendar.getInstance();
                    selectedStartDate.set(year, month, dayOfMonth);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
                    selectedDatesText.setText("Selected Date: " + dateFormat.format(selectedStartDate.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showStartDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedStartDate = Calendar.getInstance();
                    selectedStartDate.set(year, month, dayOfMonth);
                    showEndDatePicker();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showEndDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedEndDate = Calendar.getInstance();
                    selectedEndDate.set(year, month, dayOfMonth);
                    updateSelectedDatesText();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateSelectedDatesText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

        if (selectedPriceOption.equals("/month")) {
            selectedDatesText.setText("Selected Month: " + monthFormat.format(selectedStartDate.getTime()));
        } else if (selectedPriceOption.equals("/week")) {
            selectedDatesText.setText("Selected Week: " +
                    dateFormat.format(selectedStartDate.getTime()) + " – " +
                    dateFormat.format(selectedEndDate.getTime()));
        }
    }

    private void populateFieldsWithListing(int listingIndex, EditText titleInput, EditText descriptionInput, EditText priceInput, EditText postalCodeInput) {
        Listing listing = ListingsActivity.getListing(listingIndex);
        titleInput.setText(listing.getTitle());
        descriptionInput.setText(listing.getDescription());
        priceInput.setText(listing.getPrice());
        postalCodeInput.setText(listing.getPostalCode());
        selectedImageResId = listing.getImageResId();
        selectedImageView.setImageResource(selectedImageResId);
        selectedPriceOption = listing.getPriceUnit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FAKE_GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                int imageResId = data.getIntExtra("selectedImage", -1);
                if (imageResId != -1) {
                    selectedImageResId = imageResId;
                    selectedImageView.setImageResource(imageResId);
                }
            }
        }
    }
}
