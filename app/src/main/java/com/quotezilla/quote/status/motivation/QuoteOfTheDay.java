package com.quotezilla.quote.status.motivation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuoteOfTheDay extends AppCompatActivity {
    DatabaseReference mRef;
    DbFavorite dbFav;
    Quote quote;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_of_the_day);


        //ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.qotd_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        TextView contentTxt = findViewById(R.id.qotd_content_txt);
        TextView writerTxt = findViewById(R.id.qotd_writer_txt);
        ImageButton copyBtn = findViewById(R.id.qotd_copy_btn);
        ImageButton shareBtn = findViewById(R.id.qotd_share_btn);
        LottieAnimationView likeBtn = findViewById(R.id.qotd_like_btn);



        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mRef = db.getReference("QuoteOfTheDay");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quote = snapshot.getValue(Quote.class);
                contentTxt.setText(quote.getContent());
                writerTxt.setText(quote.getWriter());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        dbFav = new DbFavorite(QuoteOfTheDay.this);



        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = quote.getContent() + "\n" +"Written by " + quote.getWriter() + "\n" + "From QuoteZilla App";
                ClipboardManager clipboard= (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(QuoteOfTheDay.this,"Copied",Toast.LENGTH_SHORT).show();            }
        });

            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeBtn.playAnimation();
                   if (!dbFav.CheckIsQuoteAlreadyInFavorNot(quote.getId())){
                        dbFav.AddQuoteToFavorite(quote);
                        Toast.makeText(QuoteOfTheDay.this,"Added to Favorites",Toast.LENGTH_LONG).show();}
                 else {
                      Toast.makeText(QuoteOfTheDay.this,"Already in Favorites",Toast.LENGTH_LONG).show();
                 }

                 }


            });

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = quote.getContent() + "\n" +"Written by " + quote.getWriter() + "\n" + "From QuoteZilla App";
                    Share.shareApp(text, QuoteOfTheDay.this);

                }
            });




    }
}