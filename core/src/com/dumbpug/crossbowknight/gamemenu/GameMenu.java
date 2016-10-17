package com.dumbpug.crossbowknight.gamemenu;

/**
 * Represents the in-game menu.
 * Created by nik on 16/10/16.
 */
public class GameMenu {
	/** The drawer of the game menu. */
	private GameMenuDrawer gameMenuDrawer;
	
	/**
	 * Create a new instance of the GameMenu class.
	 */
	public GameMenu() {
		gameMenuDrawer = new GameMenuDrawer(this);
	}

	/**
	 * Update the game menu.
	 */
	public void update() {
		System.out.println("update game menu");
	}
	
	/**
	 * Get the drawer for the game menu.
	 * @return gameMenuDrawer.
	 */
	public GameMenuDrawer getGameMenuDrawer() { return this.gameMenuDrawer; }
}
