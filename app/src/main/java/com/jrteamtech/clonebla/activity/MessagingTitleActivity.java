package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class MessagingTitleActivity extends AppCompatActivity {

    private ImageButton beforbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_title);

        beforbtn = findViewById(R.id.beforebtn);

        beforbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       startActivity(new Intent(MessagingTitleActivity.this,NotificationCommunicationActivity.class));
               finish();
            }
        });
    }
}
