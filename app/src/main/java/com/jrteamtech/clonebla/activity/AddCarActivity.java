package com.jrteamtech.clonebla.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.model.CarInfoModel;
import com.jrteamtech.clonebla.fragment.CarDetailPhoneNumberFragment;

public class AddCarActivity extends AppCompatActivity {

    private CarInfoModel carInfoModel;
    private String edit_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        if(getIntent() != null && getIntent().getSerializableExtra("car_info") != null){
            carInfoModel = (CarInfoModel) getIntent().getSerializableExtra("car_info");
            edit_flag = getIntent().getStringExtra("edit_flag");
        } else {
            carInfoModel = new CarInfoModel();
            edit_flag = getIntent().getStringExtra("edit_flag");
        }

        CarDetailPhoneNumberFragment phoneNumberFragment = new CarDetailPhoneNumberFragment();
        Bundle b = new Bundle();
        b.putSerializable("car_info", carInfoModel);
        b.putString("edit_flag", edit_flag);
        phoneNumberFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.add_car_detail_frame, phoneNumberFragment)
                .commit();

    }
}
