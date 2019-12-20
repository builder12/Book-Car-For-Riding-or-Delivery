package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class RideFilterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton close_btn;
    TextView minus_count_btn, plus_count_btn, count_view;
    Button btnApply;
    ProgressBar progressBar;

    int seat_count = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_filter);

        close_btn = findViewById(R.id.close_btn);
        minus_count_btn = findViewById(R.id.minus_counter_btn);
        plus_count_btn = findViewById(R.id.plus_counter_btn);
        count_view = findViewById(R.id.count_view);
        btnApply = findViewById(R.id.apply_btn);
        progressBar = findViewById(R.id.progressBar);

        close_btn.setOnClickListener(this);
        minus_count_btn.setOnClickListener(this);
        plus_count_btn.setOnClickListener(this);
        btnApply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                finish();
                break;
            case R.id.minus_counter_btn:
                if(seat_count != 1){
                    seat_count --;
                    count_view.setText(String.valueOf(seat_count));
                }
                break;
            case R.id.plus_counter_btn:
                if(seat_count != 3){
                    seat_count ++;
                    count_view.setText(String.valueOf(seat_count));
                }
                break;
            case R.id.apply_btn:
                btnApply.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;
        }
    }
}
