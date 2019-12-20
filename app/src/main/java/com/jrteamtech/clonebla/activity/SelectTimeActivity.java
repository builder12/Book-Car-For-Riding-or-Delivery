package com.jrteamtech.clonebla.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jrteamtech.clonebla.utility.Global;
import com.jrteamtech.clonebla.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SelectTimeActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private static final String TIME_PATTERN = "HH:mm";
    private Calendar calendar;
    private SimpleDateFormat timeFormat;


    private String changetime = "08:00";
    private FloatingActionButton rightnav;
    private ImageButton leftnav;
    private TextView  timeimage,calendardate;
    private TextView lblDate;
    private TextView lblTime;
 //   private DateFormat dateFormat;

    private String activity_name = "";


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_time);

        if(getIntent() != null){
            activity_name = getIntent().getStringExtra(getResources().getString(R.string.activity_name));
        }

        timeimage = (TextView) findViewById(R.id.timecalculate);
        calendardate = (TextView) findViewById(R.id.calendardate);
        leftnav   = (ImageButton) findViewById(R.id.leftnav);
        rightnav  = (FloatingActionButton) findViewById(R.id.rightnav);
        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());


        this.timeimage.setOnClickListener(this);
        this.leftnav.setOnClickListener(this);
        this.rightnav.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        int id = view.getId();
        if(id == R.id.leftnav){
//            Intent intent = new Intent(this,ChooseDateActivity.class);
//            startActivity(intent);
            finish();
        }else if(id == R.id.rightnav){

            //  changetime = timeFormat.format(calendar.getTime());
          Global.setSelected_time(Global.getSelected_time() + " " + changetime);

            if(activity_name.equals(StopOversActivity.class.getSimpleName())){
                startActivity(new Intent(SelectTimeActivity.this, ThinkComfortActivity.class));
            } else if(activity_name.equals(PublishReturnTripActivity.class.getSimpleName())) {
                startActivity(new Intent(SelectTimeActivity.this, AddPublishRideActivity.class));
            } else {
                finish();
            }

//            calendardate.setText(timeFormat.format(calendar.getTime()));

        }else if(id == R.id.timecalculate){
            new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true){
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    if(hourOfDay<10 && minute <10){
                        changetime = "" +"0" +hourOfDay +":"+"0" + minute;
                    }else if (hourOfDay<10 && minute >10) {
                        changetime = "" +"0" +hourOfDay +":" + minute;
                    }else if(hourOfDay>10 && minute<10){
                        changetime = "" +hourOfDay +":"+"0"+ minute;
                    }else {
                        changetime = "" + hourOfDay + ":" + minute;
                    }

                }
            }.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        update();
    }

    private void update() {
//        lblDate.setText(dateFormat.format(calendar.getTime()));
        timeimage.setText(timeFormat.format(calendar.getTime()));
   }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
