package com.pri.fooddelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pri.fooddelivery.pref.PreferenceHelper;
import com.pri.fooddelivery.ui.login.LoginActivity;
import com.pri.fooddelivery.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    @Inject
    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(SplashActivity.this,
                preferenceHelper.isLoggedIn()? MainActivity.class : LoginActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();

    }
}