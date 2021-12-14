package com.example.usershopapp.Database.DataStore;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.usershopapp.Database.ModelDB.Favorite;
import com.example.usershopapp.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {

    Flowable<List<Favorite>> getFavItems();

    int isFavorite(int itemId);

    void insertFav(Favorite...favorites);

    void delete(Favorite favorite);


}
