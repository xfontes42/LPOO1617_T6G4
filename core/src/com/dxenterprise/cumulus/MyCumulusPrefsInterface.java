package com.dxenterprise.cumulus;

/**
 * Created by Xavier Fontes on 25/05/2017.
 */

public interface MyCumulusPrefsInterface  {
    String soundE = "sound";
    String musicE = "music";
    String sensitivityE = "sensitivity";
    String highscore = "highscore";


    float getSensitivity();
    void setSensitivity(float sensitivity);
    boolean getSound();
    void setSound(boolean sound);
    boolean getMusic();
    void setMusic(boolean music);
    int getHighscore(int position);
    void setHighscore(int value, int position);
}
