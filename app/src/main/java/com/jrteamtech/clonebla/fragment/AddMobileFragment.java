package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jrteamtech.clonebla.R;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AddMobileFragment extends Fragment implements View.OnClickListener {
    private ArrayAdapter<String> regionAdapter;
    private String[] regionItem = {"India (+91)", "Indonesia (+62)"};
    private MaterialSpinner regionSpinner;
    private TextView tvVerify;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_add_mobile, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tvVerify = (TextView) view.findViewById(R.id.tv_verify);
        this.regionSpinner = (MaterialSpinner) view.findViewById(R.id.spinner_region);
        this.regionAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.regionItem);
        this.regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.regionSpinner.setAdapter((SpinnerAdapter) this.regionAdapter);
        this.tvVerify.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_verify) {
            openVerifyMobileFragment();
        }
    }

    private void openVerifyMobileFragment() {
        VerifyMobileFragment verifyMobileFragment = new VerifyMobileFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frame_container, verifyMobileFragment);
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
    }
}
