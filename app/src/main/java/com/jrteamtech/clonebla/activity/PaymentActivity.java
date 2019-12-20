package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView continue_btn, cancel_agree_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        continue_btn = findViewById(R.id.continue_btn);
        cancel_agree_btn = findViewById(R.id.cancel_agree_btn);

        back_arrow_btn.setOnClickListener(this);
        continue_btn.setOnClickListener(this);
        cancel_agree_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.continue_btn:
                startActivity(new Intent(PaymentActivity.this, PublishReturnTripActivity.class));
                break;
            case R.id.cancel_agree_btn:
                startActivity(new Intent(PaymentActivity.this, GivingPriceActivity.class));
                break;
        }
    }
}
