package com.dumbpug.crossbowknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.input.DesktopPlayerInput;
import com.dumbpug.crossbowknight.input.OuyaPlayerInput;
import com.dumbpug.crossbowknight.input.PlayerInput;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;

public class CrossbowKnight extends ApplicationAdapter {
	/** The games input processor. */
	private static PlayerInput playerInput;
	/** The games sprite batch. */
	private SpriteBatch batch;

	/** Test level. */
	Level testLevel;
	
	@Override
	public void create () {
		// Create the games SpriteBatch.
		batch = new SpriteBatch();
		// Set the games input processor. Depends on whether we are running on the Ouya or Desktop.
		CrossbowKnight.playerInput = Ouya.runningOnOuya ? new OuyaPlayerInput() : new DesktopPlayerInput();
		Gdx.input.setInputProcessor(CrossbowKnight.playerInput);
		// Load all audio files.
		Audio.loadAudio();

		// Set up a test level.
		testLevel = LevelFactory.getLevelFromDisk("MAIN_HALL");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// For now, lets force exit on press of Q.
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) { Gdx.app.exit(); }
		
		// Update the level.
		testLevel.update();
		
		// Draw the level.
		batch.begin();
		testLevel.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () { batch.dispose(); }

	/**
	 * Get player input.
	 * @return player input.
     */
	public static PlayerInput getPlayerInput() { return CrossbowKnight.playerInput; }
}
