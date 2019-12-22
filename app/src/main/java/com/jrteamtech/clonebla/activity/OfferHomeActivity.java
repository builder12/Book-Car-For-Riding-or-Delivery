package com.jrteamtech.clonebla.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jrteamtech.clonebla.R;

public class OfferHomeActivity extends AppCompatActivity {


    Button btn_offer_ride;
    Button btn_offer_delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_home);

        btn_offer_ride = findViewById(R.id.btn_offer_ride);
        btn_offer_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_offer_delivery = findViewById(R.id.btn_offer_delivery);
        btn_offer_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OfferHomeActivity.this, PickUpActivity.class));
            }
        });
    }


}
