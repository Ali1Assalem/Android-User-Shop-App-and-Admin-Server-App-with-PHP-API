package com.example.usershopapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.usershopapp.Model.User;

public class SharedPrefManger {
    private static String SHARED_PREF_NAME="ali";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManger(Context context) {
        this.context = context;
    }

    public void saveUser(User user){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("email",user.getEmail());
        editor.putString("name",user.getName());
        editor.putString("address",user.getAddress());
        editor.putString("avatarUrl",user.getAvatarUrl());
        editor.putString("error_msg",user.getError_msg());
        editor.putBoolean("looged",true);
        editor.apply();
    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("looged",false);
    }

    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("email",null),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("address",null),
                sharedPreferences.getString("error_msg",null),
                sharedPreferences.getString("avatarUrl",null));

    }

}
