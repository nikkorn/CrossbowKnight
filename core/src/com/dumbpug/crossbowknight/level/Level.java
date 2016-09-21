package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
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
	/** The level physics world */
	private LevelWorld levelWorld;
	
	// -----------------------------------
	// ---------- Level Entities ---------
	/** The Player. */
	private Player player;
	// -----------------------------------
	
	
	// ----TEST---- Tester offsets
	float offsetx = 0f;
	float offsety = 0f;
	
	/**
	 * Create a new instance of the Level class.
	 */
	public Level() {
		// Create our level drawer.
		this.levelDrawer = new LevelDrawer(this);
		// Create our level world..
		this.levelWorld = new LevelWorld();
		// Initialise our player.
		initialisePlayer();
		// Set up our level camera.
		setupCamera();
	}
	
	/**
	 * Initialise the player.
	 */
	public void initialisePlayer() {
		// Create our player.
		player = new Player(50, 50); // TODO Add actual spawn point!!!
		// Add our player to the level world.
		this.levelWorld.addPlayer(player);
	}
	
	/**
	 * Set up the camera.
	 */
	public void setupCamera() {
		// Create our level camera. our camera is completely dependent
		// on the position of the character and the size of the screen.
		this.camera = new LevelCamera(new LevelCameraPositionProvider() {
			@Override
			public float getXPositon() { 
				return -((player.getCurrentOriginPoint().getX() * C.LAYOUT_MULTIPLIER) - (Gdx.graphics.getWidth()/2)); 
			} 
			@Override
			public float getYPositon() { 
				return -((player.getCurrentOriginPoint().getY() * C.LAYOUT_MULTIPLIER) - (Gdx.graphics.getHeight()/2)); 
			}
		});
	}
	
	/**
	 * Update the level.
	 */
	public void update() {
		// Step the level here!!!!
		
		// Update the level world.
		levelWorld.update();
		
		// Test camera movement
		if(Gdx.input.isKeyPressed(Input.Keys.D)) { player.moveRight(); }
		if(Gdx.input.isKeyPressed(Input.Keys.A)) { player.moveLeft(); }
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) { player.jump(); }
	}

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
	 * Get all level tiles.
	 * @return level tiles.
	 */
	public ArrayList<Tile> getLevelTiles() { return levelTiles; }
	
	/**
	 * Get the player.
	 * @return player.
	 */
	public Player getPlayer() { return player; }

	/**
	 * Set all level tiles.
	 * @param levelTiles.
	 */
	public void setLevelTiles(ArrayList<Tile> levelTiles) { 
		this.levelTiles = levelTiles; 
		// Now that we have set our tiles, we can add and tile blocks to our physics world.
		for(Tile tile : levelTiles) {
			if(tile.getPhysicsBlock() != null) {
				this.levelWorld.getPhysicsWorld().addBox(tile.getPhysicsBlock());
			}
		}
	}
	
	/**
	 * Draw this level
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw our level using our level drawer.
		levelDrawer.draw(batch);	
	}
}
