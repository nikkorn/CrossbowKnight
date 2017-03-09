package com.dumbpug.crossbowknight.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.gamemenu.GameMenu;
import com.dumbpug.crossbowknight.hud.DevHUD;
import com.dumbpug.crossbowknight.hud.HUD;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;
import com.dumbpug.crossbowknight.level.generator.LevelGenerator;

/**
 * Represents the main games state.
 */
public class MainGame extends State {
	/** The dev HUD. */
	private DevHUD devHUD;
	/** The game HUD. */
	private HUD hud;
	/** The in-game menu. */
	private GameMenu gameMenu;
	/** The current level. */
	private Level level;
	/** The level generator. */
	private LevelGenerator levelGenerator;
	/** Is the game menu open. */
	private boolean isGameMenuOpen = false;
	
	/**
	 * Create  anew instance of the MainGame class.
	 */
	public MainGame() {
		// Create the dev HUD.
		this.devHUD = new DevHUD();
		// Create the game HUD.
		this.hud = new HUD();
		// Set up a test level.
		this.setLevel(LevelFactory.getLevelFromDisk("DOOM_HALL"));
		// Create our level generator.
		this.levelGenerator = new LevelGenerator();
		// Create the game menu.
		this.gameMenu = new GameMenu(level.getPlayer());
	}

    @Override
    public void renderState() {
    	// Check whether the user wants to open close the dev HUD.
    	if(Gdx.input.isKeyJustPressed(Keys.F9)) { this.devHUD.toggle(); }
    	// Check whether the user wants to open close the game menu.
    	if(CrossbowKnight.getPlayerInput().isThirdButtonPressed()) { isGameMenuOpen = !isGameMenuOpen; }
    	// If the game menu is open then we want to render that and not the level.
    	if(isGameMenuOpen) {
    		// Update the game menu.
    		gameMenu.update();
    	} else {
    		// Update the level.
    		level.update();
    	}
    }
    
    @Override
	public void drawState() {
    	// Get the games SpriteBatch
		SpriteBatch batch = CrossbowKnight.getSpriteBatch();
		// Draw the level.
		batch.begin();
		// Draw the level.
		level.draw(batch);
		// Draw the game HUD.
		this.hud.draw(batch, level.getPlayer());
		// If the game menu is open then we need to draw it over the level.
		if(isGameMenuOpen) {
			gameMenu.getGameMenuDrawer().draw(batch);
		}
		// Draw the dev HUD if it is open.
		if(devHUD.isOpen()) { 
			devHUD.draw(batch); 
		}
		batch.end();
	}
    
    /**
     * Set the current level.
     * @param level
     */
    public void setLevel(Level level) {
    	// Set the level.
    	this.level = level;
    	// Pass the current level to our dev HUD so that 
    	// it can display level specific information.
    	this.devHUD.setLevel(level);
    }
    
    @Override
    public void onTransitionFromState(State state) {}

    @Override
    public String getStateName() { return "game"; }
}
