package com.dumbpug.crossbowknight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dumbpug.crossbowknight.CrossbowKnight;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		//config.height = 0;
		//config.width = 0;
		new LwjglApplication(new CrossbowKnight(), config);
	}
}
