package com.jrteamtech.clonebla.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarDetailChooseTypeFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    ActionBar actionBar;
    private ListView car_type_listview;
    private TextView continueBtn;
    private List<CarType> carTypes = new ArrayList<>();
    private int[] car_img_resources = {R.drawable.car_type_1, R.drawable.car_type_2, R.drawable.car_type_3, R.drawable.car_type_4, R.drawable.car_type_5, R.drawable.car_type_6, R.drawable.car_type_6,
                    R.drawable.car_type_8};
    private String[] car_img_lables = {"Hatchback", "Saloon", "Convertible", "Estate", "SUV", "Station wagon", "Minivan", "Van"};

    CarInfoModel carInfoModel;
    String edit_flag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carInfoModel = (CarInfoModel) getArguments().getSerializable("car_info");
        edit_flag = getArguments().getString("edit_flag");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_detail_choose_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        car_type_listview = view.findViewById(R.id.car_type_listview);
        continueBtn = view.findViewById(R.id.continue_btn);

        toolbar = view.findViewById(R.id.toolbar);
        setToolbar();

        continueBtn.setOnClickListener(this);

        if(carInfoModel.getType() != null && !carInfoModel.getType().trim().isEmpty()){
            continueBtn.setVisibility(View.VISIBLE);
        } else {
            continueBtn.setVisibility(View.GONE);
        }

        for (int i = 0; i < 8; i++){
            carTypes.add(new CarType(car_img_resources[i], car_img_lables[i]));
        }



        CarTypeListAdapter adapter = new CarTypeListAdapter(getContext(), 0, carTypes);
        car_type_listview.setAdapter(adapter);
    }

    private void setToolbar(){
        ((AddCarActivity)getActivity()).setSupportActionBar(this.toolbar);
        actionBar = ((AddCarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Your car details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    class CarTypeListAdapter extends ArrayAdapter<CarType> {

        private Context mContext;

        CarTypeListAdapter(Context context, int resource, List<CarType> data) {
            super(context, resource, data);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.car_type_item, parent, false);
            }

            CarType type = getItem(position);
            ImageView car_type_img = convertView.findViewById(R.id.car_type_img);
            TextView car_type_label = convertView.findViewById(R.id.car_type_label);
            RadioButton car_type_check_btn = convertView.findViewById(R.id.car_type_check_btn);

            assert type != null;
            car_type_img.setImageResource(type.image_resource);
            car_type_label.setText(type.type);

            if(carInfoModel.getType() != null && carInfoModel.getType().equals(type.type)){
                car_type_check_btn.setChecked(true);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!car_type_check_btn.isChecked()){
                        car_type_check_btn.setChecked(true);
                        carInfoModel.setModel(type.type);
                        carInfoModel.setImage(type.image_resource);
                        CarDetailChooseColorFragment colorFragment = new CarDetailChooseColorFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("car_info", carInfoModel);
                        b.putString("edit_flag", edit_flag);
                        colorFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.add_car_detail_frame, colorFragment)
                                .commit();
                    }
                }
            });

            car_type_check_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        carInfoModel.setType(type.type);
                        carInfoModel.setImage(type.image_resource);
                        CarDetailChooseColorFragment colorFragment = new CarDetailChooseColorFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("car_info", carInfoModel);
                        b.putString("edit_flag", edit_flag);
                        colorFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.add_car_detail_frame, colorFragment)
                                .commit();
                    }
                }
            });

            return convertView;
        }
    }

    class CarType {
        int image_resource;
        String type;

        CarType(int image_resource, String type) {
            this.image_resource = image_resource;
            this.type = type;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_btn:
                CarDetailChooseColorFragment colorFragment = new CarDetailChooseColorFragment();
                Bundle b2 = new Bundle();
                b2.putSerializable("car_info", (Serializable) carInfoModel);
                b2.putString("edit_flag", edit_flag);
                colorFragment.setArguments(b2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_car_detail_frame, colorFragment)
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            CarDetailChooseModelFragment chooseModelFragment = new CarDetailChooseModelFragment();
            Bundle b1 = new Bundle();
            b1.putSerializable("car_info", (Serializable) carInfoModel);
            b1.putString("flag", "model");
            b1.putString("edit_flag", edit_flag);
            chooseModelFragment.setArguments(b1);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_car_detail_frame, chooseModelFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
