package com.example.milan.rftproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedUtils {
    public SharedUtils(){{
    }
    }
    public static boolean saveUsername(String username, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefeditor = prefs.edit();
        prefeditor.putString(SharedConstants.KEY_USERNAME,username);
        prefeditor.apply();
        return true;
    }
    public static String getUsername(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(SharedConstants.KEY_USERNAME,null);
    }
    public static boolean savePassword(String password, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefeditor = prefs.edit();
        prefeditor.putString(SharedConstants.KEY_PASSWORD,password);
        prefeditor.apply();
        return true;
    }
    public static String getPassword(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(SharedConstants.KEY_PASSWORD,null);
    }
}
