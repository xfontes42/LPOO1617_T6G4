package com.dxenterprise.cumulus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dxenterprise.cumulus.view.MainMenuView;

public class MyCumulusGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private boolean soundOn = true;
	private boolean musicOn = false;

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
	}

	public void toggleMusic(){
		musicOn = !musicOn;
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
