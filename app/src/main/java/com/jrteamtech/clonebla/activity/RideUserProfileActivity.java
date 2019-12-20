package com.jrteamtech.clonebla.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class RideUserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] pref_chat_descs = {"I'm the quiet type","I talk depending on my mood","I love to chat!"};
    private String[] pref_smoking_descs = {"No smoking please","Somking is OK sometimes","Smoking doesn't bother me"};
    private String[] pref_music_descs = {"Silence is golden","I listen to music if i fancy it","It's all about the playlist"};
    private String[] pref_pets_descs = {"No pets please","Depends on the animal","Pets welcome.Woof!"};

    private int[] pref_chat_imgs = {R.drawable.chat_1, R.drawable.chat_2, R.drawable.chat_3};
    private int[] pref_smoking_imgs = {R.drawable.smoking_1, R.drawable.smoking_2, R.drawable.smoking_3};
    private int[] pref_music_imgs = {R.drawable.music_1, R.drawable.music_2, R.drawable.music_3};
    private int[] pref_pets_imgs = {R.drawable.pets_1, R.drawable.pets_2, R.drawable.pets_3};

    ImageButton back_arrow_btn;
    CircleImageView ci_user_avatar;
    TextView tv_user_name, tv_user_age;
    LinearLayout user_pref_layout, user_pref_group_layout;
    LinearLayout user_verify_info_layout;
    TextView tv_user_member_date;
    TextView btnReportMember;

    JSONObject userInfoObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_user_profile);

        if(getIntent() != null && getIntent().getStringExtra("user_info") != null){
            try {
                userInfoObject = new JSONObject(getIntent().getStringExtra("user_info"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        back_arrow_btn = findViewById(R.id.img_back_arrow);
        ci_user_avatar = findViewById(R.id.ci_user_avatar);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_age = findViewById(R.id.tv_user_age);
        user_pref_layout = findViewById(R.id.user_pref_layout);
        user_pref_group_layout = findViewById(R.id.user_pref_group_layout);
        user_verify_info_layout = findViewById(R.id.user_verify_info_layout);
        tv_user_member_date = findViewById(R.id.user_member_date);
        btnReportMember = findViewById(R.id.btnReportMember);

        back_arrow_btn.setOnClickListener(this);
        btnReportMember.setOnClickListener(this);

        initialize();

    }

    private void initialize() {
        try {
            tv_user_name.setText(userInfoObject.getString("name"));
            tv_user_member_date.setText(userInfoObject.getString("start_member"));

            JSONObject prefInfoObject = userInfoObject.getJSONObject("preference_info");
            int chat_info = Integer.parseInt(prefInfoObject.getString("chat"));
            int smoking_info = Integer.parseInt(prefInfoObject.getString("smoking"));
            int music_info = Integer.parseInt(prefInfoObject.getString("music"));
            int pets_info = Integer.parseInt(prefInfoObject.getString("pets"));

            switch (chat_info){
                case 1:
                    user_pref_group_layout.addView(getPreferenceView(pref_chat_imgs[0], pref_chat_descs[0]));
                    break;
                case 2:
                    user_pref_group_layout.addView(getPreferenceView(pref_chat_imgs[1], pref_chat_descs[1]));
                    break;
                case 3:
                    user_pref_group_layout.addView(getPreferenceView(pref_chat_imgs[2], pref_chat_descs[2]));
                    break;
            }
            switch (smoking_info){
                case 1:
                    user_pref_group_layout.addView(getPreferenceView(pref_smoking_imgs[0], pref_smoking_descs[0]));
                    break;
                case 2:
                    user_pref_group_layout.addView(getPreferenceView(pref_smoking_imgs[1], pref_smoking_descs[1]));
                    break;
                case 3:
                    user_pref_group_layout.addView(getPreferenceView(pref_smoking_imgs[2], pref_smoking_descs[2]));
                    break;
            }
            switch (music_info){
                case 1:
                    user_pref_group_layout.addView(getPreferenceView(pref_music_imgs[0], pref_music_descs[0]));
                    break;
                case 2:
                    user_pref_group_layout.addView(getPreferenceView(pref_music_imgs[1], pref_music_descs[1]));
                    break;
                case 3:
                    user_pref_group_layout.addView(getPreferenceView(pref_music_imgs[2], pref_music_descs[2]));
                    break;
            }
            switch (pets_info){
                case 1:
                    user_pref_group_layout.addView(getPreferenceView(pref_pets_imgs[0], pref_pets_descs[0]));
                    break;
                case 2:
                    user_pref_group_layout.addView(getPreferenceView(pref_pets_imgs[1], pref_pets_descs[1]));
                    break;
                case 3:
                    user_pref_group_layout.addView(getPreferenceView(pref_pets_imgs[2], pref_pets_descs[2]));
                    break;
            }

            JSONObject verifyInfoObject = userInfoObject.getJSONObject("verify_info");
            if(verifyInfoObject.getString("id").equals("1")){
                user_verify_info_layout.addView(getPreferenceView(R.drawable.check_green, "Identity verified"));
            }
            if(verifyInfoObject.getString("phone").equals("1")){
                user_verify_info_layout.addView(getPreferenceView(R.drawable.check_green, "Phone verified"));
            }
            if(verifyInfoObject.getString("email").equals("1")){
                user_verify_info_layout.addView(getPreferenceView(R.drawable.check_green, "Email verified"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
            case R.id.btnReportMember:
                break;
        }
    }
}
