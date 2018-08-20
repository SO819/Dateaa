package com.example.dateaa;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

public class D_Day extends AppCompatActivity {
    private static final int REQUEST_ACT = 2 ;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d__day);
        calendarView = findViewById(R.id.calendarview);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                 Intent intent = new Intent(D_Day.this, MainActivity.class);
                intent.putExtra("년도",year);
                intent.putExtra("월",month);
                intent.putExtra("일",dayOfMonth);
                startActivity(intent);

            }

        });
    }

}