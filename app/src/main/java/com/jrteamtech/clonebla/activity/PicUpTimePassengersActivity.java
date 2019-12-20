package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class PicUpTimePassengersActivity extends AppCompatActivity {

    private ImageView close;
    private Button gotit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_up_time_passengers);

        close = (ImageView) findViewById(R.id.multi);
        gotit = (Button) findViewById(R.id.gotit);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gotit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
    }
}
