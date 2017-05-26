package com.dxenterprise.cumulus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dxenterprise.cumulus.view.MainMenuView;

import java.util.ArrayList;


public class MyCumulusGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private boolean soundOn = false;
	private boolean musicOn = false; //enquanto nao le das preferences
	private MyCumulusPrefsInterface preferences;
	private final int num_scores = 5;

	public int getNum_scores() {
		return num_scores;
	}

	public ArrayList<Integer> getScores() {
		return scores;
	}

	public void setScores(ArrayList<Integer> scores) {
		this.scores = scores;
	}

	private ArrayList<Integer> scores = new ArrayList<Integer>(num_scores);

	public MyCumulusGame(MyCumulusPrefsInterface prefs){
		this.preferences = prefs;
	}


	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 * Also starts the game until we have a main menu.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadScores();
		startMainMenu();
	}

	public void loadScores(){
		for(int i = 1; i <= num_scores; i++){
			scores.add(preferences.getHighscore(i));
		}

	}

	public void toggleSound(){
		soundOn = !soundOn;
		preferences.setSound(soundOn);
	}

	public void toggleMusic(){
		musicOn = !musicOn;
		preferences.setMusic(musicOn);
	}

	public MyCumulusPrefsInterface getPreferences(){
		return preferences;
	}

	public boolean isSoundOn(){
		return soundOn;
	}

	public boolean isMusicOn(){
		return musicOn;
	}

	/**
	 *Starts the main menu.
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
