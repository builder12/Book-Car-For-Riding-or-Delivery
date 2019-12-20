package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class AddPublishRideActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back_arrow_btn;
    EditText edit_about_ride;
    TextView publish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publish_ride);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        edit_about_ride = findViewById(R.id.edit_about_ride);
        publish_btn = findViewById(R.id.publish_btn);

        back_arrow_btn.setOnClickListener(this);
        publish_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.publish_btn:
                startActivity(new Intent(AddPublishRideActivity.this, PublishSuccessActivity.class));
                break;
        }
    }
}
