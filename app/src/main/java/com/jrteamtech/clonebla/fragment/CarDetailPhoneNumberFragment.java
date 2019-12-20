package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.model.CarInfoModel;

public class CarDetailPhoneNumberFragment extends Fragment {

    private Toolbar toolbar;
    private EditText numberplate;

    private Button continuebtn;
    private TextView skip;
    private Spinner countryname;
    private String[] countries = {};

    ArrayAdapter adaptercountryname;
    CarInfoModel carInfoModel;
    String edit_flag;
    ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        carInfoModel = (CarInfoModel) getArguments().getSerializable("car_info");
        edit_flag = getArguments().getString("edit_flag");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_detail_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        toolbar = view.findViewById(R.id.toolbar);
        continuebtn = view.findViewById(R.id.continuebtn);
        skip = view.findViewById(R.id.skip);
        numberplate = view.findViewById(R.id.numberplate);
        numberplate.requestFocus();
        this.countryname = view.findViewById(R.id.countryname);

        countries = getResources().getStringArray(R.array.countries);

        setToolbar();
        setAdapter();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarDetailChooseModelFragment modelFragment = new CarDetailChooseModelFragment();
                Bundle b = new Bundle();
                b.putSerializable("car_info", carInfoModel);
                b.putString("flag", "make");
                b.putString("edit_flag", edit_flag);
                modelFragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_car_detail_frame, modelFragment)
                        .commit();
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberplate.getText().toString().length()>0)
                {

//                    startActivity(new Intent(AddCarActivity.this,CarDetailsActivity.class));
                }
            }
        });
    }

    private void setToolbar(){
        ((AddCarActivity)getActivity()).setSupportActionBar(this.toolbar);
        actionBar = ((AddCarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Your car details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setAdapter(){
        this.adaptercountryname = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, this.countries);
        this.adaptercountryname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryname.setAdapter(this.adaptercountryname);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
