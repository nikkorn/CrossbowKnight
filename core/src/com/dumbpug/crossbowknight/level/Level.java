package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** The name of this level */
	private String name = "";
	/** All tiles in this level. */
	private ArrayList<Tile> levelTiles = new ArrayList<Tile>();
	/** The level camera. */
	private LevelCamera camera;
	/** The level drawer. */
	private LevelDrawer levelDrawer;
	
	// ----TEST---- Tester offsets
	float offsetx = 0f;
	float offsety = 0f;
	
	/**
	 * Create a new instance of the Level class.
	 */
	public Level() {
		// Create our level drawer.
		this.levelDrawer = new LevelDrawer(this);
		// Create our level camera.
		// TODO Replace with correct hook-up.
		this.camera = new LevelCamera(new LevelCameraPositionProvider() {
			@Override
			public float getXPositon() { return offsetx; } 
			@Override
			public float getYPositon() { return offsety; }
		});
	}
	
	/**
	 * Update the level.
	 */
	public void update() {
		// Step the level here!!!!
		
		// Test camera movement
		if(Gdx.input.isKeyPressed(Input.Keys.W)) { offsety++; }
		if(Gdx.input.isKeyPressed(Input.Keys.S)) { offsety--; }
		if(Gdx.input.isKeyPressed(Input.Keys.A)) { offsetx--; }
		if(Gdx.input.isKeyPressed(Input.Keys.D)) { offsetx++; }
	}

	/**
	 * Get all level tiles.
	 * @return level tiles.
	 */
	public ArrayList<Tile> getLevelTiles() { return levelTiles; }

	/**
	 * Set all level tiles.
	 * @param levelTiles.
	 */
	public void setLevelTiles(ArrayList<Tile> levelTiles) { this.levelTiles = levelTiles; }

	/**
	 * Get the name of this level.
	 * @return name
	 */
	public String getName() { return name; }

	/**
	 * Set the name of this level.
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * Get the level camera.
	 * @return level camera.
	 */
	public LevelCamera getLevelCamera() { return camera; }

	/**
	 * Set the level camera.
	 * @param level camera.
	 */
	public void setLevelCamera(LevelCamera camera) { this.camera = camera; }
	
	/**
	 * Draw this level
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw our level using our level drawer.
		levelDrawer.draw(batch);	
	}
}
