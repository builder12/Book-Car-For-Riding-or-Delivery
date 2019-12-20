package com.jrteamtech.clonebla.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.jrteamtech.clonebla.R;

public class RatingsLeftActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ProgressDialog progressDialog = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_left);

        setToolbar();
    //    showdialog();
    }

//    private void showdialog(){
//        progressDialog = new ProgressDialog(this);
//      //  progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//
//        Runnable progressRunnable = new Runnable() {
//            @Override
//            public void run() {
//                progressDialog.cancel();
//            }
//        };
//        Handler pdCanceller = new Handler();
//        pdCanceller.postDelayed(progressRunnable, 3000);
//    }



    private void setToolbar(){
        this.toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Edit preferences");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
