package com.jrteamtech.clonebla.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jrteamtech.clonebla.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogIn;
    private Button btnSignUp;



    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        this.btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        this.btnLogIn = (Button) findViewById(R.id.btn_log_in);
        this.btnSignUp.setOnClickListener(this);
        this.btnLogIn.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        String str = "signUp";
        if (id == R.id.btn_log_in) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(str, "login");
            startActivity(intent);
        } else if (id == R.id.btn_sign_up) {
            Intent intent2 = new Intent(this, HomeActivity.class);
            intent2.putExtra(str, str);
            startActivity(intent2);
        }
        initID();
        this.btnSignUp.setOnClickListener(this);
    }

    private void initID() {
        this.btnSignUp = (Button) findViewById(R.id.btn_sign_up);
    }
}
