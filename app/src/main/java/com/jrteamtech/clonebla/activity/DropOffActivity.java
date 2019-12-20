package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class DropOffActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    ImageButton back_arrow_btn;
    EditText et_location_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropoff);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        et_location_search = findViewById(R.id.et_location_search);

        back_arrow_btn.setOnClickListener(this);
        et_location_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.et_location_search:
                Intent intent = new Intent(DropOffActivity.this, SearchActivity.class);
                intent.putExtra(getResources().getString(R.string.activity_name), DropOffActivity.class.getSimpleName());
                startActivity(intent);
                break;

        }
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override public void afterTextChanged(Editable s) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
