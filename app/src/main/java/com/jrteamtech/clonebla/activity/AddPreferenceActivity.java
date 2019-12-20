package com.jrteamtech.clonebla.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jrteamtech.clonebla.R;

public class AddPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Spinner chattines,smoking,music,pets;
    private String[] chattinesitem = {"I'm the quiet type","I talk depending on my mood","I love to chat!"};
    private String[] smokingitem = {"No smoking please","Somking is OK sometimes","Smoking doesn't bother me"};
    private String[] musicitem = {"Silence is golden","I listen to music if i fancy it","It's all about the playlist"};
    private String[] petsitem = {"No pets please","Depends on the animal","Pets welcome.Woof!"};
    private ArrayAdapter adapterchattiness,adaptersmoking,adaptermusic,adapterpets;

    private ImageView chat_img, smoking_img, music_img, pet_img;
    private Button save_preferences_btn;

    int chat_resource, smoking_resource, music_resource, pet_resource;
    int chat_index, smoking_index, music_index, pet_index;



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperference);

        this.chattines =  findViewById(R.id.chattiness);
        this.smoking =  findViewById(R.id.smoking);
        this.music =  findViewById(R.id.music);
        this.pets =  findViewById(R.id.pets);

        this.chat_img = findViewById(R.id.chat_img);
        this.smoking_img = findViewById(R.id.smoking_img);
        this.music_img = findViewById(R.id.music_img);
        this.pet_img = findViewById(R.id.pet_img);


        this.save_preferences_btn = findViewById(R.id.save_preferences);
        this.save_preferences_btn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("CarPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(!sharedPreferences.getBoolean("isExistCarPreferences", false)){
            chat_resource = R.drawable.chat_1;
            smoking_resource = R.drawable.smoking_1;
            music_resource = R.drawable.music_1;
            pet_resource = R.drawable.pets_1;

            chat_index = 0;
            smoking_index = 0;
            music_index = 0;
            pet_index = 0;
        } else {
            chat_resource = sharedPreferences.getInt("chat_resource", 0);
            smoking_resource = sharedPreferences.getInt("smoking_resource", 0);
            music_resource = sharedPreferences.getInt("music_resource", 0);
            pet_resource = sharedPreferences.getInt("pet_resource", 0);

            chat_index = sharedPreferences.getInt("chat_index", 0);
            smoking_index = sharedPreferences.getInt("smoking_index", 0);
            music_index = sharedPreferences.getInt("music_index", 0);
            pet_index = sharedPreferences.getInt("pet_index", 0);
        }

        this.chat_img.setImageResource(chat_resource);
        this.smoking_img.setImageResource(smoking_resource);
        this.music_img.setImageResource(music_resource);
        this.pet_img.setImageResource(pet_resource);

        setAdapter();
        setToolbar();
    }
    private void setAdapter(){
        this.adapterchattiness = new ArrayAdapter<>(AddPreferenceActivity.this, android.R.layout.simple_list_item_1, this.chattinesitem);
        this.adapterchattiness.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chattines.setAdapter(this.adapterchattiness);
        chattines.setSelection(chat_index);
        chattines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chat_index = position;
                switch (position){
                    case 0:
                        chat_resource = R.drawable.chat_1;
                        break;
                    case 1:
                        chat_resource = R.drawable.chat_2;
                        break;
                    case 2:
                        chat_resource = R.drawable.chat_3;
                        break;
                }
                chat_img.setImageResource(chat_resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.adaptersmoking = new ArrayAdapter<>(AddPreferenceActivity.this,android.R.layout.simple_list_item_1,this.smokingitem);
        this.adaptersmoking.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoking.setAdapter(this.adaptersmoking);
        smoking.setSelection(smoking_index);
        smoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                smoking_index = position;
                switch (position){
                    case 0:
                        smoking_resource = R.drawable.smoking_1;
                        break;
                    case 1:
                        smoking_resource = R.drawable.smoking_2;
                        break;
                    case 2:
                        smoking_resource = R.drawable.smoking_3;
                        break;
                }
                smoking_img.setImageResource(smoking_resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.adaptermusic = new ArrayAdapter<>(AddPreferenceActivity.this,android.R.layout.simple_list_item_1,this.musicitem);
        this.adaptermusic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        music.setAdapter(this.adaptermusic);
        music.setSelection(music_index);
        music.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                music_index = position;
                switch (position){
                    case 0:
                        music_resource = R.drawable.music_1;
                        break;
                    case 1:
                        music_resource = R.drawable.music_2;
                        break;
                    case 2:
                        music_resource = R.drawable.music_3;
                        break;
                }
                music_img.setImageResource(music_resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.adapterpets = new ArrayAdapter<>(AddPreferenceActivity.this,android.R.layout.simple_list_item_1,this.petsitem);
        this.adapterpets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pets.setAdapter(this.adapterpets);
        pets.setSelection(pet_index);
        pets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pet_index = position;
                switch (position){
                    case 0:
                        pet_resource = R.drawable.pets_1;
                        break;
                    case 1:
                        pet_resource = R.drawable.pets_2;
                        break;
                    case 2:
                        pet_resource = R.drawable.pets_3;
                        break;
                }
                pet_img.setImageResource(pet_resource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void setToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("Edit preferences");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_preferences:
                editor.putInt("chat_index", chat_index);
                editor.putInt("chat_resource", chat_resource);
                editor.putString("chat_text", chattinesitem[chat_index]);
                editor.putInt("smoking_index", smoking_index);
                editor.putInt("smoking_resource", smoking_resource);
                editor.putString("smoking_text", smokingitem[smoking_index]);
                editor.putInt("music_index", music_index);
                editor.putInt("music_resource", music_resource);
                editor.putString("music_text", musicitem[music_index]);
                editor.putInt("pet_index", pet_index);
                editor.putInt("pet_resource", pet_resource);
                editor.putString("pet_text", petsitem[pet_index]);
                editor.putBoolean("isExistCarPreferences", true);
                editor.apply();
                finish();
                break;
        }
    }
}
