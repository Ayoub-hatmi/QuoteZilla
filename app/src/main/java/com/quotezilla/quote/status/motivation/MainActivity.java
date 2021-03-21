package com.quotezilla.quote.status.motivation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;



public class MainActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {

    ChipNavigationBar navBar;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //Dark mode
        SharedPreferences appSettingsPrefs = getSharedPreferences("AppSettingsPrefs",0);
        Boolean isNightModeOn = appSettingsPrefs.getBoolean("NightMode",false);

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

        //Firebase push notification

        Intent intentBackgroundService = new Intent(this, FirebasePushNotification.class);
        startService(intentBackgroundService);

        // Chip Bottom Navigation Bar
        navBar = findViewById(R.id.bottomNav);
        navBar.setOnItemSelectedListener(this);
        navBar.setItemSelected(R.id.home,true);
        //loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        else {

        Toast.makeText(this,"Fragment error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottommenu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(int i) {

        Fragment selectedFragment = null;
        switch (i) {
            case R.id.home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.favorites:
                selectedFragment = new FavoritesFragment();
                break;
            case R.id.topics:
                selectedFragment = new TopicsFragment();
                break;
            case R.id.settings:
                selectedFragment = new SettingsFragment();
                break;
        }
        loadFragment(selectedFragment);

    }

}
