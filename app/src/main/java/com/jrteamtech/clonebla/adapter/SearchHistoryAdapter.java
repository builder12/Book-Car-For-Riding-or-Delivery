package com.jrteamtech.clonebla.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.jrteamtech.clonebla.R;

import java.util.List;

import static com.jrteamtech.clonebla.activity.SearchActivity.mMap;
import static com.jrteamtech.clonebla.activity.SearchActivity.mMapView;
import static com.jrteamtech.clonebla.activity.SearchActivity.savedInstanceState;

public class SearchHistoryAdapter extends ArrayAdapter<String> implements OnMapReadyCallback {
    private List<String> addresses;
    Context mContext;
    RelativeLayout search_history_item_view;

    public SearchHistoryAdapter(List<String> data, Context context) {
        super(context, R.layout.search_history_list_item, data);
        this.addresses = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.search_history_list_item, parent, false);

        }

        String address = getItem(position);
        search_history_item_view = convertView.findViewById(R.id.search_history_item_view);
        TextView search_list_text = convertView.findViewById(R.id.search_history_item_tv);
        search_list_text.setText(address);

        search_history_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySearchResult();
            }
        });

        return convertView;
    }

    private void displaySearchResult() {
        mMapView.setVisibility(View.VISIBLE);
        search_history_item_view.setVisibility(View.GONE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ((Activity)mContext).requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
        } else {
            mMapView.onCreate(savedInstanceState, this);
            mMapView.onResume();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMyLocationEnabled(true);
        mMapView.onMapReady(googleMap);

        mMapView.setGoogleMapPadding(0,24,24,0);

//        mMap.addMarker(new MarkerOptions().position(new LatLng(geocodeResult.getExtent().getWidth(), geocodeResult.getExtent().getDepth())).title("Marker is Sydney"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(geocodeResult.getExtent().getWidth(), geocodeResult.getExtent().getDepth()), 18.f));

    }
}
