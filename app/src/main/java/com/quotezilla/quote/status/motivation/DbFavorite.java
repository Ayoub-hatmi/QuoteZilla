package com.quotezilla.quote.status.motivation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbFavorite extends SQLiteOpenHelper {

    private static final String DB_NAME = "my_phone_db";
    private static final int DB_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_QUOTE = "content";
    private static final String KEY_WRITER = "writer";
    private static final String KEY_TOPIC = "topic";
    private static final String TABLE_FAVORITES = "FAVORITES";



    public DbFavorite(@Nullable Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table "+ TABLE_FAVORITES+ "("+ KEY_ID+" integer primary key," + KEY_QUOTE+" varchar(1000), "+ KEY_WRITER+" varchar(100), "+KEY_TOPIC+" varchar(30) ) ";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String delete_query = "DROP table "+TABLE_FAVORITES;
        db.execSQL(delete_query);

        onCreate(db);
    }

    public void AddQuoteToFavorite(Quote quote){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUOTE, quote.getContent());
        values.put(KEY_WRITER, quote.getWriter());
        values.put(KEY_TOPIC, quote.getTopic());
        values.put(KEY_ID, quote.getId());
        db.insert(TABLE_FAVORITES,null, values);


    }

    public void DeleteQuoteFromFav(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_FAVORITES + " where " + KEY_ID + " = " + id );
        this.getAllFavorites();

    }

    public boolean CheckIsQuoteAlreadyInFavorNot(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select id from " + TABLE_FAVORITES + " where " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public ArrayList<Quote> getAllFavorites(){


        ArrayList<Quote> favorites = new ArrayList<>();
        String select_query = "select * from "+ TABLE_FAVORITES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);

    if (cursor.moveToFirst()) {

    do {

        String content = cursor.getString(cursor.getColumnIndex(KEY_QUOTE));
        String writer = cursor.getString(cursor.getColumnIndex(KEY_WRITER));
        int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));

        Quote quote = new Quote(content,writer,"", id);

            favorites.add(0, quote);


    } while (cursor.moveToNext());
}
        return favorites;
    }
}
