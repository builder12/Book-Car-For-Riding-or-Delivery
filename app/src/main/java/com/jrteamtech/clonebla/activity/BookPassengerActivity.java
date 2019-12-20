package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class BookPassengerActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    RelativeLayout yes_btn, no_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_passenger);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        yes_btn = findViewById(R.id.yes_btn);
        no_btn = findViewById(R.id.no_btn);

        back_arrow_btn.setOnClickListener(this);
        yes_btn.setOnClickListener(this);
        no_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.yes_btn:
                String yes = "yes";
                startActivity(new Intent(BookPassengerActivity.this, PaymentActivity.class));
                break;
            case R.id.no_btn:
                String no = "no";
                startActivity(new Intent(BookPassengerActivity.this, PaymentActivity.class));
                break;
        }
    }
}
