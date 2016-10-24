package com.dumbpug.crossbowknight.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.gamemenu.GameMenu;
import com.dumbpug.crossbowknight.hud.HUD;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;

/**
 * Represents the main games state.
 */
public class MainGame extends State {
	/** The game HUD. */
	private HUD hud;
	/** The in-game menu. */
	private GameMenu gameMenu;
	/** Test level. */
	Level testLevel;
	/** Is the game menu open. */
	private boolean isGameMenuOpen = false;
	
	public MainGame() {
		// Create the game HUD.
		this.hud = new HUD();
		// Set up a test level.
		testLevel = LevelFactory.getLevelFromDisk("DOOM_HALL");
		// Create the game menu.
		this.gameMenu = new GameMenu(testLevel.getPlayer());
	}

    @Override
    public void renderState() {
    	// Check whether the user wants to open close the game menu.
    	if(CrossbowKnight.getPlayerInput().isThirdButtonPressed()) { isGameMenuOpen = !isGameMenuOpen; }
    	// If the game menu is open then we want to render that and not the level.
    	if(isGameMenuOpen) {
    		// Update the game menu.
    		gameMenu.update();
    	} else {
    		// Update the level.
    		testLevel.update();
    	}
    }
    
    @Override
	public void drawState() {
    	// Get the games SpriteBatch
		SpriteBatch batch = CrossbowKnight.getSpriteBatch();
		// Draw the level.
		batch.begin();
		// Draw the level.
		testLevel.draw(batch);
		// Draw the game HUD.
		this.hud.draw(batch);
		// If the game menu is open then we need to draw it over the level.
		if(isGameMenuOpen) {
			gameMenu.getGameMenuDrawer().draw(batch);
		}
		batch.end();
	}
    
    @Override
    public void onTransitionFromState(State state) {}

    @Override
    public String getStateName() { return "game"; }
}
