package com.dxenterprise.cumulus;

/**
 * Created by Xavier Fontes on 25/05/2017.
 */

public interface MyCumulusPrefsInterface  {
    /**
     * The string associated to the sound value in the preferences.
     */
    String soundE = "sound";

    /**
     * The string associated to the music value in the preferences.
     */
    String musicE = "music";

    /**
     * The string associated to the sensitivity value in the preferences.
     */
    String sensitivityE = "sensitivity";

    /**
     * The string associated to the highscores in the preferences.
     */
    String highscore = "highscore";

    /**
     * Gets the sensitivity value.
     * @return the sensitivity value
     */
    float getSensitivity();

    /**
     * Sets the sensitivity.
     * @param sensitivity the new sensitivity value
     */
    void setSensitivity(float sensitivity);

    /**
     * Gets the state of the sound.
     * @return true if sound is enabled, false if otherwise
     */
    boolean getSound();

    /**
     * Sets the sound state.
     * @param sound the new sound state (enabled/disabled)
     */
    void setSound(boolean sound);

    /**
     * Gets the state of the music.
     * @return true if sound is enabled, false if otherwise
     */
    boolean getMusic();

    /**
     * Sets the music state.
     * @param music the new sound state (enabled/disabled)
     */
    void setMusic(boolean music);

    /**
     * Gets a highscore for a specific position.
     * @param position the position being inquired
     * @return the score in that position
     */
    int getHighscore(int position);

    /**
     * Sets a highscore for a specific position.
     * @param value the score for that position
     * @param position the position being changed
     */
    void setHighscore(int value, int position);
}
