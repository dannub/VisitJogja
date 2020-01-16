package com.erporate.visitjogja.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class Intro {
    private static Context context;

    public Intro(Context context){
        Intro.context = context;
    }

    private final static String PREFS_NAME = "jogja_prefs";


    public  void setrestorePrefData( boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isIntroOpnend", value);
        editor.apply();
    }


    public boolean getrestorePrefData() {
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME,0);
        return pref.getBoolean("isIntroOpnend",false);
    }


}
