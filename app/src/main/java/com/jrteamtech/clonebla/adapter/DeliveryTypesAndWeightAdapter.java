package com.jrteamtech.clonebla.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jrteamtech.clonebla.fragment.DeliveryAutoFragment;
import com.jrteamtech.clonebla.fragment.DeliveryCargoFragment;
import com.jrteamtech.clonebla.fragment.DeliveryPlaneFragment;

public class DeliveryTypesAndWeightAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public DeliveryTypesAndWeightAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DeliveryAutoFragment homeFragment = new DeliveryAutoFragment();
                return homeFragment;
            case 1:
                DeliveryCargoFragment cargoFragment = new DeliveryCargoFragment();
                return cargoFragment;
            case 2:
                DeliveryPlaneFragment planeFragment = new DeliveryPlaneFragment();
                return planeFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
