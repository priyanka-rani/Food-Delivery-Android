package com.pri.fooddelivery.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.pri.fooddelivery.BuildConfig;


public class PreferenceHelper {
    public static final String SHARED_PREF_NAME = BuildConfig.APPLICATION_ID+".cart_pref";
    public static final String LOGIN_ID = "login_id";
    public static final String ISLOGGEDIN = "isLoggedIn";

    // marking the instance as volatile to ensure atomic access to the variable
    private PreferenceHelper() {
    }

    private SharedPreferences prefs;

    public static PreferenceHelper getInstance(Context context) {
        PreferenceHelper instance = new PreferenceHelper();
        instance.prefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return instance;
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(ISLOGGEDIN, false);
    }

    public int getLoggedInId() {
        return prefs.getInt(LOGIN_ID, -1);
    }

    public void loginUser(int loggedinId) {
        prefs.edit()
                .putInt(LOGIN_ID, loggedinId)
                .putBoolean(ISLOGGEDIN, true)
                .apply();
    }

    public void logoutUser() {
        prefs.edit().clear().apply();
    }
}
