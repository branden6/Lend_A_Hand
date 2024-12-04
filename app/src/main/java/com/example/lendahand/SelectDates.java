package com.example.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SelectDates extends AppCompatActivity {

    CalendarView calendarView;
    Button returnButton, nextButton;
    TextView startDateView, endDateView;

    private long startDate = -1;
    private long endDate = -1;

    String startDateString = "";
    String endDateString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_dates);

        calendarView = findViewById(R.id.calendarView);
        returnButton = findViewById(R.id.returnListing);
        nextButton = findViewById(R.id.next);
        startDateView = findViewById(R.id.startDate);
        endDateView = findViewById(R.id.endDate);

        Bundle prevBundle = getIntent().getExtras();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            long selectedTime = selectedDate.getTimeInMillis();

            if (startDate == -1) {
                startDate = selectedTime;
                startDateString = formatDate(year, month, dayOfMonth);
                startDateView.setText(formatDate(year, month, dayOfMonth));
                endDateView.setText("");
                Toast.makeText(this, "Start Date Selected", Toast.LENGTH_SHORT).show();
            } else if (endDate == -1) {
                if (selectedTime == startDate) {
                    endDate = startDate;
                    endDateString = startDateString;
                    startDateView.setText(formatDate(year, month, dayOfMonth));
                    endDateView.setText(formatDate(year, month, dayOfMonth));
                    Toast.makeText(this, "Same Date Selected Twice", Toast.LENGTH_SHORT).show();
                } else if (selectedTime > startDate) {
                    endDate = selectedTime;
                    endDateString = formatDate(year, month, dayOfMonth);
                    endDateView.setText(formatDate(year, month, dayOfMonth));
                    Toast.makeText(this, "End Date Selected", Toast.LENGTH_SHORT).show();
                } else {
                    startDate = selectedTime;
                    startDateString = formatDate(year, month, dayOfMonth);
                    endDate = -1;
                    startDateView.setText(formatDate(year, month, dayOfMonth));
                    endDateView.setText("");
                    Toast.makeText(this, "New Start Date Selected", Toast.LENGTH_SHORT).show();
                }
            } else {
                startDate = selectedTime;
                startDateString = formatDate(year, month, dayOfMonth);
                endDate = -1;
                startDateView.setText(formatDate(year, month, dayOfMonth));
                endDateView.setText("");
                Toast.makeText(this, "New Start Date Selected", Toast.LENGTH_SHORT).show();
            }


        });

        returnButton.setOnClickListener(v -> {
            finish();
        });

        nextButton.setOnClickListener(v -> {
            if (endDate == -1 || startDate == -1) {
                Toast.makeText(this, "Please select both start and end dates", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isSameDay(startDate, endDate)) {
                Intent intent = new Intent(this, SelectTime.class);
                Bundle bundle = new Bundle();;
                bundle.putString("endDate", endDateString);
                double price = prevBundle.getDouble("hourPrice");
                bundle.putDouble("hourPrice", price);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, PaymentProcessing.class);
                Bundle bundle = new Bundle();
                bundle.putString("startDate", startDateString);
                bundle.putString("endDate", endDateString);
                double price = prevBundle.getDouble("dayPrice");
                price *= getTotalDaysBetween();
                bundle.putDouble("estimatedCost", price);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }

    private String formatDate(int year, int month, int day) {
        return day + "/" + (month + 1) + "/" + year;
    }

    private boolean isSameDay(long time1, long time2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        cal2.setTimeInMillis(time2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private int getTotalDaysBetween() {
        long differenceInMillis = Math.abs(endDate - startDate);

        return (int) (differenceInMillis / (1000 * 60 * 60 * 24));
    }
}