package com.dxenterprise.cumulus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

/**
 * Created by Xavier Fontes on 25/05/2017.
 */

public class MyCumulusPreference implements MyCumulusPrefsInterface {
    public static final String PREFS_NAME = "cumulus_settings";
    Handler handler;
    Context context;

    public MyCumulusPreference(Context context){
        handler = new Handler();
        this.context = context;
    }


    @Override
    public float getSensitivity() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        float sensitivity = prefs.getFloat("sensitivity",2.5f);
        return sensitivity;
    }

    @Override
    public void setSensitivity(float sensitivity) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putFloat("sensitivity",sensitivity);
        editor.commit();
    }

    @Override
    public boolean getSound() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        boolean soundEnabled = prefs.getBoolean("sound",true);
        return soundEnabled;
    }

    @Override
    public void setSound(boolean sound) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putBoolean("sound",sound);
        editor.commit();
    }

    @Override
    public boolean getMusic() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        boolean musicEnabled = prefs.getBoolean("music",true);
        return musicEnabled;
    }

    @Override
    public void setMusic(boolean music) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putBoolean("music",music);
        editor.commit();
    }
}
