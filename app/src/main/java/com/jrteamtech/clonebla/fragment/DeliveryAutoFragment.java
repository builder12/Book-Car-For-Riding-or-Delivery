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

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.EditPriceActivity;
import com.jrteamtech.clonebla.activity.FindHomeActivity;
import com.jrteamtech.clonebla.activity.PaymentActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryAutoFragment extends Fragment {

    Button btn_weight1;

    public DeliveryAutoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_weight1 = view.findViewById(R.id.weight_1_btn);

        btn_weight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PaymentActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
    }
}
