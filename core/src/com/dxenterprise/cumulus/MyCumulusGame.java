package com.dxenterprise.cumulus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dxenterprise.cumulus.view.MainMenuView;


public class MyCumulusGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private boolean soundOn = false;
	private boolean musicOn = false; //enquanto nao le das preferences
	private MyCumulusPrefsInterface preferences;

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
		loadAssets();
		startMainMenu();
	}

	public void loadAssets(){
//		assetManager.load("Gorillaz_Andromeda.mp3", Music.class);
//		assetManager.finishLoading();

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
