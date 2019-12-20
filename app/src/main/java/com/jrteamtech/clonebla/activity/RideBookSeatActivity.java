package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RideBookSeatActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView minus_counter_btn, plus_counter_btn;
    TextView count_view;
    FloatingActionButton next_btn;

    JSONObject rideInfoObject;

    int count = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_book_seat);

        if (getIntent() != null && getIntent().getStringExtra("ride_info") != null){
            try {
                rideInfoObject = new JSONObject(getIntent().getStringExtra("ride_info"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        minus_counter_btn = findViewById(R.id.minus_counter_btn);
        plus_counter_btn = findViewById(R.id.plus_counter_btn);
        count_view = findViewById(R.id.count_view);
        next_btn = findViewById(R.id.next_btn);

        back_arrow_btn.setOnClickListener(this);
        minus_counter_btn.setOnClickListener(this);
        plus_counter_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        count_view.setText(String.valueOf(count));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.minus_counter_btn:
                if(count != 1) {
                    count --;
                    count_view.setText(String.valueOf(count));
                }
                break;
            case R.id.plus_counter_btn:
                if(count != 3){
                    count ++;
                    count_view.setText(String.valueOf(count));
                }
                break;
            case R.id.next_btn:
                Intent intent = new Intent(RideBookSeatActivity.this, RideFinalBookActivity.class);
                intent.putExtra("ride_info", rideInfoObject.toString());
                startActivity(intent);
                break;
        }
    }
}
