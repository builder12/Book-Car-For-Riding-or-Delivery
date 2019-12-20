package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.database.DBHelper;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.io.Serializable;

public class CarDetailRegisteredYearFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    ActionBar actionBar;
    private TextView submitBtn;
    private TextInputEditText et_year;

    CarInfoModel carInfoModel;
    String edit_flag;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carInfoModel = (CarInfoModel) getArguments().getSerializable("car_info");
        edit_flag = getArguments().getString("edit_flag");
        dbHelper = new DBHelper(getContext());
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_detail_registered_year, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        submitBtn = view.findViewById(R.id.submit_btn);
        et_year = view.findViewById(R.id.et_year);

        toolbar = view.findViewById(R.id.toolbar);
        setToolbar();

        if(carInfoModel.getYear() != null){
            et_year.setText(carInfoModel.getYear());
        }

        submitBtn.setOnClickListener(this);
    }

    private void setToolbar(){
        ((AddCarActivity)getActivity()).setSupportActionBar(this.toolbar);
        actionBar = ((AddCarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Your car details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_btn:
                if (!et_year.getText().toString().trim().isEmpty()) {
                    carInfoModel.setYear(et_year.getText().toString());
                    if(edit_flag.equals("new")){
                        dbHelper.insertCarInfo(carInfoModel);
                    } else {
                        dbHelper.updateCarInfo(carInfoModel);
                    }
                    ((AddCarActivity)getContext()).finish();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            CarDetailChooseColorFragment colorFragment = new CarDetailChooseColorFragment();
            Bundle b = new Bundle();
            b.putSerializable("car_info", (Serializable) carInfoModel);
            b.putString("edit_flag", edit_flag);
            colorFragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_car_detail_frame, colorFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
