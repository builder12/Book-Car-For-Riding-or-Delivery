package com.jrteamtech.clonebla.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jrteamtech.clonebla.R;

public class SearchLocationActivity extends AppCompatActivity {

    ListView lv_station;
    EditText et_search_station;
    ImageView arrow_back_img;
    ArrayAdapter<String> stationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        et_search_station = findViewById(R.id.ev_station);
        lv_station = findViewById(R.id.lv_station);

        String [] stationContent = {
                "Manchester",
                "London",
                "Chelsea"
        };

        stationListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stationContent);
        lv_station.setAdapter(stationListAdapter);

        lv_station.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SearchLocationActivity.this, DropOffActivity.class));
            }
        });

        et_search_station.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stationListAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        arrow_back_img = findViewById(R.id.back_arrow);

        arrow_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchLocationActivity.this, PickUpActivity.class));
            }
        });
    }


}
