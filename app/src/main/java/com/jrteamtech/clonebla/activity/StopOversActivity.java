package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class StopOversActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back_arrow_btn;
    EditText et_add_stopovers;
    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopovers);

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        et_add_stopovers = findViewById(R.id.et_add_stopovers);
        confirm_btn = findViewById(R.id.confirm_btn);

        back_arrow_btn.setOnClickListener(this);
        et_add_stopovers.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.et_add_stopovers:
                startActivity(new Intent(StopOversActivity.this, AddStopOverActivity.class));
                break;
            case R.id.confirm_btn:
                Intent intent = new Intent(StopOversActivity.this, ChooseDateActivity.class);
                intent.putExtra(getResources().getString(R.string.activity_name), StopOversActivity.class.getSimpleName());
                startActivity(intent);
                break;
        }
    }
}
