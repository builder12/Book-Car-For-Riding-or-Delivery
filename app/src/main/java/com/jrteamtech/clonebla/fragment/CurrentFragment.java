package com.jrteamtech.clonebla.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.HomeActivity;
import com.jrteamtech.clonebla.activity.PickUpActivity;
import com.jrteamtech.clonebla.activity.SearchActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment implements View.OnClickListener {
    Button btnOfferRide,btnFindRide;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_current, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.btnOfferRide = (Button) view.findViewById(R.id.btn_offer_ride);
        this.btnOfferRide.setOnClickListener(this);

        this.btnFindRide =  (Button) view.findViewById(R.id.btn_find_ride);
        this.btnFindRide.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_offer_ride) {
            startActivity(new Intent(getContext(), PickUpActivity.class));
        } else if (view.getId() == R.id.btn_find_ride) {
         //   startActivity(new Intent(getContext(), SearchActivity.class));

//            SearchFragment searchFragment = new SearchFragment();
//            FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
//            beginTransaction.replace(R.id.frame_container, searchFragment);
//            supportFragmentManager.popBackStack((String) null, 1);
//            beginTransaction.commit();
            ((HomeActivity)getActivity()).onNavigationItemSelected(HomeActivity.bottom_menu.findItem(R.id.action_search));
            HomeActivity.bottom_menu.findItem(R.id.action_search).setChecked(true);
        }
    }
}
