package com.dumbpug.crossbowknight.leveleditor;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.input.DesktopPlayerInput;
import com.dumbpug.crossbowknight.input.PlayerInput;

public class CrossbowKnightLevelEditor extends ApplicationAdapter {
	/** The input processor. */
	private static PlayerInput playerInput;
	/** The games sprite batch. */
	private SpriteBatch batch;
	/** The level editor textures */
	private LevelEditorTextures levelEditorTextures;
	/** The editor tile offset. */
	private int editorTilePositionX = 0;
	private int editorTilePositionY = 0;
	/** The Tiles */
	private ArrayList<LevelTile> tiles = new ArrayList<LevelTile>();

	@Override
	public void create () {
		batch               = new SpriteBatch();
		levelEditorTextures = new LevelEditorTextures();
		playerInput         = new DesktopPlayerInput();
		Gdx.input.setInputProcessor(CrossbowKnightLevelEditor.playerInput);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// For now, lets force exit on press of Q.
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) { Gdx.app.exit(); }
		
		// For now, lets output our level on whatever is the cancel button in-game
		if(playerInput.isCancelButtonPressed()) { outputRawLevel(); }
		
		// Do we want to move our editor tile offset?
		if(playerInput.isUpButtonPressed()) { editorTilePositionY++; }
		if(playerInput.isDownButtonPressed()) { editorTilePositionY--; }
		if(playerInput.isLeftButtonPressed()) { editorTilePositionX--; }
		if(playerInput.isRightButtonPressed()) { editorTilePositionX++; }
		
		// Has a grid tile been selected?
		if(playerInput.isJumpButtonPressed()) {
			int posX = ((int) (Gdx.input.getX() / C.TILE_SIZE)) + editorTilePositionX;
			int posY = ((int) ((Gdx.graphics.getHeight() - Gdx.input.getY()) / C.TILE_SIZE)) + editorTilePositionY;
			onClickOnGridTile(posX, posY);
		}
	
		// Draw the editor.
		batch.begin();
		// Draw grid tiles.
		for(int x = 0; x < (C.TILE_GRID_SIZE*2); x++) {
			for(int y = 0; y < C.TILE_GRID_SIZE; y++) {
				batch.draw(levelEditorTextures.getGridTileTexture(), x*C.TILE_SIZE, y*C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		
		// ...
		
		batch.end();
	}
	
	/**
	 * Called when we click on a grid tile.
	 * @param x
	 * @param y
	 */
	public void onClickOnGridTile(int x, int y) {
		System.out.println("Got click at:");
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
	}
	
	/**
	 * Get a tile at a specific x/y position.
	 * @param x
	 * @param y
	 * @return
	 */
	public LevelTile getTileAt(int x, int y) {
		for(LevelTile tile: tiles) {
			if(tile.X == x && tile.Y == y) {
				return tile;
			}
		}
		// Didn't find the tile.
		return null;
	}
	
	/**
	 * Spit out the raw level JSON.
	 */
	private void outputRawLevel() {
		System.out.println("Output");
	}

	@Override
	public void dispose () { batch.dispose(); }
}
