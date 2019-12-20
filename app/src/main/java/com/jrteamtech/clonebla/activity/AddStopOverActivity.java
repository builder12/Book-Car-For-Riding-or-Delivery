package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class AddStopOverActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    ImageButton back_arrow_btn;
    EditText et_search;
    LinearLayout my_location_btn;
    ListView stop_over_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stop_over);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        et_search = findViewById(R.id.et_search);
        my_location_btn = findViewById(R.id.ll_my_location_btn);
        stop_over_listview = findViewById(R.id.stop_over_listview);

        back_arrow_btn.setOnClickListener(this);
        my_location_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.ll_my_location_btn:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
