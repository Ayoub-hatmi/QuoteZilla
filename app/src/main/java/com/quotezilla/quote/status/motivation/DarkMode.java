package com.quotezilla.quote.status.motivation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DarkMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_mode);

        RadioGroup radioGroup = findViewById(R.id.radio_group);

        RadioButton darkBtn = findViewById(R.id.radio_dark);
        RadioButton lightBtn = findViewById(R.id.radio_light);
        darkBtn.setChecked(Update("radio_dark"));
        lightBtn.setChecked(Update("radio_light"));






        // shared preferences to save the mode
        SharedPreferences appSettingsPrefs = getSharedPreferences("AppSettingsPrefs",0);
        SharedPreferences.Editor sharedPrefsEdit = appSettingsPrefs.edit();
        Boolean isNightModeOn = appSettingsPrefs.getBoolean("NightMode",false);


        if(isNightModeOn){
            darkBtn.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            lightBtn.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }




        darkBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedDark) {
                SaveRadioStatus("radio_dark", isCheckedDark );
                if (isNightModeOn){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPrefsEdit.putBoolean("NightMode",false);
                    sharedPrefsEdit.apply();}

            }


        });

        lightBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedLight) {
                SaveRadioStatus("radio_light", isCheckedLight);


                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedPrefsEdit.putBoolean("NightMode", true);
                sharedPrefsEdit.apply();
            }
        });



    }





    private void SaveRadioStatus(String key, boolean value){
        SharedPreferences radioStatusPrefs = getSharedPreferences("Radio status",MODE_PRIVATE);
        SharedPreferences.Editor editor = radioStatusPrefs.edit();
        editor.putBoolean(key, value);
        editor.apply();


    }


    private boolean Update(String key){
        SharedPreferences radioStatusPrefs = getSharedPreferences("Radio status",MODE_PRIVATE);
        return radioStatusPrefs.getBoolean(key, false);


    }
}