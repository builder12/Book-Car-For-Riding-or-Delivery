package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class TermsConditionsActivity extends AppCompatActivity {

    private WebView webView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        webView = (WebView)findViewById(R.id.termsconditions);
        webView.loadUrl("https://blog.blablacar.co.uk/about-us/terms-and-conditions");
    }
}
