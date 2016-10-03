package com.dumbpug.crossbowknight.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;

/**
 * Represents the title screen state.
 */
public class Title extends State {
	/** The DumbPug logo. */
	private Texture dumbpugLogo;
	/** The time we first rendered this state. */
	private long firstRenderTime = -1;
	/** The length of time we should show the splash. */
	private long titleDuration = 500;
	
	public Title() {
		// Load our logo.
		dumbpugLogo = new Texture("graphics/misc/dumbpug.png");
	}

    @Override
    public void renderState() {
    	// Set the first time that this state was rendered
    	if(firstRenderTime == -1) {
    		firstRenderTime = System.currentTimeMillis();
    	}
    	// If we have been displaying this title too long 
    	// then we need to change state to the splash screen.
    	if((System.currentTimeMillis() - firstRenderTime) > titleDuration) {
    		this.changeState("game");
    	}
    }
    
    @Override
	public void drawState() {
		int logoHeight = Gdx.graphics.getHeight() / 8;
		int logoWidth  = logoHeight * 2;
		int posX       = (Gdx.graphics.getWidth()/2) - (logoWidth/2);
		int posY       = (Gdx.graphics.getHeight()/2) - (logoHeight/2);
		// Get the games SpriteBatch
		SpriteBatch batch = CrossbowKnight.getSpriteBatch();
		// Draw our logo to the screen.
		batch.begin();
		batch.draw(dumbpugLogo, posX, posY, logoWidth, logoHeight);
		batch.end();
	}
    
    @Override
    public void onTransitionFromState(State state) {}

    @Override
    public String getStateName() { return "title"; }
}
