package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class EditPriceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView src_tar_city_name_view;
    TextView minus_counter_btn, plus_counter_btn;
    TextView price_view;
    TextView confirm_btn;

    private int aa=192;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_price);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        src_tar_city_name_view = findViewById(R.id.src_tar_city_name_view);
        minus_counter_btn = findViewById(R.id.minus_counter_btn);
        plus_counter_btn = findViewById(R.id.plus_counter_btn);
        price_view = findViewById(R.id.price_view);
        confirm_btn = findViewById(R.id.confirm_btn);

        back_arrow_btn.setOnClickListener(this);
        minus_counter_btn.setOnClickListener(this);
        plus_counter_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.minus_counter_btn:
                aa--;
               price_view.setText("$"+aa);
                break;
            case R.id.plus_counter_btn:
                aa++;
                price_view.setText("$"+aa);
                break;
            case R.id.confirm_btn:
                startActivity(new Intent(EditPriceActivity.this, PublishReturnTripActivity.class));
                break;
        }
    }
}
