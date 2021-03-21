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

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {
    private ArrayList<Quote> mExampleList;
    private DbFavorite db;
    private Context context;

    public static class FavViewHolder extends RecyclerView.ViewHolder {
        public TextView contentTxt;
        public TextView writerTxt;
        public ImageButton copyBtn;
        public ImageButton likeBtn;
        public ImageButton shareBtn;

        public FavViewHolder(View itemView) {
            super(itemView);
            contentTxt = itemView.findViewById(R.id.fav_content_txt);
            writerTxt = itemView.findViewById(R.id.fav_writer_txt);
            copyBtn = itemView.findViewById(R.id.copy_fav_btn);
            likeBtn = itemView.findViewById(R.id.like_fav_btn);
            shareBtn = itemView.findViewById(R.id.share_fav_btn);

        }
    }
    public FavAdapter(ArrayList<Quote> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public FavAdapter.FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qoute_fav_view, parent, false);
        FavAdapter.FavViewHolder evh = new FavAdapter.FavViewHolder(v);
        context = parent.getContext();

        return evh;
    }

    @Override
    public void onBindViewHolder(FavViewHolder holder, int position) {
        Quote currentItem = mExampleList.get(position);
        int id = mExampleList.get(position).getId();

        holder.contentTxt.setText(currentItem.getContent());
        holder.writerTxt.setText(currentItem.getWriter());
        holder.itemView.setTag(id);
        db = new DbFavorite(context);
        String text = currentItem.getContent() + "\n" + "Written by " + currentItem.getWriter() + "\n" + "From QuoteZilla App";

        holder.copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard= (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context,"Copied",Toast.LENGTH_SHORT).show();

            }
        });

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.DeleteQuoteFromFav(id);
                Toast.makeText(context, "Removed from Favorites",Toast.LENGTH_SHORT).show();

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