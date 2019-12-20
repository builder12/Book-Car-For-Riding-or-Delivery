package com.jrteamtech.clonebla.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jrteamtech.clonebla.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyMobileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_mobile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvSubmit = view.findViewById(R.id.tv_verify);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                FragmentTransaction beginTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                beginTransaction.replace(R.id.frame_container, loginFragment);
                beginTransaction.disallowAddToBackStack();
                beginTransaction.commit();
            }
        });
    }
}
