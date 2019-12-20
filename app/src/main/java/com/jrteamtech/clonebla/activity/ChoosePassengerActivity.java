package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jrteamtech.clonebla.R;

public class ChoosePassengerActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView minus_counter_btn, plus_counter_btn;
    TextView count_view;
    FloatingActionButton next_btn;

    int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_passenger);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        minus_counter_btn = findViewById(R.id.minus_counter_btn);
        plus_counter_btn = findViewById(R.id.plus_counter_btn);
        count_view = findViewById(R.id.count_view);
        next_btn = findViewById(R.id.next_btn);

        count_view.setText(String.valueOf(count));

        back_arrow_btn.setOnClickListener(this);
        minus_counter_btn.setOnClickListener(this);
        plus_counter_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.minus_counter_btn:
                if(count != 1){
                    count = count -1;
                }
                count_view.setText(String.valueOf(count));
                break;
            case R.id.plus_counter_btn:
                if(count != 3){
                    count = count + 1;
                }
                count_view.setText(String.valueOf(count));
                break;
            case R.id.next_btn:
                startActivity(new Intent(ChoosePassengerActivity.this, BookPassengerActivity.class));
                break;
        }
    }
}
