package com.jrteamtech.clonebla.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jrteamtech.clonebla.R;

public class FindHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_home);

        Button find_delivery_btn;
        find_delivery_btn = findViewById(R.id.btn_find_delivery);
        find_delivery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindHomeActivity.this,FindDeliveryActivity.class));
            }
        });
    }
}
