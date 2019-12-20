package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.jrteamtech.clonebla.R;

public class PickUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edtLocationSearch;
    private ImageView imgBackArrow;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView( R.layout.activity_pickup);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        initid();
        this.edtLocationSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
//                    Intent intent = new Intent(PickUpActivity.this.getApplicationContext(), SearchActivity.class);
//                    intent.putExtra(String.valueOf(R.string.activity_name), PickUpActivity.class.getSimpleName());
//                    PickUpActivity.this.startActivity(intent);
                }
            }
        });
        this.edtLocationSearch.setOnClickListener(this);
        this.imgBackArrow.setOnClickListener(this);
    }

    private void initid() {
        this.edtLocationSearch = (TextInputEditText) findViewById(R.id.edt_location_search);
        this.imgBackArrow = (ImageView) findViewById(R.id.img_back_arrow);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.edt_location_search) {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra(getResources().getString(R.string.activity_name), PickUpActivity.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.img_back_arrow) {
            finish();
        }
    }
}

