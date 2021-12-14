package com.example.usershopapp.Database.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.usershopapp.Database.ModelDB.Cart;
import com.example.usershopapp.Database.ModelDB.Favorite;

@Database(entities = {Cart.class, Favorite.class},version = 1,exportSchema = false)
public abstract class AliRoomDatabase extends RoomDatabase {
    public abstract CartDAO cartDAO();
    public abstract FavoriteDao favoriteDao();
    private static AliRoomDatabase instance;

    public static AliRoomDatabase getInstance(Context context)
    {
        if (instance==null)
            instance=Room.databaseBuilder(context, AliRoomDatabase.class,"ali_Drink")
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}
