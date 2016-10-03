package com.dumbpug.crossbowknight.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;

/**
 * Represents the main games state.
 */
public class MainGame extends State {
	/** Test level. */
	Level testLevel;
	
	public MainGame() {
		// Set up a test level.
		testLevel = LevelFactory.getLevelFromDisk("MAIN_HALL");
	}

    @Override
    public void renderState() {
    	// Update the level.
		testLevel.update();
    }
    
    @Override
	public void drawState() {
    	// Get the games SpriteBatch
		SpriteBatch batch = CrossbowKnight.getSpriteBatch();
		// Draw the level.
		batch.begin();
		testLevel.draw(batch);
		batch.end();
	}
    
    @Override
    public void onTransitionFromState(State state) {}

    @Override
    public String getStateName() { return "game"; }
}
