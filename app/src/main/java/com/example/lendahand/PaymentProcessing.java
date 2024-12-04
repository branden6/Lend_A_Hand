package com.example.lendahand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentProcessing extends AppCompatActivity {

    Button x;
    Button confirmPayment;

    TextView startPoint;
    TextView endPoint;
    TextView totalDisplay;

    EditText cardName;
    EditText cardNumber;
    EditText cardExpiry;
    EditText cardSecurity;
    EditText billingAddress;
    EditText city;
    EditText postalCode;

    CheckBox termsAndConditions;
    TextView termsAndConditionsText;

    String startTime = "";
    String endTime = "";
    String date = "";
    double startHour;
    double startMinute;
    double endHour;
    double endMinute;
    double cost = 0;
    double hst = 1.13;

    String tac = "Cubilia lacus congue in enim facilisi sociosqu. Pretium urna accumsan facilisi velit; aliquet tincidunt. Dignissim sed venenatis pellentesque vivamus congue. Purus est ultricies elit himenaeos a eget suspendisse elementum. Ut bibendum dignissim at tortor sapien; amet ac. Senectus viverra aliquet rutrum libero et auctor.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_processing);

        Bundle prevBundle = getIntent().getExtras();

        startPoint = findViewById(R.id.startPoint);
        endPoint = findViewById(R.id.endPoint);

        x = findViewById(R.id.X);
        confirmPayment = findViewById(R.id.confirmPayment);

        totalDisplay = findViewById(R.id.totalDisplay);
        cardName = findViewById(R.id.cardName);
        cardNumber = findViewById(R.id.cardNumber);
        cardExpiry = findViewById(R.id.cardExpiry);
        cardSecurity = findViewById(R.id.cardSecurity);
        billingAddress = findViewById(R.id.billingAddress);
        city = findViewById(R.id.city);
        postalCode = findViewById(R.id.postalCode);

        termsAndConditions = findViewById(R.id.checkBox);
        termsAndConditionsText = findViewById(R.id.termsText);

        cost = prevBundle.getDouble("estimatedCost");
        cost = Math.round((cost*hst) * 100.0) / 100.0;

        if (prevBundle != null) {
            cost = prevBundle.getDouble("estimatedCost", 0.0);
            cost = Math.round((cost * hst) * 100.0) / 100.0;

            date = prevBundle.getString("date", "");
            startHour = prevBundle.getDouble("startHour", -1.0);
            startMinute = prevBundle.getDouble("startMinute", -1.0);
            endHour = prevBundle.getDouble("endHour", -1.0);
            endMinute = prevBundle.getDouble("endMinute", -1.0);

            if (!date.isEmpty() && startHour != -1.0 && startMinute != -1.0 && endHour != -1.0 && endMinute != -1.0) {
                startPoint.setText(date + " " + String.format("%02d:%02d", (int) startHour, (int) startMinute));
                endPoint.setText(date + " " + String.format("%02d:%02d", (int) endHour, (int) endMinute));
            } else {
                startTime = prevBundle.getString("startDate", "Start Date Not Set");
                endTime = prevBundle.getString("endDate", "End Date Not Set");

                startPoint.setText(startTime);
                endPoint.setText(endTime);
            }

            totalDisplay.setText("$" + cost);
        } else {
            Toast.makeText(this, "Error: No data received.", Toast.LENGTH_SHORT).show();
        }



        x.setOnClickListener(v -> {
            finish();
        });

        termsAndConditionsText.setOnClickListener(v -> {
            new AlertDialog.Builder(PaymentProcessing.this)
            .setTitle("Terms and Conditions")
            .setMessage(tac)
            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .show();
        });

        confirmPayment.setOnClickListener(v -> {
            if (checkAllInputs()) {
                Intent intent = new Intent(PaymentProcessing.this, PaymentConfirmation.class);
                startActivity(intent);
            }
        });


    }

    public boolean checkAllInputs() {
        if (cardName.getText().toString().isEmpty() || cardNumber.getText().toString().isEmpty() || cardExpiry.getText().toString().isEmpty() || cardSecurity.getText().toString().isEmpty() || billingAddress.getText().toString().isEmpty() || !(termsAndConditions.isChecked()) || city.getText().toString().isEmpty() || postalCode.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
