package com.jrteamtech.clonebla.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.adapter.SearchHistoryAdapter;
import com.jrteamtech.clonebla.fragment.SearchFragment;
import com.jrteamtech.clonebla.utility.Global;

import java.util.ArrayList;
import java.util.List;

import jrizani.jrmapview.JRMapView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, TextWatcher {

    private EditText et_search;
    private ImageButton search_back_btn, search_btn;

    private ListView search_history_list_view;
    SearchHistoryAdapter mAdapter;
    private RelativeLayout rl_current_location_btn;
    private RelativeLayout map_layout;

    public static GoogleMap mMap;
    public static JRMapView mMapView;
    private FloatingActionButton next_pick_btn;

    public static Bundle savedInstanceState;

    private String activity_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_search);

        if(getIntent() != null){
            activity_name = getIntent().getStringExtra(getResources().getString(R.string.activity_name));
        }

        mMapView = findViewById(R.id.mapView);

        et_search = findViewById(R.id.et_search);
        search_history_list_view = findViewById(R.id.search_history_list_view);

        search_back_btn = findViewById(R.id.search_back_btn);
        search_btn = findViewById(R.id.search_icon);
        rl_current_location_btn = findViewById(R.id.rl_current_location);
        map_layout = findViewById(R.id.map_layout);
        next_pick_btn = findViewById(R.id.next_pick_btn);

        et_search.addTextChangedListener(this);
        search_back_btn.setOnClickListener(this);
        rl_current_location_btn.setOnClickListener(this);
        next_pick_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back_btn:
                search_back_btn.setVisibility(View.GONE);
                finish();
                break;
            case R.id.rl_current_location:
                rl_current_location_btn.setVisibility(View.GONE);
                search_history_list_view.setVisibility(View.GONE);
                map_layout.setVisibility(View.VISIBLE);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
                } else {
                    mMapView.onCreate(savedInstanceState, this);
                    mMapView.onResume();
                }
                break;
            case R.id.next_pick_btn:
                if(activity_name.equals(PickUpActivity.class.getSimpleName())){
                    startActivity(new Intent(SearchActivity.this, DropOffActivity.class));
                } else if(activity_name.equals(DropOffActivity.class.getSimpleName())){
                    startActivity(new Intent(SearchActivity.this, StopOversActivity.class));
                } else if(activity_name.equals(SearchFragment.class.getSimpleName())) {
                    startActivity(new Intent(SearchActivity.this, DropOffActivity.class));
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mMapView.onCreate(savedInstanceState, this);
                mMapView.onResume();
            } else {

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        mMapView.onMapReady(googleMap);

        mMapView.setGoogleMapPadding(0,24,24,0);

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Marker is Sydney"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 18.f));
                }
            }
        });
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override public void afterTextChanged(Editable s) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals("")) {
            List<String> results = new ArrayList<>();
            String url = Global.GOOGLE_MAP_SEARCH_PLACE_URL + "?query=" + s.toString() + "&key=" + getResources().getString(R.string.google_map_api_key);
//            new ReadJsonFromUrl().execute(url);
//            Call<GetXmlInfo> apiCall = ApiClient.getApiXmlClient().getXml();
//            apiCall.enqueue(new Callback<GetXmlInfo>() {
//                @Override
//                public void onResponse(Call<GetXmlInfo> call, Response<GetXmlInfo> response) {
//                    assert response.body() != null;
//                    Float newVersionNumber = Float.parseFloat(response.body().getVersion());
//                    if(currentVersionNumber < newVersionNumber){
//                        new AlertDialog.Builder(CategoryActivity.this)
//                                .setTitle("Update available")
//                                .setMessage("New version " + newVersionNumber + " is released. Click DOWNLOAD NOW to download please.")
//                                .setPositiveButton("DOWNLOAD NOW", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.evilkingmedia.com/download/"));
//                                        startActivity(intent);
//                                    }
//                                })
//                                .setNegativeButton("Cancel", null)
//                                .show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<GetXmlInfo> call, Throwable t) {
//                    Toast.makeText(CategoryActivity.this, "error!", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

//    private class ReadJsonFromUrl extends AsyncTask<String, Void, JsonElement> {
//        @Override
//        protected JsonElement doInBackground(String... urls) {
//            InputStream is = null;
//            try {
//                is = new URL(urls[0]).openStream();
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//                StringBuilder sb = new StringBuilder();
//                int cp;
//                while ((cp = rd.read()) != -1){
//                    sb.append((char)cp);
//                }
//                String jsonText = sb.toString();
//                Log.e("AAAAAAAAA", jsonText);
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        @Override
//        protected void onPostExecute(JsonElement jsonElement) {
//            if(jsonElement != null){
////                try {
////
//////                    Log.e("AAAAAAAAAA", );
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//            }
//        }
//    }

}

