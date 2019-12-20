package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class PrivatePolicyActivity extends AppCompatActivity {
    private  WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_policy);

        webView = findViewById(R.id.private_policy_webview);
        webView.loadUrl("https://blog.blablacar.co.uk/blog/privacy-and-data-protection-policy");

    }
}
