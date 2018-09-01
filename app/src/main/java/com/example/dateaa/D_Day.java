package com.example.dateaa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.HeterogeneousExpandableList;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class D_Day extends Activity {
    private static final int REQUEST_ACT = 2 ;
    CalendarView calendarView;
    LinearLayout li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_d__day);
        calendarView = findViewById(R.id.calendarview);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(D_Day.this, MainActivity.class);
                intent.putExtra("년도", year);
                intent.putExtra("월", month);
                intent.putExtra("일", dayOfMonth);
                startActivity(intent);
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x - 10;
                int height = size.y - 10;

            }

        });

    }
}