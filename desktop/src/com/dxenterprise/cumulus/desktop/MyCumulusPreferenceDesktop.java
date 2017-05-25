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
       return getPrefs().getFloat("sensitivity",2.5f);
    }

    @Override
    public void setSensitivity(float sensitivity) {
        getPrefs().putFloat("sensivity",sensitivity);
    }

    @Override
    public boolean getSound() {
        return getPrefs().getBoolean("sound",true);
    }

    @Override
    public void setSound(boolean sound) {
        getPrefs().putBoolean("sound",sound);
    }

    @Override
    public boolean getMusic() {
        return getPrefs().getBoolean("music",true);
    }

    @Override
    public void setMusic(boolean music) {
        getPrefs().putBoolean("music",music);
    }
}
