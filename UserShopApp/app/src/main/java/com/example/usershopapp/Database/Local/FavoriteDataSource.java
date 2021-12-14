package com.example.usershopapp.Database.Local;

import com.example.usershopapp.Database.DataStore.IFavoriteDataSource;

import com.example.usershopapp.Database.ModelDB.Favorite;


import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDataSource {

    private FavoriteDao favoriteDao;
    private static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDataSource getInstance(FavoriteDao favoriteDao)
    {
        if (instance==null)
            instance=new FavoriteDataSource(favoriteDao);
        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavItems() {
        return favoriteDao.getFavItems();
    }

    @Override
    public int isFavorite(int itemId) {
        return favoriteDao.isFavorite(itemId);
    }

    @Override
    public void insertFav(Favorite... favorites) {
        favoriteDao.insertFav(favorites);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDao.delete(favorite);
    }
}
