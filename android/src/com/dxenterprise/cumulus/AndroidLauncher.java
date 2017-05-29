package com.dxenterprise.cumulus;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication {
	/**
	 * Runs the game.
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MyCumulusPreference prefs = new MyCumulusPreference(this);

		initialize(new MyCumulusGame(prefs), config);
	}
}
