package com.jrteamtech.clonebla.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.AddCarActivity;
import com.jrteamtech.clonebla.activity.AddPreferenceActivity;
import com.jrteamtech.clonebla.activity.ChooseCarPhotoActivity;
import com.jrteamtech.clonebla.activity.EditProfileActivity;
import com.jrteamtech.clonebla.activity.SeePublicProfileActivity;
import com.jrteamtech.clonebla.activity.VerifyMyIdActivity;
import com.jrteamtech.clonebla.activity.VerifyPhoneNumberActivity;
import com.jrteamtech.clonebla.adapter.CarInfoAdapter;
import com.jrteamtech.clonebla.database.DBHelper;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment implements View.OnClickListener {
    private ImageView imageVerification;
    private TextView tvAddCar;
    private Button btnAddCar;
    private TextView tvAddPhone;
    private TextView tvAddPreference;
    private TextView tvMiniBio;
    private TextView tvSeePublicProfile;
    private TextView tvVerifyEmail;
    private TextView tvVerifyID;
    private RecyclerView rvCarInfoRecyclerView;
    private CarInfoAdapter carInfoAdapter;

    private LinearLayout preferences_view;

    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;
    private List<CarInfoModel> carInfoModelList = new ArrayList<>();

    private boolean isExistCarPreferences = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
        sharedPreferences = getContext().getSharedPreferences("CarPrefs", Context.MODE_PRIVATE);
        dbHelper = new DBHelper(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_detail, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);

        this.preferences_view = view.findViewById(R.id.preferences_view);
        this.tvMiniBio = view.findViewById(R.id.tv_mini_bio);
        this.tvAddPreference = view.findViewById(R.id.tv_add_preference);
        this.tvVerifyID = view.findViewById(R.id.tv_verify_id);
        this.tvAddPhone = view.findViewById(R.id.tv_add_phone);
        this.tvVerifyEmail = view.findViewById(R.id.tv_verify_email);
        this.tvAddCar = view.findViewById(R.id.tv_add_car);
        this.btnAddCar = view.findViewById(R.id.add_car_btn);
        this.tvSeePublicProfile = view.findViewById(R.id.tv_see_public_profile);
        this.imageVerification = view.findViewById(R.id.image_verification);
        this.rvCarInfoRecyclerView = view.findViewById(R.id.car_info_recyclerview);

        if(sharedPreferences.getBoolean("isExistCarPreferences", false)){
            isExistCarPreferences = true;
        } else {
            isExistCarPreferences = false;
        }

        this.tvMiniBio.setOnClickListener(this);
        this.tvAddPreference.setOnClickListener(this);
        this.preferences_view.setOnClickListener(this);
        this.tvVerifyID.setOnClickListener(this);
        this.tvAddPhone.setOnClickListener(this);
        this.tvVerifyEmail.setOnClickListener(this);
        this.tvAddCar.setOnClickListener(this);
        this.btnAddCar.setOnClickListener(this);
        this.tvSeePublicProfile.setOnClickListener(this);
        this.imageVerification.setOnClickListener(this);

        showOrHidePreferences();
    }

    private void showCarInfoList() {
        carInfoModelList = dbHelper.getAllCarInfos();
        if(carInfoModelList.size() == 0){
            this.tvAddCar.setVisibility(View.VISIBLE);
            this.btnAddCar.setVisibility(View.GONE);
            this.rvCarInfoRecyclerView.setVisibility(View.GONE);
        } else {
            this.tvAddCar.setVisibility(View.GONE);
            this.btnAddCar.setVisibility(View.VISIBLE);
            this.rvCarInfoRecyclerView.setVisibility(View.VISIBLE);
            this.carInfoAdapter = new CarInfoAdapter(getContext(), carInfoModelList);
            this.carInfoAdapter.setItemClickListener(new CarInfoAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getContext(), AddCarActivity.class);
                    intent.putExtra("car_info", (Serializable) carInfoModelList.get(position));
                    intent.putExtra("edit_flag", "edit");
                    startActivity(intent);
                }
            });

            this.carInfoAdapter.setItemMenuClickListener(new CarInfoAdapter.ItemMenuClickListener() {
                @Override
                public void onItemMenuClick(View view, int position, String type) {
                    if(type.equals(carInfoAdapter.CHANGE_PHOTO)){
                        startActivity(new Intent(getContext(), ChooseCarPhotoActivity.class));
                    } else if(type.equals(carInfoAdapter.EDIT)){
                        Intent intent = new Intent(getContext(), AddCarActivity.class);
                        intent.putExtra("car_info", (Serializable) carInfoModelList.get(position));
                        intent.putExtra("edit_flag", "edit");
                        startActivity(intent);
                    } else {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Delete this car")
                                .setMessage("Are you sure you want to delete this car?")
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dbHelper.deleteCarInfo(carInfoModelList.get(position));
                                        carInfoModelList.remove(position);
                                        carInfoAdapter.notifyItemChanged(position);
                                        if(carInfoModelList.size() == 0){
                                            tvAddCar.setVisibility(View.VISIBLE);
                                            btnAddCar.setVisibility(View.GONE);
                                            rvCarInfoRecyclerView.setVisibility(View.GONE);
                                        }
                                    }
                                })
                                .show();
                    }
                }
            });

            this.rvCarInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            this.rvCarInfoRecyclerView.setAdapter(this.carInfoAdapter);
            this.carInfoAdapter.notifyDataSetChanged();
        }
    }

    private void showOrHidePreferences() {
        if(isExistCarPreferences){
            preferences_view.setVisibility(View.VISIBLE);
            tvAddPreference.setVisibility(View.GONE);
            if(preferences_view.getChildCount() > 0) {
                preferences_view.removeAllViews();
            }

            int chat_resource = sharedPreferences.getInt("chat_resource", 0);
            int smoking_resource = sharedPreferences.getInt("smoking_resource", 0);
            int music_resource = sharedPreferences.getInt("music_resource", 0);
            int pet_resource = sharedPreferences.getInt("pet_resource", 0);
            preferences_view.addView(getImageView(chat_resource));
            preferences_view.addView(getImageView(smoking_resource));
            preferences_view.addView(getImageView(music_resource));
            preferences_view.addView(getImageView(pet_resource));
        } else {
            tvAddPreference.setVisibility(View.VISIBLE);
            preferences_view.setVisibility(View.GONE);
        }
    }

    private ImageView getImageView(int resource) {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.car_preference_img_width), getResources().getDimensionPixelSize(R.dimen.car_preference_img_height));
        params.setMarginEnd(20);
        imageView.setLayoutParams(params);
        imageView.setImageResource(resource);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    private void getPopUpMenu() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), this.imageVerification);
        popupMenu.getMenuInflater().inflate(R.menu.verification_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_edit_my_email) {
                    startActivity(new Intent(getContext(),EditProfileActivity.class));
                  //  Toast.makeText(DetailFragment.this.getContext(), DetailFragment.this.getResources().getString(R.string.action_edit_my_email), Toast.LENGTH_LONG).show();
                }
                else if(menuItem.getItemId() == R.id.action_edit_my_phone){
                  //  Toast.makeText(getContext(),"Edit my photo",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(),VerifyPhoneNumberActivity.class));
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void addUserBio() {
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }

    private void addpreference(){
        startActivity(new Intent(getContext(), AddPreferenceActivity.class));
    }

    private void addACar() {
        Intent intent = new Intent(getContext(), AddCarActivity.class);
        intent.putExtra("edit_flag", "new");
        startActivity(intent);
    }

    private void addverifymyid(){
        startActivity(new Intent(getContext(), VerifyMyIdActivity.class));
    }

    private void addverifyphone(){
        startActivity(new Intent(getContext(), VerifyPhoneNumberActivity.class));
    }

    private void addverifyemail(){
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }
    private void seepublicprofile(){
        startActivity(new Intent(getContext(), SeePublicProfileActivity.class));

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_verification /*2131230905*/:
                getPopUpMenu();
                return;
            case R.id.tv_add_car /*2131231072*/:
                addACar();
                return;
            case R.id.add_car_btn:
                addACar();
                return;
            case R.id.tv_mini_bio /*2131231092*/:
                addUserBio();
                return;
            case R.id.tv_add_preference:
                 addpreference();
                 return;
            case R.id.preferences_view:
                addpreference();
                return;
            case  R.id.tv_verify_id:
                  addverifymyid();
                  return;
            case  R.id.tv_add_phone:
                  addverifyphone();
                  return;
            case R.id.tv_verify_email:
                   addverifyemail();
                   return;
            case  R.id.tv_see_public_profile:
                    seepublicprofile();
                    return;
            default:
                return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedPreferences.getBoolean("isExistCarPreferences", false)){
            isExistCarPreferences = true;
        } else {
            isExistCarPreferences = false;
        }
        showOrHidePreferences();
        showCarInfoList();
    }
}
