package com.jrteamtech.clonebla.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.fragment.DetailFragment;

public class SeePublicProfileActivity  extends AppCompatActivity {

    private ImageButton back_arrow_btn;

    private ImageView chat_img, smoking_img, music_img, pet_img;
    private TextView chat_text, smoking_text, music_text, pet_text;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_public_profile);

        sharedPreferences = getSharedPreferences("CarPrefs", Context.MODE_PRIVATE);

        chat_img = findViewById(R.id.chat_img);
        chat_text = findViewById(R.id.chat_text);
        smoking_img = findViewById(R.id.smoking_img);
        smoking_text = findViewById(R.id.smoking_text);
        music_img = findViewById(R.id.music_img);
        music_text = findViewById(R.id.music_text);
        pet_img = findViewById(R.id.pet_img);
        pet_text = findViewById(R.id.pet_text);

        chat_img.setImageResource(sharedPreferences.getInt("chat_resource", 0));
        chat_text.setText(sharedPreferences.getString("chat_text", ""));
        smoking_img.setImageResource(sharedPreferences.getInt("smoking_resource", 0));
        smoking_text.setText(sharedPreferences.getString("smoking_text", ""));
        music_img.setImageResource(sharedPreferences.getInt("music_resource", 0));
        music_text.setText(sharedPreferences.getString("music_text", ""));
        pet_img.setImageResource(sharedPreferences.getInt("pet_resource", 0));
        pet_text.setText(sharedPreferences.getString("pet_text", ""));

        back_arrow_btn = (ImageButton) findViewById(R.id.profileclose);


        back_arrow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SeePublicProfileActivity.this, DetailFragment.class);
//                startActivity(intent);
                finish();
            }
        });
    }

}
