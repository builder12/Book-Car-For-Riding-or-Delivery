package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class GivingPriceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    TextView edit_amount_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giving_price);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        edit_amount_btn = findViewById(R.id.edit_amount_btn);

        back_arrow_btn.setOnClickListener(this);
        edit_amount_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.edit_amount_btn:
                startActivity(new Intent(GivingPriceActivity.this, EditPriceActivity.class));
                break;
        }
    }
}
