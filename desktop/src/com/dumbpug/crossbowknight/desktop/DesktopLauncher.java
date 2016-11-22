package com.dumbpug.crossbowknight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.leveleditor.CrossbowKnightLevelEditor;

public class DesktopLauncher {
	public static void main (String[] args) {
		// If we get any args then we are after the level editor.
		if(args.length > 0) {
			// Our command line argument is our level name.
			String levelName = args[0];
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.height = 500;
			config.width  = 1000;
			new LwjglApplication(new CrossbowKnightLevelEditor(levelName), config);
		} else {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//	config.fullscreen = true;
			//config.height     = 0;
			//config.width      = 0;
			new LwjglApplication(new CrossbowKnight(), config);
		}
	}
}
