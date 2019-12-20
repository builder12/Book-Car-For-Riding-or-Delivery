package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;

    private WebView webView;



    private RelativeLayout relativeLayout;


    TextView howtoworks,faq,insurance,contacusus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        howtoworks = findViewById(R.id.howitwokrs);
        faq = findViewById(R.id.faq);
        insurance = findViewById(R.id.insurance);
        contacusus = findViewById(R.id.contactus);

        this.howtoworks.setOnClickListener(this);
        this.faq.setOnClickListener(this);
        this.insurance.setOnClickListener(this);
        this.contacusus.setOnClickListener(this);

        setToolbar();
    }

    private void setToolbar(){
        this.toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Help");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.howitwokrs :
                howitworks();
                return;
            case R.id.faq  :
                faq();
                return;
            case R.id.insurance :
                insurance();
                return;
            case R.id.contactus :
                contacusus();
                return;

             default: return;
        }

    }

    private void howitworks(){
        Intent intent = new Intent(HelpActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://m.blablacar.co.uk/how-does-car-sharing-work");
        startActivity(intent);

    }
    private void faq(){
        Intent intent = new Intent(HelpActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://m.blablacar.co.uk/faq");
        startActivity(intent);
    }
    private void insurance(){
        Intent intent = new Intent(HelpActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://m.blablacar.co.uk/insurance-carpooling");
        startActivity(intent);
    }
    private void contacusus(){

        Intent intent = new Intent(HelpActivity.this, WebViewActivity.class);
        intent.putExtra("url", "https://m.blablacar.co.uk/contact");
        startActivity(intent);
    }
}
