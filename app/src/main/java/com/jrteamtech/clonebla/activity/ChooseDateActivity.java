package com.jrteamtech.clonebla.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.utility.Global;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.calendarlistview.CalendarUtils;
import com.jrteamtech.clonebla.calendarlistview.DayPickerView;
import com.jrteamtech.clonebla.calendarlistview.SimpleMonthAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ChooseDateActivity extends AppCompatActivity implements com.jrteamtech.clonebla.calendarlistview.DatePickerController {

    private DayPickerView dayPickerView;
    private ImageView multi;

    private String[] weeks = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    private String activity_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        if(getIntent() != null){
            activity_name = getIntent().getStringExtra(getResources().getString(R.string.activity_name));
        }

        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        multi     = (ImageView)findViewById(R.id.multi);
        dayPickerView.setController(this);

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if(id == R.id.multi){
                   /* Intent intent = new Intent(ChooseDateActivity.this,SearchFragment.class);
                    startActivity(intent);*/
                   finish();

                }
            }
        });
    }





    @Override
    public int getMaxYear()
    {
        return 2020;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse("" + year + "-" + month + "-" + day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
           Log.e("Week selected", "" + calendar.get(Calendar.DAY_OF_WEEK));
            String week = weeks[calendar.get(Calendar.DAY_OF_WEEK) == 7 ? 0 : calendar.get(Calendar.DAY_OF_WEEK)];
            Global.setSelected_time(week + ". " + day + " " + CalendarUtils.getMonthFromIndex(month) + ", ");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, SelectTimeActivity.class);
        intent.putExtra(getResources().getString(R.string.activity_name), activity_name);
//        Log.e("Day Selected", day + " / " + month + " / " + year);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays)
    {

        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}
