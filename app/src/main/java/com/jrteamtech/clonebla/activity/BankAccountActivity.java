package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class BankAccountActivity extends AppCompatActivity {

    private Spinner choosecountry;
    private EditText accountholder,bankname,sortcode,accountnumber;
    private ArrayAdapter adapterchoosecountry;

    private Toolbar toolbar;
    private String[] choosecountryitem = {"Denmark","Lithuania","Luxembourg","Sweden","Republic of Ireland","United Kingdom","Saint Pierre and Miqulon","Latvia","Guadeloupe","Guinea","Slovakia","Germany","France","Martinique","Norway","Slovenia","Cyprus","Hungary","Monaco","Netherlands","San Marino","Poland","Finland","Bulgaria","Spain","Switzerland","Reunion","Liechtenstein","Romania","saint Martin","Mayotte","Iceland","Malta","Belgium","Gibraltar","Italy","Greece","Portugal","Austria","Saint Barthelemy"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);
        this.choosecountry = (Spinner)findViewById(R.id.choosecountry);
        setToolbar();
        setAdapter();
    }
    private void setAdapter(){
        this.adapterchoosecountry = new ArrayAdapter<>(BankAccountActivity.this, android.R.layout.simple_list_item_1, this.choosecountryitem);
        this.adapterchoosecountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosecountry.setAdapter(this.adapterchoosecountry);
    }

    private void setToolbar(){
        this.toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Bank account");
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
