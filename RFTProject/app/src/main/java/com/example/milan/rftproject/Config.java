package com.example.milan.rftproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Config {
    private SharedPreferences sharedPreferences;
    private Context context;

    public Config (Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }
    public String readUsername(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_name),"User");
    }
    public String writeUsername(String username){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),username);
        editor.commit();
    }
    public  void diplayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
