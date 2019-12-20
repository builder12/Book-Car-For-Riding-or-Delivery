package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class PushNotificationsActivity extends AppCompatActivity {

    private ImageButton backbtn;
    private ImageView yourridestick,newstick,messagestick,ratingstick;
    private ImageButton uncheck_btn, check_btn,uncheck_btn1,check_btn1,uncheck_btn2,check_btn2,uncheck_btn3,check_btn3;
    private ProgressBar progressBar,progressBar1,progressBar2,progressBar3;

    private boolean isChecked = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notifications);


        backbtn = findViewById(R.id.beforebtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        backbtn = findViewById(R.id.beforebtn);
//        yourridestick = findViewById(R.id.yourrides);


        uncheck_btn = findViewById(R.id.uncheck_btn);
        check_btn = findViewById(R.id.check_btn);

        uncheck_btn1 = findViewById(R.id.uncheck_btn1);
        check_btn1 = findViewById(R.id.check_btn1);

        uncheck_btn2 = findViewById(R.id.uncheck_btn2);
        check_btn2 = findViewById(R.id.check_btn2);

        uncheck_btn3 = findViewById(R.id.uncheck_btn3);
        check_btn3 = findViewById(R.id.check_btn3);

        progressBar = findViewById(R.id.progress_indicator);
        progressBar1 = findViewById(R.id.progress_indicator1);
        progressBar2 = findViewById(R.id.progress_indicator2);
        progressBar3 = findViewById(R.id.progress_indicator3);


        if (isChecked) {
            check_btn.setVisibility(View.VISIBLE);
            uncheck_btn.setVisibility(View.GONE);
        } else {
            uncheck_btn.setVisibility(View.VISIBLE);
            check_btn.setVisibility(View.GONE);
        }

        uncheck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                uncheck_btn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        check_btn.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = false;
                check_btn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        uncheck_btn.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });


        if (isChecked) {
            check_btn1.setVisibility(View.VISIBLE);
            uncheck_btn1.setVisibility(View.GONE);
        } else {
            uncheck_btn1.setVisibility(View.VISIBLE);
            check_btn1.setVisibility(View.GONE);
        }

        uncheck_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                uncheck_btn1.setVisibility(View.GONE);
                progressBar1.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar1.setVisibility(View.GONE);
                        check_btn1.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });

        check_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = false;
                check_btn1.setVisibility(View.GONE);
                progressBar1.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar1.setVisibility(View.GONE);
                        uncheck_btn1.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });


        if (isChecked) {
            check_btn2.setVisibility(View.VISIBLE);
            uncheck_btn2.setVisibility(View.GONE);
        } else {
            uncheck_btn2.setVisibility(View.VISIBLE);
            check_btn2.setVisibility(View.GONE);
        }

        uncheck_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                uncheck_btn2.setVisibility(View.GONE);
                progressBar2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar2.setVisibility(View.GONE);
                        check_btn2.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });

        check_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = false;
                check_btn2.setVisibility(View.GONE);
                progressBar2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar2.setVisibility(View.GONE);
                        uncheck_btn2.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });



        if (isChecked) {
            check_btn3.setVisibility(View.VISIBLE);
            uncheck_btn3.setVisibility(View.GONE);
        } else {
            uncheck_btn3.setVisibility(View.VISIBLE);
            check_btn3.setVisibility(View.GONE);
        }

        uncheck_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
                uncheck_btn3.setVisibility(View.GONE);
                progressBar3.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar3.setVisibility(View.GONE);
                        check_btn3.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });

        check_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = false;
                check_btn3.setVisibility(View.GONE);
                progressBar3.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar3.setVisibility(View.GONE);
                        uncheck_btn3.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        });





    }
}
