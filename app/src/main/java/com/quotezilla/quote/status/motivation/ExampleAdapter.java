package com.quotezilla.quote.status.motivation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Quote> mExampleList;
    private DbFavorite db;
    private Context context;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView contentTxt;
        public TextView writerTxt;
        public ImageButton copyBtn;
        public ImageButton shareBtn;
        public LottieAnimationView likeBtn;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            contentTxt = itemView.findViewById(R.id.content_txt);
            writerTxt = itemView.findViewById(R.id.writer_txt);
            copyBtn = itemView.findViewById(R.id.copy_btn);
            likeBtn = itemView.findViewById(R.id.like_btn);
            shareBtn = itemView.findViewById(R.id.share_btn);

        }
    }
    public ExampleAdapter(ArrayList<Quote> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_view, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        context = parent.getContext();

        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Quote currentItem = mExampleList.get(position);
        holder.contentTxt.setText(currentItem.getContent());
        holder.writerTxt.setText(currentItem.getWriter());
        db = new DbFavorite(context);
        String text = currentItem.getContent() + "\n" + "Written by " + currentItem.getWriter() + "\n" + "From QuoteZilla App";


        holder.copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard= (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context,"Copied!",Toast.LENGTH_SHORT).show();

            }
        });

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.likeBtn.playAnimation();
                if (!db.CheckIsQuoteAlreadyInFavorNot(currentItem.getId())){
                db.AddQuoteToFavorite(currentItem);
                Toast.makeText(context,"Added to Favorites",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(context,"Already in Favorites",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.shareApp(text, context);

            }
        });
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}