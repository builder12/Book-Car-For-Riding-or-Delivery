package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RidePriceDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView seat_count_view, operation_price_view, calculated_price_view, service_fee_price_view, total_price_view;

    JSONObject rideInfoObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_price_details);

        if(getIntent() != null && getIntent().getStringExtra("ride_info") != null){
            try {
                rideInfoObject = new JSONObject(getIntent().getStringExtra("ride_info"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        seat_count_view = findViewById(R.id.seat_count_view);
        operation_price_view = findViewById(R.id.operation_price_view);
        calculated_price_view = findViewById(R.id.calculated_price_view);
        service_fee_price_view = findViewById(R.id.service_fee_price_view);
        total_price_view = findViewById(R.id.total_price_view);

        back_arrow_btn.setOnClickListener(this);

        try {
            long seat_count = Long.parseLong(rideInfoObject.getString("seat"));
            long total_price = Long.parseLong(rideInfoObject.getString("price"));
            long calculated_price = total_price - 4;

            seat_count_view.setText(String.valueOf(seat_count));
            operation_price_view.setText(rideInfoObject.getString("currency") + calculated_price);
            calculated_price_view.setText(rideInfoObject.getString("currency") + calculated_price);
            total_price_view.setText(rideInfoObject.getString("currency") + total_price);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
        }
    }
}
