package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RideFinalBookActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView date_view;
    TextView leave_time_view, leave_address_view, leave_city_view;
    TextView drop_time_view, drop_address_view, drop_city_view;
    TextView total_price_view;
    TextView btnPriceDetail;
    TextView btnBook;

    JSONObject rideInfoObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_final_book);

        if(getIntent() != null && getIntent().getStringExtra("ride_info") != null){
            try {
                rideInfoObject = new JSONObject(getIntent().getStringExtra("ride_info"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        date_view = findViewById(R.id.date_view);
        leave_time_view = findViewById(R.id.leave_time_view);
        leave_address_view = findViewById(R.id.leave_address_view);
        leave_city_view = findViewById(R.id.leave_city_view);
        drop_time_view = findViewById(R.id.drop_time_view);
        drop_address_view = findViewById(R.id.drop_address_view);
        drop_city_view = findViewById(R.id.drop_city_view);
        total_price_view = findViewById(R.id.total_price_view);
        btnPriceDetail = findViewById(R.id.tv_price_detail_btn);
        btnBook = findViewById(R.id.book_btn);

        back_arrow_btn.setOnClickListener(this);
        btnPriceDetail.setOnClickListener(this);
        btnBook.setOnClickListener(this);

        initialize();

    }

    private void initialize() {
        try {
            date_view.setText(rideInfoObject.getString("date"));
            leave_time_view.setText(rideInfoObject.getString("leave_time"));
            leave_address_view.setText(rideInfoObject.getString("leave_address"));
            leave_city_view.setText(rideInfoObject.getString("leave_city"));
            drop_time_view.setText(rideInfoObject.getString("drop_time"));
            drop_address_view.setText(rideInfoObject.getString("drop_address"));
            drop_city_view.setText(rideInfoObject.getString("drop_city"));
            total_price_view.setText(rideInfoObject.getString("currency") + rideInfoObject.getString("price"));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.tv_price_detail_btn:
                Intent intent = new Intent(RideFinalBookActivity.this, RidePriceDetailsActivity.class);
                intent.putExtra("ride_info", rideInfoObject.toString());
                startActivity(intent);
                break;
            case R.id.book_btn:
//                startActivity(new Intent(RideFinalBookActivity.this, PaymentActivity.class));
                break;
        }
    }
}
