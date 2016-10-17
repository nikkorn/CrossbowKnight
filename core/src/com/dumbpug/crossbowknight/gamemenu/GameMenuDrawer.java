package com.dumbpug.crossbowknight.gamemenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * Responsible for drawing a game menu.
 * Created by nik on 16/10/16.
 */
public class GameMenuDrawer {
	/** The game menu. */
	private GameMenu gameMenu;
	/** The game menu background. */
	private Texture background;

	/**
	 * Create a new instance of the GameMenuDrawer class.
	 * @param gameMenu
	 */
	public GameMenuDrawer(GameMenu gameMenu) {
		this.gameMenu   = gameMenu;
		this.background = new Texture("graphics/gamemenu/gamemenu_background.png"); 
	}
	
	/**
	 * Draw the game menu.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw the background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
	}
}
