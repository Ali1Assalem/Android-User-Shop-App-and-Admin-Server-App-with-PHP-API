package com.example.usershopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usershopapp.Fragments.SignInFragment;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import edmt.dev.afilechooser.utils.FileUtils;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer,new SignInFragment()).commit();

        Objects.requireNonNull(getSupportActionBar()).hide();


    }

}