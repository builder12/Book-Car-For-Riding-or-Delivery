package com.jrteamtech.clonebla.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RideDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] pref_chat_descs = {"I'm the quiet type","I talk depending on my mood","I love to chat!"};
    private String[] pref_smoking_descs = {"No smoking please","Somking is OK sometimes","Smoking doesn't bother me"};
    private String[] pref_music_descs = {"Silence is golden","I listen to music if i fancy it","It's all about the playlist"};
    private String[] pref_pets_descs = {"No pets please","Depends on the animal","Pets welcome.Woof!"};

    private int[] pref_chat_imgs = {R.drawable.chat_1, R.drawable.chat_2, R.drawable.chat_3};
    private int[] pref_smoking_imgs = {R.drawable.smoking_1, R.drawable.smoking_2, R.drawable.smoking_3};
    private int[] pref_music_imgs = {R.drawable.music_1, R.drawable.music_2, R.drawable.music_3};
    private int[] pref_pets_imgs = {R.drawable.pets_1, R.drawable.pets_2, R.drawable.pets_3};

    ImageButton back_arrow_btn;
    TextView date_view;
    RelativeLayout leave_detail_layout, drop_detail_layout;
    TextView leave_time_view, leave_address_view, leave_city_view, from_departure_view;
    TextView drop_time_view, drop_address_view, drop_city_view, from_arrival_view;
    ImageView leave_walk_img, drop_walk_img;
    TextView price_view;
    RelativeLayout user_item_layout;
    TextView user_name_view, user_rating_view;
    CircleImageView user_avatar_view;
    TextView description_view;
    TextView btnContact;
    LinearLayout preference_layout;
    TextView car_info_make_model_view, car_info_color_view;
    CircleImageView car_avatar_view;
    LinearLayout booked_layout;
    ListView booked_listview;
    TextView btnReportRide;
    Button btnContinue;

    JSONObject rideInfoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail);

        if(getIntent() != null && getIntent().getStringExtra("ride_item_info") != null){
            try {
                rideInfoObject = new JSONObject(getIntent().getStringExtra("ride_item_info"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        date_view = findViewById(R.id.date_view);
        leave_detail_layout = findViewById(R.id.leave_detail_layout);
        leave_time_view = findViewById(R.id.leave_time_view);
        leave_address_view = findViewById(R.id.leave_address_view);
        leave_city_view = findViewById(R.id.leave_city_view);
        from_departure_view = findViewById(R.id.from_departure_view);
        drop_detail_layout = findViewById(R.id.drop_detail_layout);
        drop_time_view = findViewById(R.id.drop_time_view);
        drop_address_view = findViewById(R.id.drop_address_view);
        drop_city_view = findViewById(R.id.drop_city_view);
        from_arrival_view = findViewById(R.id.from_arrival_view);
        leave_walk_img = findViewById(R.id.leave_walk_img);
        drop_walk_img = findViewById(R.id.drop_walk_img);
        price_view = findViewById(R.id.price_view);
        user_item_layout = findViewById(R.id.user_item_layout);
        user_name_view = findViewById(R.id.tv_user_name);
        user_rating_view = findViewById(R.id.tv_user_rating_view);
        user_avatar_view = findViewById(R.id.ci_user_avatar);
        description_view = findViewById(R.id.tv_description);
        btnContact = findViewById(R.id.tv_contact_btn);
        preference_layout = findViewById(R.id.preference_layout);
        car_info_make_model_view = findViewById(R.id.car_info_make_model_view);
        car_info_color_view = findViewById(R.id.car_info_color_view);
        car_avatar_view = findViewById(R.id.ci_car_avatar);
        booked_layout = findViewById(R.id.booked_list_layout);
        booked_listview = findViewById(R.id.booked_listview);
        btnReportRide = findViewById(R.id.tv_report_ride_btn);
        btnContinue = findViewById(R.id.continue_btn);

        back_arrow_btn.setOnClickListener(this);
        leave_detail_layout.setOnClickListener(this);
        drop_detail_layout.setOnClickListener(this);
        user_item_layout.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnReportRide.setOnClickListener(this);
        btnContinue.setOnClickListener(this);

        initialize();

    }

    private void initialize() {
        try {
            date_view.setText(rideInfoObject.getString("date"));
            leave_time_view.setText(rideInfoObject.getString("leave_time"));
            leave_address_view.setText(rideInfoObject.getString("leave_address"));
            leave_city_view.setText(rideInfoObject.getString("leave_city"));
            switch (rideInfoObject.getString("leave_walk_type")){
                case "1":
                    leave_walk_img.setImageResource(R.drawable.walk_green_icon);
                    break;
                case "2":
                    leave_walk_img.setImageResource(R.drawable.walk_yellow_icon);
                    break;
                case "3":
                    leave_walk_img.setImageResource(R.drawable.walk_orange_icon);
                    break;
            }
            from_departure_view.setText(rideInfoObject.getString("from_departure"));
            drop_time_view.setText(rideInfoObject.getString("drop_time"));
            drop_address_view.setText(rideInfoObject.getString("drop_address"));
            drop_city_view.setText(rideInfoObject.getString("drop_city"));
            switch (rideInfoObject.getString("drop_walk_type")){
                case "1":
                    drop_walk_img.setImageResource(R.drawable.walk_green_icon);
                    break;
                case "2":
                    drop_walk_img.setImageResource(R.drawable.walk_yellow_icon);
                    break;
                case "3":
                    drop_walk_img.setImageResource(R.drawable.walk_orange_icon);
                    break;
            }
            from_arrival_view.setText(rideInfoObject.getString("from_arrival"));
            price_view.setText(rideInfoObject.getString("currency") + rideInfoObject.getString("price"));
            user_name_view.setText(rideInfoObject.getJSONObject("user_info").getString("name"));
            btnContact.setText("Contact " + rideInfoObject.getJSONObject("user_info").getString("name"));

            JSONObject prefInfoObject = rideInfoObject.getJSONObject("user_info").getJSONObject("preference_info");
            int chat_info = Integer.parseInt(prefInfoObject.getString("chat"));
            int smoking_info = Integer.parseInt(prefInfoObject.getString("smoking"));
            int music_info = Integer.parseInt(prefInfoObject.getString("music"));
            int pets_info = Integer.parseInt(prefInfoObject.getString("pets"));

            switch (chat_info){
                case 1:
                    preference_layout.addView(getPreferenceView(pref_chat_imgs[0], pref_chat_descs[0]));
                    break;
                case 2:
                    preference_layout.addView(getPreferenceView(pref_chat_imgs[1], pref_chat_descs[1]));
                    break;
                case 3:
                    preference_layout.addView(getPreferenceView(pref_chat_imgs[2], pref_chat_descs[2]));
                    break;
            }
            switch (smoking_info){
                case 1:
                    preference_layout.addView(getPreferenceView(pref_smoking_imgs[0], pref_smoking_descs[0]));
                    break;
                case 2:
                    preference_layout.addView(getPreferenceView(pref_smoking_imgs[1], pref_smoking_descs[1]));
                    break;
                case 3:
                    preference_layout.addView(getPreferenceView(pref_smoking_imgs[2], pref_smoking_descs[2]));
                    break;
            }
            switch (music_info){
                case 1:
                    preference_layout.addView(getPreferenceView(pref_music_imgs[0], pref_music_descs[0]));
                    break;
                case 2:
                    preference_layout.addView(getPreferenceView(pref_music_imgs[1], pref_music_descs[1]));
                    break;
                case 3:
                    preference_layout.addView(getPreferenceView(pref_music_imgs[2], pref_music_descs[2]));
                    break;
            }
            switch (pets_info){
                case 1:
                    preference_layout.addView(getPreferenceView(pref_pets_imgs[0], pref_pets_descs[0]));
                    break;
                case 2:
                    preference_layout.addView(getPreferenceView(pref_pets_imgs[1], pref_pets_descs[1]));
                    break;
                case 3:
                    preference_layout.addView(getPreferenceView(pref_pets_imgs[2], pref_pets_descs[2]));
                    break;
            }

            JSONObject carInfoObject = rideInfoObject.getJSONObject("car_info");
            car_info_make_model_view.setText(carInfoObject.getString("make") + " " + carInfoObject.getString("model"));
            car_info_color_view.setText(carInfoObject.getString("color"));

            JSONObject bookedUsersObject = rideInfoObject.getJSONObject("booked_users");
            List<JSONObject> bookedUserInfosList = new ArrayList<>();
            if(bookedUsersObject.length() != 0){
                booked_layout.setVisibility(View.VISIBLE);
                Iterator<String> stringIterator = bookedUsersObject.keys();
                while (stringIterator.hasNext()){
                    try {
                        String key = stringIterator.next();
                        JSONObject childObject = (JSONObject) bookedUsersObject.get(key);
                        bookedUserInfosList.add(childObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                BookedListAdapter adapter = new BookedListAdapter(RideDetailActivity.this, 0, bookedUserInfosList);
                booked_listview.setAdapter(adapter);

            } else {
                booked_layout.setVisibility(View.GONE);
            }

        } catch (JSONException e) {
            Log.e("JSONObject Error", e.getLocalizedMessage());
        }
    }

    private View getPreferenceView (int img_resource, String desc) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.preference_item, null);
        ImageView pref_icon = view.findViewById(R.id.pref_icon);
        TextView pref_desc = view.findViewById(R.id.pref_description);
        pref_icon.setImageResource(img_resource);
        pref_desc.setText(desc);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_arrow:
                finish();
                break;
            case R.id.leave_detail_layout:
                startActivity(new Intent(RideDetailActivity.this, FullMapActivity.class));
                break;
            case R.id.drop_detail_layout:
                startActivity(new Intent(RideDetailActivity.this, FullMapActivity.class));
                break;
            case R.id.user_item_layout:
                Intent userIntent = new Intent(RideDetailActivity.this, RideUserProfileActivity.class);
                try {
                    userIntent.putExtra("user_info", rideInfoObject.getJSONObject("user_info").toString());
                    startActivity(userIntent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_contact_btn:
                break;
            case R.id.tv_report_ride_btn:
                startActivity(new Intent(RideDetailActivity.this, ReportRideActivity.class));
                break;
            case R.id.continue_btn:
                Intent continueIntent = new Intent(RideDetailActivity.this, RideBookSeatActivity.class);
                continueIntent.putExtra("ride_info", rideInfoObject.toString());
                startActivity(continueIntent);
                break;
        }
    }

    private class BookedListAdapter extends ArrayAdapter<JSONObject> {

        Context mContext;

        BookedListAdapter(Context context, int res, List<JSONObject> data){
            super(context, res, data);
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.user_item, parent, false);
            }

            JSONObject object = getItem(position);
            TextView tv_user_name = convertView.findViewById(R.id.tv_user_name);
            TextView tv_user_simple_route_view = convertView.findViewById(R.id.tv_simple_route_view);
            CircleImageView ci_user_avatar = convertView.findViewById(R.id.ci_user_avatar);

            try {
                tv_user_name.setText(object.getString("name"));
                tv_user_simple_route_view.setText(object.getString("route"));
            } catch (JSONException e){
                e.printStackTrace();
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RideUserProfileActivity.class);
                    intent.putExtra("user_info", object.toString());
                    mContext.startActivity(intent);
                }
            });

            return convertView;
        }
    }
}
