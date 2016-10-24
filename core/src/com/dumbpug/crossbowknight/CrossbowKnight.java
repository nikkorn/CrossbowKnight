package com.dumbpug.crossbowknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.gamestate.MainGame;
import com.dumbpug.crossbowknight.gamestate.MainMenu;
import com.dumbpug.crossbowknight.gamestate.Splash;
import com.dumbpug.crossbowknight.gamestate.StateManager;
import com.dumbpug.crossbowknight.gamestate.Title;
import com.dumbpug.crossbowknight.input.DesktopPlayerInput;
import com.dumbpug.crossbowknight.input.OuyaPlayerInput;
import com.dumbpug.crossbowknight.input.PlayerInput;

public class CrossbowKnight extends ApplicationAdapter {
	/** The games input processor. */
	private static PlayerInput playerInput;
	/** The games sprite batch. */
	private static SpriteBatch batch;
	/** The games state manager. */
	private static StateManager stateManager;
	
	/** The game states. */
	Title titleState;
	Splash splashState;
	MainMenu mainMenuState;
	MainGame gameState;

	@Override
	public void create () {
		// Create the games SpriteBatch.
		batch = new SpriteBatch();
		// Set the games input processor. Depends on whether we are running on the Ouya or Desktop.
		CrossbowKnight.playerInput = Ouya.runningOnOuya ? new OuyaPlayerInput() : new DesktopPlayerInput();
		Gdx.input.setInputProcessor(CrossbowKnight.playerInput);
		// Load all audio files.
		Audio.loadAudio();
		// Create our game states.
		titleState    = new Title();
		splashState   = new Splash();
		mainMenuState = new MainMenu();
		gameState     = new MainGame();
		// Create our game state manager and add our states.
		stateManager = new StateManager();
		stateManager.addState(titleState);
		stateManager.addState(splashState);
		stateManager.addState(mainMenuState);
		stateManager.addState(gameState);
		// Set the entry state.
		stateManager.setCurrentState(gameState);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// For now, lets force exit on press of Escape.
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { Gdx.app.exit(); }
		
		// Render the current state.
		stateManager.renderCurrentState();
	}
	
	@Override
	public void dispose () { batch.dispose(); }

	/**
	 * Get player input.
	 * @return player input.
     */
	public static PlayerInput getPlayerInput() { return CrossbowKnight.playerInput; }
	
	/**
	 * Get the shared sprite batch.
	 * @return sprite batch.
     */
	public static SpriteBatch getSpriteBatch() { return CrossbowKnight.batch; }
}
