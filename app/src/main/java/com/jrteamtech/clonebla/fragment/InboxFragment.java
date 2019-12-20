package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.adapter.ViewPagerAdapter;

public class InboxFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_inbox, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        this.viewPager = (ViewPager) view.findViewById(R.id.pager);
        setupViewPager(this.viewPager);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new MessageFragment(), getResources().getString(R.string.label_message));
        viewPagerAdapter.addFragment(new NotificationFragment(), getResources().getString(R.string.label_notification));
        viewPager2.setAdapter(viewPagerAdapter);
    }
}

