package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentConfirmation extends AppCompatActivity {
    Button returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_confirmation);

        returnHome = findViewById(R.id.returnHome);
        returnHome.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentConfirmation.this, ListingPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

    }
}