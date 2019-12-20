package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.fragment.CurrentFragment;
import com.jrteamtech.clonebla.fragment.RidesHomeFragment;
import com.jrteamtech.clonebla.fragment.InboxFragment;
import com.jrteamtech.clonebla.fragment.LoginFragment;
import com.jrteamtech.clonebla.fragment.ProfileFragment;
import com.jrteamtech.clonebla.fragment.RidesFragment;
import com.jrteamtech.clonebla.fragment.SearchFragment;
import com.jrteamtech.clonebla.fragment.SignUpFragment;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameContainer;
    String logIn;
    String signUp;
    private Toolbar toolbar;
    public static Menu bottom_menu;

    public HomeActivity() {
        String str = "";
        this.signUp = str;
        this.logIn = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_home);
        setToolbar();
        initID();
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        this.signUp = getIntent().getStringExtra("signUp");
        this.logIn = getIntent().getStringExtra("login");

        launchMainFragment();
    }

    private void initID() {
        this.frameContainer = (FrameLayout) findViewById(R.id.frame_container);
        this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_menu = this.bottomNavigationView.getMenu();
    }

    private void setToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
    }

    private void openLoginFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.label_login));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().show();
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, loginFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openSignUpFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.label_signUp));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().show();
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, signUpFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openRideFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.label_your_rides));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitleTextColor(Color.WHITE);
        RidesHomeFragment ridesHomeFragment = new RidesHomeFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, ridesHomeFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openCurrentFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.label_your_rides));
        CurrentFragment currentFragment = new CurrentFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, currentFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openSearchFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, searchFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openInboxFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.action_inbox));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().show();
        InboxFragment inboxFragment = new InboxFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, inboxFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void openProfileFragment() {
        getSupportActionBar().setTitle((CharSequence) getString(R.string.action_profile));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().show();
        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.replace(R.id.frame_container, profileFragment);
        supportFragmentManager.popBackStack((String) null, 1);
        beginTransaction.commit();
    }

    private void launchMainFragment() {
        if (this.signUp.equals("signUp")) {
            openSignUpFragment();
        } else if (this.signUp.equals("login")) {
            openLoginFragment();
        } else {
            openCurrentFragment();
        }
        emptyID();
    }

    private void emptyID() {
        String str = "";
        this.signUp = str;
        this.logIn = str;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.action_inbox) {
            switch (itemId) {
                case R.id.action_offer /*2131230780*/:
//                    getSupportActionBar().show();
//                    openLoginFragment();
//                    emptyID();
                    startActivity(new Intent(HomeActivity.this, PickUpActivity.class));
                    break;
                case R.id.action_profile /*2131230781*/:
                    getSupportActionBar().show();
                    openProfileFragment();
                    emptyID();
                    break;
                case R.id.action_rides /*2131230782*/:
                    getSupportActionBar().show();
                    openRideFragment();
                    break;
                case R.id.action_search /*2131230783*/:
                    getSupportActionBar().hide();
                    openSearchFragment();
                    emptyID();
                    break;
            }
        } else {
            getSupportActionBar().show();
            openInboxFragment();
            emptyID();
        }
        return true;
    }
}
