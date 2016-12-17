package com.example.dolphin.kichuna;

/**
 * Created by DOLPHIN on 12/16/2016.
 */

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.activeandroid.ActiveAndroid;


public class Emergency extends Application {
    private static Emergency sInstance;
    private static DBUser_Info mDatabaseUserInfo;

    public static Emergency getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public synchronized static DBUser_Info getWritableDatabaseUserInfo() {
        if (mDatabaseUserInfo == null) {
            mDatabaseUserInfo = new DBUser_Info(getAppContext());
        }
        return mDatabaseUserInfo;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance=this;
        //mDatabaseUserInfo = new DBUser_Info(this);
        ActiveAndroid.initialize(this);
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
