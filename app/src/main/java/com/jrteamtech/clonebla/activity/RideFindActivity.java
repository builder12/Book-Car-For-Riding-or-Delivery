package com.jrteamtech.clonebla.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.adapter.AddressListAdapter;
import com.jrteamtech.clonebla.model.RideHistoryItemModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RideFindActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton back_arrow_btn;
    TextView route_view, date_view;
    ProgressBar progressBar;
    LinearLayout no_ride_layout;
    Button btnCreateRideAlert, btnFilter;
    ProgressBar button_progress_bar;
    ImageView success_img;
    RecyclerView foundRideRecyclerView;

    AddressListAdapter mAdapter;
    RideHistoryItemModel rideHistoryItemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_find);

        if(getIntent() != null && getIntent().getSerializableExtra("ride_info") != null){
            rideHistoryItemModel = (RideHistoryItemModel) getIntent().getSerializableExtra("ride_info");
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        route_view = findViewById(R.id.route_view);
        date_view = findViewById(R.id.date_view);
        progressBar = findViewById(R.id.progressBar);
        no_ride_layout = findViewById(R.id.no_ride_layout);
        btnCreateRideAlert = findViewById(R.id.btnCreateRideAlert);
        btnFilter = findViewById(R.id.btnFilter);
        foundRideRecyclerView = findViewById(R.id.found_ride_recyclerview);
        button_progress_bar = findViewById(R.id.button_progress_bar);
        success_img = findViewById(R.id.success_icon);

        back_arrow_btn.setOnClickListener(this);
        btnCreateRideAlert.setOnClickListener(this);
        btnFilter.setOnClickListener(this);

        route_view.setText(rideHistoryItemModel.getLeaveAddress() + " -> " + rideHistoryItemModel.getDropAddress() + " (Stop " + rideHistoryItemModel.getStops() + ")");
        date_view.setText(rideHistoryItemModel.getDate());

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        foundRideRecyclerView.setVisibility(View.GONE);
        btnCreateRideAlert.setVisibility(View.GONE);
        btnFilter.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                foundRideRecyclerView.setVisibility(View.VISIBLE);
                btnFilter.setVisibility(View.VISIBLE);
                btnCreateRideAlert.setVisibility(View.VISIBLE);
                showList();
            }
        }, 2000);
    }

    private void showList() {
        JSONObject jsonObjectList = getJSONObjectList();
        Iterator<String> stringIterator = jsonObjectList.keys();
        List<JSONObject> objectList = new ArrayList<>();
        while (stringIterator.hasNext()){
            try {
                String key = stringIterator.next();
                JSONObject childObject = (JSONObject) jsonObjectList.get(key);
                objectList.add(childObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter = new AddressListAdapter(RideFindActivity.this, objectList);
        foundRideRecyclerView.setLayoutManager(new LinearLayoutManager(RideFindActivity.this));
        foundRideRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foundRideRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setItemClickListener(new AddressListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(RideFindActivity.this, RideDetailActivity.class);
                intent.putExtra("ride_item_info", objectList.get(position).toString());
                startActivity(intent);
            }
        });
    }

    private JSONObject getJSONObjectList() {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("json.txt")));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }

            return new JSONObject(builder.toString());
        } catch (IOException e) {
            //log the exception
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.btnCreateRideAlert:
                btnCreateRideAlert.setVisibility(View.GONE);
                button_progress_bar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button_progress_bar.setVisibility(View.GONE);
                        success_img.setVisibility(View.VISIBLE);
                    }
                }, 2000);
                break;
            case R.id.btnFilter:
                startActivity(new Intent(RideFindActivity.this, RideFilterActivity.class));
                break;
        }
    }
}
