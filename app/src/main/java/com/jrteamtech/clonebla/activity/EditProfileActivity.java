package com.jrteamtech.clonebla.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.jrteamtech.clonebla.R;

public class EditProfileActivity extends AppCompatActivity {

    private TextView chooseprofile ;
    private ImageView choosephoto;
    private Button  saveprofile, saveemail,savemobile;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private Spinner spinneryearofbirth;
    private String[] spinneryearofbirthitem={ "2010", "2009" ,"2008", "2007", "2006", "2005", "2004" ,"2003" ,"2002" ,"2001", "2000", "1999", "1998", "1997",
            "1996", "1995" ,"1994", "1993", "1992", "1991", "1990" ,"1989" ,"1988" ,"1987", "1986", "1985", "1984", "1983",
            "1982", "1981" ,"1980", "1979", "1978", "1977", "1976" ,"1975" ,"1974" ,"1973", "1972", "1971", "1970", "1969",
            "1968", "1967" ,"1966", "1965", "1964", "1963", "1962" ,"1961" ,"1960" ,"1959", "1958", "1957", "1956", "1955",
            "1954", "1953" ,"1952", "1951", "1950","1949","1948","1947","1946","1945","1944","1943","1942","1941","1940","1939",
       "1938","1937","1936","1935","1934","1933","1932","1931","1930"};
    private ArrayAdapter<String> yearadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        chooseprofile = (TextView)findViewById(R.id.profiletxt);
        choosephoto = (ImageView)findViewById(R.id.profileimg);
        saveprofile = (Button)findViewById(R.id.save_profile);
        saveemail = (Button)findViewById(R.id.save_email);
        savemobile = (Button)findViewById(R.id.save_mobile);
      this.spinneryearofbirth =(Spinner)findViewById(R.id.yearofbirth);

        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ChooseProfilePhotoActivity.class);
                startActivity(intent);
            }
        });


        chooseprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ChooseProfilePhotoActivity.class);
                startActivity(intent);
            }
        });

        setyear();
        setToolbar();
        showdialog();

    }

    private void showdialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 800);
    }


    private void setyear(){
        this.yearadapter = new ArrayAdapter<>(EditProfileActivity.this, android.R.layout.simple_list_item_1, this.spinneryearofbirthitem);
        this.yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryearofbirth.setAdapter(this.yearadapter);
    }




    private void setToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Edit profile");
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
