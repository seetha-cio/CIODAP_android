package com.connectedio.ciodav001.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreferences {
    private static final String KEY_IS_LOGIN = "isLogin";


    private SharedPreferences prefs;

    public MyPreferences(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static MyPreferences getInstance(Context context) {
        MyPreferences instance = new MyPreferences(context);
        return instance;
    }



    public boolean isLogin() {
        return prefs.getBoolean(KEY_IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.apply();
    }


    public void clear() {
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.commit();
    }

}
