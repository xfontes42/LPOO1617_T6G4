package com.dxenterprise.cumulus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dxenterprise.cumulus.view.MainMenuView;

public class MyCumulusGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

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
		assetManager.load("iconSettings.png",Texture.class);
		assetManager.load("iconShare.png",Texture.class);
		assetManager.load("iconController.png",Texture.class);
		assetManager.load("iconPlay.png",Texture.class);
		assetManager.load("iconCheck.png",Texture.class);
		assetManager.load("iconBack.png",Texture.class);
		assetManager.load("iconPause.png",Texture.class);
		assetManager.load("Cumulus.png",Texture.class);
		assetManager.load("Settings.png",Texture.class);
		assetManager.load("bird.png",Texture.class);
		assetManager.load("cloudSmall.png",Texture.class);
		assetManager.load("cloudMedium.png",Texture.class);
		assetManager.load("cloudBig.png",Texture.class);
		assetManager.finishLoading();
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
