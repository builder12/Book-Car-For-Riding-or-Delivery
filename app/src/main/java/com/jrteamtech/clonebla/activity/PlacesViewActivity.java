package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class PlacesViewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_view);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        confirm_btn = findViewById(R.id.confirm_btn);

        back_arrow_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.confirm_btn:
                startActivity(new Intent(PlacesViewActivity.this, ChooseDateActivity.class));
                break;
        }
    }
}
