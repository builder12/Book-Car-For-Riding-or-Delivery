package com.jrteamtech.clonebla.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.FindHomeActivity;
import com.jrteamtech.clonebla.activity.OfferHomeActivity;
import com.jrteamtech.clonebla.activity.PickUpActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RidesHomeFragment extends Fragment {

    Button offerBtn;
    Button findBtn;
    public RidesHomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_rides_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.offerBtn = view.findViewById(R.id.btn_offer);
        this.findBtn = view.findViewById(R.id.btn_find);
        this.offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OfferHomeActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        this.findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FindHomeActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
