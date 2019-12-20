package com.jrteamtech.clonebla.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarDetailChooseColorFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    ActionBar actionBar;
    private ListView car_color_listview;
    private TextView continueBtn;
    private int[] color_resources = {
            R.color.black,
            R.color.white,
            R.color.dark_grey,
            R.color.grey,
            R.color.burgundy,
            R.color.red,
            R.color.dark_blue,
            R.color.blue,
            R.color.dark_green,
            R.color.green,
            R.color.brown,
            R.color.beige,
            R.color.orange,
            R.color.yellow,
            R.color.purple,
            R.color.pink
    };

    private String[] color_labels = {
            "Black", "White", "Dark grey", "Grey", "Burgundy", "Red", "Dark blue", "Blue", "Dark green", "Green", "Brown", "Beige", "Orange", "Yellow", "Purple", "Pink"
    };

    private List<CarColor> colorList = new ArrayList<>();

    CarInfoModel carInfoModel;
    String edit_flag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carInfoModel = (CarInfoModel) getArguments().getSerializable("car_info");
        edit_flag = getArguments().getString("edit_flag");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_detail_choose_color, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        car_color_listview = view.findViewById(R.id.car_color_listview);
        continueBtn = view.findViewById(R.id.continue_btn);

        toolbar = view.findViewById(R.id.toolbar);
        setToolbar();

        if(carInfoModel.getColor_label() != null && !carInfoModel.getColor_label().trim().isEmpty()){
            continueBtn.setVisibility(View.VISIBLE);
        }

        continueBtn.setOnClickListener(this);

        for(int i = 0; i < color_labels.length; i++) {
            colorList.add(new CarColor(color_resources[i], color_labels[i]));
        }
        CarColorListAdapter adapter = new CarColorListAdapter(getContext(), 0, colorList);
        car_color_listview.setAdapter(adapter);

    }

    private void setToolbar(){
        ((AddCarActivity)getActivity()).setSupportActionBar(this.toolbar);
        actionBar = ((AddCarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Your car details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    class CarColorListAdapter extends ArrayAdapter<CarColor> {

        private Context mContext;

        CarColorListAdapter(Context context, int resource, List<CarColor> data) {
            super(context, resource, data);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.car_color_item, parent, false);
            }

            CarColor color = getItem(position);
            TextView car_color_view = convertView.findViewById(R.id.car_color_view);
            TextView car_color_label = convertView.findViewById(R.id.car_color_label);
            RadioButton car_color_check_btn = convertView.findViewById(R.id.car_color_check_btn);

            assert color != null;

            Drawable drawable = car_color_view.getBackground();
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, mContext.getResources().getColor(color.color_resource));
            car_color_view.setBackgroundDrawable(drawable);

            car_color_label.setText(color.color_label);

            if(carInfoModel.getColor_label() != null && carInfoModel.getColor_label().equals(color.color_label)){
                car_color_check_btn.setChecked(true);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!car_color_check_btn.isChecked()){
                        car_color_check_btn.setChecked(true);
                        carInfoModel.setColor_label(color.color_label);
                        carInfoModel.setColor_value(color.color_resource);
                        CarDetailRegisteredYearFragment registeredYearFragment = new CarDetailRegisteredYearFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("car_info", (Serializable) carInfoModel);
                        b.putString("edit_flag", edit_flag);
                        registeredYearFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.add_car_detail_frame, registeredYearFragment)
                                .commit();
                    }
                }
            });

            car_color_check_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        carInfoModel.setColor_label(color.color_label);
                        carInfoModel.setColor_value(color.color_resource);
                        CarDetailRegisteredYearFragment registeredYearFragment = new CarDetailRegisteredYearFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("car_info", (Serializable) carInfoModel);
                        b.putString("edit_flag", edit_flag);
                        registeredYearFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.add_car_detail_frame, registeredYearFragment)
                                .commit();
                    }
                }
            });

            return convertView;
        }
    }

    class CarColor {
        int color_resource;
        String color_label;

        CarColor(int color_resource, String color_label) {
            this.color_resource = color_resource;
            this.color_label = color_label;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_btn:
                CarDetailRegisteredYearFragment registeredYearFragment = new CarDetailRegisteredYearFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("car_info", (Serializable) carInfoModel);
                bundle.putString("edit_flag", edit_flag);
                registeredYearFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_car_detail_frame, registeredYearFragment)
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            CarDetailChooseTypeFragment chooseTypeFragment = new CarDetailChooseTypeFragment();
            Bundle b = new Bundle();
            b.putSerializable("car_info", (Serializable) carInfoModel);
            b.putString("edit_flag", edit_flag);
            chooseTypeFragment.setArguments(b);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_car_detail_frame, chooseTypeFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
