package com.dxenterprise.cumulus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dxenterprise.cumulus.view.MainMenuView;

import java.util.ArrayList;


public class MyCumulusGame extends Game {
	/**
	 * The game's sprites.
	 */
	private SpriteBatch batch;

	/**
	 * The game's asset manager.
	 */
	private AssetManager assetManager;

	/**
	 * A flag indicating if sound is on.
	 */
	private boolean soundOn = false;

	/**
	 * A flag indicating if music is on.
	 */
	private boolean musicOn = false;

	/**
	 * The game's preferences.
	 */
	private MyCumulusPrefsInterface preferences;

	/**
	 * The number of highscores to be shown in the respective menu.
	 */
	private final int num_scores = 5;

	/**
	 * Returns the number of highscores being stored.
	 * @return the number of highscores being stored
	 */
	public int getNum_scores() {
		return num_scores;
	}

	/**
	 * Returns an ArrayList of the top scores.
	 * @return the top scores
	 */
	public ArrayList<Integer> getScores() {
		return scores;
	}

	/**
	 * Replaces the current scores with a new set.
	 * @param scores the new highscore set
	 */
	public void setScores(ArrayList<Integer> scores) {
		this.scores = scores;
	}

	/**
	 * The game's highscores.
	 */
	private ArrayList<Integer> scores = new ArrayList<Integer>(num_scores);

	/**
	 * Creates the game object.
	 * @param prefs the preferences
	 */
	public MyCumulusGame(MyCumulusPrefsInterface prefs){
		this.preferences = prefs;
	}

	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadScores();
		startMainMenu();
	}

	/**
	 * Loads the highscores.
	 */
	public void loadScores(){
		for(int i = 1; i <= num_scores; i++){
			scores.add(preferences.getHighscore(i));
		}

	}

	/**
	 * Toggles the sound on/off.
	 */
	public void toggleSound(){
		soundOn = !soundOn;
		preferences.setSound(soundOn);
	}

	/**
	 * Toggles the music on/off.
	 */
	public void toggleMusic(){
		musicOn = !musicOn;
		preferences.setMusic(musicOn);
	}

	/**
	 * Returns the game's preferences.
	 * @return the preferences
	 */
	public MyCumulusPrefsInterface getPreferences(){
		return preferences;
	}

	/**
	 * Checks if the sound is on.
	 * @return true if the sound is on, false if otherwise
	 */
	public boolean isSoundOn(){
		return soundOn;
	}

	/**
	 * Checks if the music is on.
	 * @return true if the music is on, false if otherwise
	 */
	public boolean isMusicOn(){
		return musicOn;
	}

	/**
	 * Starts the main menu.
	 */
	public void startMainMenu(){
		soundOn = preferences.getSound();
		musicOn = preferences.getMusic();
		setScreen(new MainMenuView(this));
	}

	/**
	 * Disposes of the loaded resources.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

	/**
	 * Returns the asset manager used to load all textures and sounds.
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}
}
