package com.jrteamtech.clonebla.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.VerifyPhoneNumberActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyPhoneNumberFragment extends Fragment {

    private Button verifybtn;
    private MaterialSpinner regionnumber;

    private ArrayAdapter adapterregion;

    private String[] regionnumberitem = {
            "Ascension lsland (+247)",
            "United Arab Emirates (+971)",
            "Afghanistan (+93)",
            "Antigua And Barbuda (+car_type_1)",
            "Anguilla (+car_type_1)",
            "Angola(+244)",
            "American Samoa (+car_type_1)",
            "Aruba (+297)",
            "Aland lslands (+358)",
            "Azerbaijan (+994)",
            "Algerie (+213)",
            "Andorra (+376)",
            "Argentina (+54)",
            "Australia (+61)",
            "Barbados (+car_type_1)",
            "Bangladesh (+880)",
            "Burkina Faso(+226)",
            "Bahrain (+973)",
            "Burundi (+257)",
            "Benin (+229)",
            "Bermuda (+car_type_1)",
            "Brunei (+673)",
            "Bolivia (+591)",
            "Caribbean Netherlands (+559)",
            "Bahamas (+car_type_1)",
            "Bhutan (+975)",
            "Bo9tswana (+267)",
            "Belize (+375)",
            "Belgique(+32)",
            "Bosnia / Herzegovina (+387)",
            "Brasil (+55)",
            "Bulgaria (359)",
            "Cocos (keepling lslands (+61))"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regionnumber = view.findViewById(R.id.regionchoose);

        verifybtn = view.findViewById(R.id.phoneverify);

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyMobileFragment mobileFragment = new VerifyMobileFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.verify_phone_number_frame, mobileFragment)
                        .commit();
                ((VerifyPhoneNumberActivity) getActivity()).getSupportActionBar().setTitle("Verify mobile");
            }
        });


        setadapter();
    }

    private void setadapter(){
        adapterregion = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, regionnumberitem);
        adapterregion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionnumber.setAdapter(adapterregion);
    }
}
