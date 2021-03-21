package com.quotezilla.quote.status.motivation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class TopicsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topics, container, false);

        Button inspirationalButton = view.findViewById(R.id.inspirational_btn);
        Button successButton = view.findViewById(R.id.success_btn);
        Button animeButton = view.findViewById(R.id.anime_btn);
        Button leadershipButton = view.findViewById(R.id.leadership_btn);
        Button loveButton = view.findViewById(R.id.love_btn);
        Button artButton = view.findViewById(R.id.art_btn);
        Button entrepreneurshipButton = view.findViewById(R.id.entrepreneurship_btn);
        Button familyButton = view.findViewById(R.id.family_btn);
        Button happinessButton = view.findViewById(R.id.happiness_btn);
        Button lifeButton = view.findViewById(R.id.life_btn);
        Button showsButton = view.findViewById(R.id.shows_btn);
        Button wisdomButton = view.findViewById(R.id.wisdom_btn);
        Button workoutButton = view.findViewById(R.id.workout_btn);
        Button timeButton = view.findViewById(R.id.time_btn);




        inspirationalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getActivity(),InspirationalQuotes.class);
                startActivity(i1);
            }
        });
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getActivity(),SuccessQuotes.class);
                startActivity(i2);
            }
        });
        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(getActivity(),LoveQuotes.class);
                startActivity(i3);
            }
        });
        leadershipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getActivity(),LeadershipQuotes.class);
                startActivity(i4);
            }
        });
        animeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(getActivity(),AnimeQuotes.class);
                startActivity(i5);
            }
        });

        artButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(getActivity(),ArtQuotes.class);
                startActivity(i6);
            }
        });

        entrepreneurshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7 = new Intent(getActivity(),EntrepreneurshipQuotes.class);
                startActivity(i7);
            }
        });

        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i8 = new Intent(getActivity(),FamilyQuotes.class);
                startActivity(i8);
            }
        });
        happinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i9 = new Intent(getActivity(),HappinessQuotes.class);
                startActivity(i9);
            }
        });

        lifeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i10 = new Intent(getActivity(),LifeQuotes.class);
                startActivity(i10);
            }
        });

        showsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i11 = new Intent(getActivity(),ShowsQuotes.class);
                startActivity(i11);
            }
        });
        wisdomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i12 = new Intent(getActivity(),WisdomQuotes.class);
                startActivity(i12);
            }
        });

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i13 = new Intent(getActivity(),WorkoutQuotes.class);
                startActivity(i13);
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i14 = new Intent(getActivity(),TimeQuotes.class);
                startActivity(i14);
            }
        });

        return view;


    }


}