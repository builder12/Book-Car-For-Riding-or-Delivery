package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jrteamtech.clonebla.R;

public class HistoryFragment extends Fragment {
    private Button btnSeeArchivedRides;
    private TextView tvNoOfferedRides;
    private TextView tvYourRides;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
      return layoutInflater.inflate(R.layout.fragment_history, viewGroup, false);
       // return null;
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
//        super.onViewCreated(view, bundle);
//        this.tvYourRides = (TextView) view.findViewById(R.id.tv_your_rides);
//        this.tvNoOfferedRides = (TextView) view.findViewById(R.id.tv_no_offer_rides);
//        this.btnSeeArchivedRides = (Button) view.findViewById(R.id.btn_see_archived_rides);
    }
}
