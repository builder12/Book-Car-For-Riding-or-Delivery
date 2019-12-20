package com.jrteamtech.clonebla.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.utility.Global;
import com.jrteamtech.clonebla.R;

public class EmailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView multi;
    private EditText  emailaddress;
    private Button    createalert;

    private String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private TextView emailvalidationchecking,datetime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailsending);

        multi = (ImageView) findViewById(R.id.leftnavemail);
        emailaddress = (EditText) findViewById(R.id.tv_email);
        createalert = (Button) findViewById(R.id.tv_createalert);
        emailvalidationchecking = (TextView) findViewById(R.id.email_validation_checking);
        datetime = (TextView)findViewById(R.id.datetime);
        emailaddress.requestFocus();

        multi.setOnClickListener(this);
        createalert.setOnClickListener(this);

        emailaddress.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EmailValidation(charSequence.toString());
            }
        });

        Datetime();

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.leftnavemail){
            finish();
        }else if(id == R.id.tv_createalert){

           if( emailaddress.getText().toString().trim().matches(emailPattern) ){
               Toast.makeText(getApplicationContext(),"okay",Toast.LENGTH_LONG).show();
               emailvalidationchecking.setVisibility(View.INVISIBLE);
           } else {
               emailvalidationchecking.setVisibility(View.VISIBLE);
       //        emailaddress.setBackground();
               emailaddress.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
           }



        }
    }

    private void EmailValidation(String value){
        if(value.length() == 0){
            createalert.setVisibility(View.GONE);
        } else {
            createalert.setVisibility(View.VISIBLE);
        }
    }

    private void Datetime(){
        if (Global.getSelected_time() != null){
            //   Calendardate.setText("ddd");
            datetime.setText(Global.getSelected_time());
        }
    }
}
