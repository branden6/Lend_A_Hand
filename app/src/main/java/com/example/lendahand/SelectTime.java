package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectTime extends AppCompatActivity {
    TimePicker startTimePicker;
    TimePicker endTimePicker;

    TextView startHours;
    TextView endHours;
    TextView estimatedHourly;

    Button returnButton;
    Button confirmButton;

    double hourlyPrice;
    double estimatedCost;

    int startHour = 0;
    int startMinute = 0;
    int endHour = 0;
    int endMinute = 0;
    double totalHours = 0;

    long date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_time);

        Bundle prevBundle = getIntent().getExtras();
        hourlyPrice = prevBundle.getDouble("hourPrice");

        startTimePicker = findViewById(R.id.beforeTimePicker);
        endTimePicker = findViewById(R.id.afterTimePicker);

        startHours = findViewById(R.id.startHours);
        endHours = findViewById(R.id.endHours);
        estimatedHourly = findViewById(R.id.estimatedHourly);

        returnButton = findViewById(R.id.returnCalendar);
        confirmButton = findViewById(R.id.confirmHours);

        startTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            startHour = hourOfDay;
            startMinute = minute;
            if (isValidStartTime(startHour, startMinute)) {
                startHours.setText(startHour + " : " + startMinute);

                if (isValidTime(startHour, startMinute, endHour, endMinute)) {
                    totalHours = calculateTotalHours(startHour, startMinute, endHour, endMinute);
                    estimatedCost = Math.round((totalHours * hourlyPrice)*100.0)/100.0;
                    estimatedHourly.setText("$" + String.valueOf(estimatedCost));
                }
            }
        });

        endTimePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            endHour = hourOfDay;
            endMinute = minute;
            if (isValidEndTime(endHour, endMinute)) {
                endHours.setText(endHour + " : " + endMinute);

                if (isValidTime(startHour, startMinute, endHour, endMinute)) {
                    totalHours = calculateTotalHours(startHour, startMinute, endHour, endMinute);
                    estimatedCost = Math.round((totalHours * hourlyPrice)*100.0)/100.0;
                    estimatedHourly.setText("$" + String.valueOf(estimatedCost));
                }
            }
        });

        returnButton.setOnClickListener(view -> {
            finish();
        });

        confirmButton.setOnClickListener(view -> {
            if (totalHours == 0) {
                Toast.makeText(this, "Please select a valid time", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidTime(startHour, startMinute, endHour, endMinute)) {
                Toast.makeText(this, "Please select a valid time", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putDouble("estimatedCost", estimatedCost);
            bundle.putString("date", prevBundle.getString("endDate"));
            bundle.putDouble("startHour", startHour);
            bundle.putDouble("startMinute", startMinute);
            bundle.putDouble("endHour", endHour);
            bundle.putDouble("endMinute", endMinute);

            Intent intent = new Intent(this, PaymentProcessing.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }

    private boolean isValidStartTime(int startHour, int startMinute) {
        if ((startMinute != 0 && startMinute != 30)) {
            return false;
        }
        return true;
    }

    private boolean isValidEndTime(int endHour, int endMinute) {
        if ((endMinute != 0 && endMinute != 30)) {
            return false;
        }
        return true;
    }

    private boolean isValidTime(int startHour, int startMinute, int endHour, int endMinute) {
        if (startHour > endHour ) {
            return false;
        }
        if (startHour == endHour && startMinute >= endMinute) {
            return false;
        }
        if (startHour == endHour && startMinute == endMinute) {
            return false;
        }
        return true;
    }

    private double calculateTotalHours(int startHour, int startMinute, int endHour, int endMinute) {
        double startTotalMinutes = (startHour * 60) + startMinute;
        double endTotalMinutes = (endHour * 60) + endMinute;
        return (endTotalMinutes - startTotalMinutes) / 60;
    }
}