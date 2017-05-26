package com.dxenterprise.cumulus.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.dxenterprise.cumulus.MyCumulusPrefsInterface;

/**
 * Created by Xavier Fontes on 25/05/2017.
 */

public class MyCumulusPreferenceDesktop implements MyCumulusPrefsInterface {

    public Preferences getPrefs(){
        return Gdx.app.getPreferences("settings");
    }

    @Override
    public float getSensitivity() {
       return getPrefs().getFloat(sensitivityE,2.5f);
    }

    @Override
    public void setSensitivity(float sensitivity) {
        getPrefs().putFloat(sensitivityE,sensitivity);
    }

    @Override
    public boolean getSound() {
        return getPrefs().getBoolean(soundE,true);
    }

    @Override
    public void setSound(boolean sound) {
        getPrefs().putBoolean(soundE,sound);
    }

    @Override
    public boolean getMusic() {
        return getPrefs().getBoolean(musicE,true);
    }

    @Override
    public void setMusic(boolean music) {
        getPrefs().putBoolean(musicE,music);
    }

    @Override
    public int getHighscore(int position) {
        String temp = highscore + position;
        return getPrefs().getInteger(temp,0);
    }

    @Override
    public void setHighscore(int value, int position) {
        String temp = highscore + position;
        getPrefs().putInteger(temp, value);
    }
}
