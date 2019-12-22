package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.jrteamtech.clonebla.R;

public class PickUpActivity extends AppCompatActivity{
    private TextInputEditText edtLocationSearch;
    private ImageView imgBackArrow;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView( R.layout.activity_pickup);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        this.edtLocationSearch = (TextInputEditText) findViewById(R.id.edt_location_search);
        this.imgBackArrow = (ImageView) findViewById(R.id.img_back_arrow);


        this.imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.edtLocationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickUpActivity.this, SearchLocationActivity.class));
            }
        });
    }
}

