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
        float sensitivity = prefs.getFloat(sensitivityE,2.5f);
        return sensitivity;
    }

    @Override
    public void setSensitivity(float sensitivity) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putFloat(sensitivityE,sensitivity);
        editor.commit();
    }

    @Override
    public boolean getSound() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        boolean soundEnabled = prefs.getBoolean(soundE,true);
        return soundEnabled;
    }

    @Override
    public void setSound(boolean sound) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putBoolean(soundE,sound);
        editor.commit();
    }

    @Override
    public boolean getMusic() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        boolean musicEnabled = prefs.getBoolean(musicE,true);
        return musicEnabled;
    }

    @Override
    public void setMusic(boolean music) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putBoolean(musicE,music);
        editor.commit();
    }

    @Override
    public int getHighscore(int position) {
        String temp = highscore + position;
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        int highscoreValue = prefs.getInt(temp,0);
        return highscoreValue;
    }

    @Override
    public void setHighscore(int value, int position) {
        String temp = highscore + position;
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putInt(temp,value);
        editor.commit();
    }
}
