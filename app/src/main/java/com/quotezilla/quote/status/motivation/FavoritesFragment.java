package com.quotezilla.quote.status.motivation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Quote> favList;

    DbFavorite db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);
        db = new DbFavorite(getActivity());

        TextView txt1 = view.findViewById(R.id.txt1);
        TextView txt2 = view.findViewById(R.id.txt2);
        ImageView img = view.findViewById(R.id.img);


        favList = new ArrayList<>();
        favList = db.getAllFavorites();


        // build recycler view
        mRecyclerView = view.findViewById(R.id.fav_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new FavAdapter(favList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);



        // empty favorites view
        if (favList.size()==0) {

            img.setImageResource(R.drawable.empty_fav);
            txt1.setText("No Favorites");
            txt2.setText("Your Favorites will appear here");

        }




        return view;


    }
}