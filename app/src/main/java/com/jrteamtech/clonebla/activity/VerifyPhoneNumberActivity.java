package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.fragment.VerifyPhoneNumberFragment;

import fr.ganfra.materialspinner.MaterialSpinner;

public class VerifyPhoneNumberActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout verify_phone_number_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        verify_phone_number_frame = findViewById(R.id.verify_phone_number_frame);

        VerifyPhoneNumberFragment phoneNumberFragment = new VerifyPhoneNumberFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.verify_phone_number_frame, phoneNumberFragment)
                .commit();

        setuptoolbar();
    }


    private void setuptoolbar(){
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(this.toolbar);
            getSupportActionBar().setTitle("Verify phone number");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                if(getSupportActionBar().getTitle().equals("Verify phone number")){
                    finish();
                } else {
                    getSupportActionBar().setTitle("Verify phone number");
                    VerifyPhoneNumberFragment phoneNumberFragment = new VerifyPhoneNumberFragment();

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.verify_phone_number_frame, phoneNumberFragment)
                            .commit();
                }

                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}

