package com.jrteamtech.clonebla.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarDetailChooseModelFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    ActionBar actionBar;
    private TextView question_view, popular_title_view;
    private ListView popular_listview;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private LinearLayout car_model_list_layout;
    private TextView continueBtn;

    private List<String> popular_makes = new ArrayList<>();
    private List<String> volk_models = new ArrayList<>();
    private List<String> ford_models = new ArrayList<>();
    private List<String> bmw_models = new ArrayList<>();
    private List<String> vaux_models = new ArrayList<>();
    private List<String> audi_models = new ArrayList<>();
    private List<List<String>> models = new ArrayList<>();

    private String flag;

    CarInfoModel carInfoModel;
    String edit_flag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carInfoModel = (CarInfoModel) getArguments().getSerializable("car_info");
        edit_flag = getArguments().getString("edit_flag");
        flag = getArguments().getString("flag");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_detail_choose_model, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        question_view = view.findViewById(R.id.question_view);
        popular_title_view = view.findViewById(R.id.popular_title_view);
        textInputLayout = view.findViewById(R.id.input_layout);
        textInputEditText = view.findViewById(R.id.et_search);
        car_model_list_layout = view.findViewById(R.id.car_model_list_layout);
        continueBtn = view.findViewById(R.id.continue_btn);

        toolbar = view.findViewById(R.id.toolbar);
        setToolbar();

        textInputLayout.setOnClickListener(this);
        continueBtn.setOnClickListener(this);

        popular_makes = Arrays.asList(getResources().getStringArray(R.array.popular_makes));
        volk_models = Arrays.asList(getResources().getStringArray(R.array.VOLKSWAGEN));
        ford_models = Arrays.asList(getResources().getStringArray(R.array.FORD));
        bmw_models = Arrays.asList(getResources().getStringArray(R.array.BMW));
        vaux_models = Arrays.asList(getResources().getStringArray(R.array.VAUXHALL));
        audi_models = Arrays.asList(getResources().getStringArray(R.array.AUDI));

        models.add(volk_models);
        models.add(ford_models);
        models.add(bmw_models);
        models.add(vaux_models);
        models.add(audi_models);

        if(flag.equals("model")){
            question_view.setText("What model?");
            if(carInfoModel.getModel() == null) {
                popular_title_view.setText("Popular models");
                car_model_list_layout.setVisibility(View.VISIBLE);
                continueBtn.setVisibility(View.GONE);
                textInputEditText.setText("");
            } else {
                car_model_list_layout.setVisibility(View.GONE);
                continueBtn.setVisibility(View.VISIBLE);
                textInputEditText.setText(carInfoModel.getModel());
            }
        } else {
            question_view.setText("What make is your car?");
            if(carInfoModel.getMake() == null) {
                popular_title_view.setText("Popular makes");
                car_model_list_layout.setVisibility(View.VISIBLE);
                continueBtn.setVisibility(View.GONE);
                textInputEditText.setText("");
            } else {
                car_model_list_layout.setVisibility(View.GONE);
                continueBtn.setVisibility(View.VISIBLE);
                textInputEditText.setText(carInfoModel.getMake());
            }
        }

        popular_listview = view.findViewById(R.id.popular_listview);
        PopularListAdapter adapter = new PopularListAdapter(getContext(), 0, popular_makes);
        popular_listview.setAdapter(adapter);
    }

    private void setToolbar(){
        ((AddCarActivity)getActivity()).setSupportActionBar(this.toolbar);
        actionBar = ((AddCarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Your car details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    class PopularListAdapter extends ArrayAdapter<String> {

        private Context mContext;

        PopularListAdapter(Context context, int res, List<String> data) {
            super(context, res, data);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.popular_list_item, null);
            }

            String item = getItem(position);
            TextView item_view = convertView.findViewById(R.id.popular_item_view);
            item_view.setText(item);

            item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag.equals("model")){
                        carInfoModel.setModel(item);
                        CarDetailChooseTypeFragment typeFragment = new CarDetailChooseTypeFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("car_info", (Serializable) carInfoModel);
                        b.putString("edit_flag", edit_flag);
                        typeFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.add_car_detail_frame, typeFragment)
                                .commit();
                    } else {
                        flag = "model";
                        question_view.setText("What model?");
                        popular_title_view.setText("Popular models");
                        List<String> model = models.get(position);
                        PopularListAdapter adapter = new PopularListAdapter(mContext, 0, model);
                        popular_listview.setAdapter(adapter);
                        notifyDataSetChanged();
                        carInfoModel.setMake(item);
                    }
                }
            });

            return convertView;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_btn:
                if(flag.equals("model")){
                    CarDetailChooseTypeFragment typeFragment = new CarDetailChooseTypeFragment();
                    Bundle b = new Bundle();
                    b.putSerializable("car_info", (Serializable) carInfoModel);
                    b.putString("edit_flag", edit_flag);
                    typeFragment.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.add_car_detail_frame, typeFragment)
                            .commit();
                } else {
                    flag = "model";
                    question_view.setText("What model?");
                    popular_title_view.setText("Popular models");
//                    if(carInfoModel.getModel() == null){
                    textInputEditText.setText(carInfoModel.getModel());
                    if(carInfoModel.getModel() == null){
                        car_model_list_layout.setVisibility(View.VISIBLE);
                        continueBtn.setVisibility(View.GONE);
                    } else {
                        car_model_list_layout.setVisibility(View.GONE);
                        continueBtn.setVisibility(View.VISIBLE);
                    }
//                    } else {
//                        car_model_list_layout.setVisibility(View.VISIBLE);
//                        continueBtn.setVisibility(View.GONE);
//                        textInputEditText.setText("");
//                        List<String> model = models.get(position);
//                        PopularListAdapter adapter = new PopularListAdapter(mContext, 0, model);
//                        popular_listview.setAdapter(adapter);
//                        carInfoModel.setMake(item);
//                    }
                }
                break;
            case R.id.input_layout:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(flag.equals("model")){
                flag = "make";
                question_view.setText("What make is your car?");
                popular_title_view.setText("Popular makes");
                car_model_list_layout.setVisibility(View.GONE);
                continueBtn.setVisibility(View.VISIBLE);
                textInputEditText.setText(carInfoModel.getMake());
            } else {
                CarDetailPhoneNumberFragment phoneNumberFragment = new CarDetailPhoneNumberFragment();
                Bundle b = new Bundle();
                b.putSerializable("car_info", (Serializable) carInfoModel);
                b.putString("edit_flag", edit_flag);
                phoneNumberFragment.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_car_detail_frame, phoneNumberFragment)
                        .commit();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}