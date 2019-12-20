package com.jrteamtech.clonebla.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.activity.ChooseDateActivity;
import com.jrteamtech.clonebla.activity.RideFindActivity;
import com.jrteamtech.clonebla.activity.SearchActivity;
import com.jrteamtech.clonebla.model.RideHistoryItemModel;
import com.jrteamtech.clonebla.utility.Global;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    private TextView tvGoingTo;
    private TextView tvLeavingFrom;
    private ImageView exchangeLocationBtn;
    private TextView Calendardate;

    private ListView rideHistoryListView;

    private Button btnSearch;
    List<RideHistoryItemModel> historyItemModels = new ArrayList<>();
    RideHistoryItemModel selected_object;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        tvLeavingFrom = view.findViewById(R.id.tv_leaving_from);
        tvGoingTo = view.findViewById(R.id.tv_going_to);
        exchangeLocationBtn = view.findViewById(R.id.exchange_location_btn);
        Calendardate = view.findViewById(R.id.calendardate);
        btnSearch = view.findViewById(R.id.btnSearch);
        rideHistoryListView = view.findViewById(R.id.ride_history_list);

        tvLeavingFrom.setOnClickListener(this);
        tvGoingTo.setOnClickListener(this);
        exchangeLocationBtn.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        Calendardate.setOnClickListener(this);

        btnSearch.setVisibility(View.GONE);
        rideHistoryListView.setVisibility(View.VISIBLE);

        RideHistoryItemModel model1 = new RideHistoryItemModel();
        model1.setLeaveAddress("Centre St, London E2, UK");
        model1.setDropAddress("Liverpool John Lennon Airport");
        model1.setStops(3);
        model1.setDate("Wed. 16 Oct, 08:00");
        historyItemModels.add(model1);

        RideHistoryItemModel model2 = new RideHistoryItemModel();
        model2.setLeaveAddress("Birmingham, city centre");
        model2.setDropAddress("Bristol, city centre");
        model2.setStops(2);
        model2.setDate("Today 01:00");
        historyItemModels.add(model2);

        RideHistoryListAdapter mAdapter = new RideHistoryListAdapter(getContext(), 0, historyItemModels, new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                selected_object = historyItemModels.get(position);
                tvLeavingFrom.setText(historyItemModels.get(position).getLeaveAddress());
                tvGoingTo.setText(historyItemModels.get(position).getDropAddress());
                exchangeLocationBtn.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.VISIBLE);
                rideHistoryListView.setVisibility(View.GONE);
                Intent intent = new Intent(getContext(), RideFindActivity.class);
                intent.putExtra("ride_info", historyItemModels.get(position));
                startActivity(intent);
            }
        });
        rideHistoryListView.setAdapter(mAdapter);

    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_going_to) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(getContext().getResources().getString(R.string.activity_name), SearchFragment.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.tv_leaving_from) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(getContext().getResources().getString(R.string.activity_name), SearchFragment.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.calendardate) {
         //   startActivity(new Intent(getContext(), RideFindActivity.class));
            Intent intent = new Intent(getContext(), ChooseDateActivity.class);
            intent.putExtra(getContext().getResources().getString(R.string.activity_name), SearchFragment.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.btnSearch) {
            Intent intent = new Intent(getContext(), RideFindActivity.class);
            intent.putExtra("ride_info", selected_object);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Global.getSelected_time() != null){
         //   Calendardate.setText("ddd");
            Calendardate.setText(Global.getSelected_time());
        }
    }

    private class RideHistoryListAdapter extends ArrayAdapter<RideHistoryItemModel> {

        Context mContext;
        ItemClickListener itemClickListener;

        RideHistoryListAdapter(Context context, int res, List<RideHistoryItemModel> data, ItemClickListener itemClickListener) {
            super(context, res, data);
            this.mContext = context;
            this.itemClickListener = itemClickListener;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.ride_history_item, parent, false);
            }

            RideHistoryItemModel model = getItem(position);
            TextView route_view = convertView.findViewById(R.id.route_view);
            TextView date_view = convertView.findViewById(R.id.date_view);

            route_view.setText(model.getLeaveAddress() + " -> " + model.getDropAddress() + " (STOP " + model.getStops() + ")");
            date_view.setText(model.getDate());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClickListener(position);
                }
            });

            return convertView;
        }
    }

    interface ItemClickListener {
        void OnItemClickListener(int position);
    }
}
