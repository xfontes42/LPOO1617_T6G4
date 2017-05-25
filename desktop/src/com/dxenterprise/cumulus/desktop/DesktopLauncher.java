package com.dxenterprise.cumulus.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dxenterprise.cumulus.MyCumulusGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Cumulus";
		config.addIcon("icon.png", Files.FileType.Internal);
		//MyCumulusPreference prefs = new MyCumulusPreference(this);
		new LwjglApplication(new MyCumulusGame(), config);

	}
}
