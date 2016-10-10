package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Projectile;
import com.dumbpug.crossbowknight.hud.dialog.DialogBox;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.entities.characters.Character;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** The name of this level. */
	private String name = "";
	/** All tiles in this level. */
	private ArrayList<Tile> levelTiles = new ArrayList<Tile>();
	/** The level camera. */
	private LevelCamera camera;
	/** The level drawer. */
	private LevelDrawer levelDrawer;
	/** The level physics world. */
	private LevelWorld levelWorld;
	/** The current dialog box. */
	private DialogBox currentDialogBox;
	
	// -----------------------------------
	// ---------- Level Entities ---------
	/** The Player. */
	private Player player;
	/** The Projectiles in the level. TODO Make a projectile list class dedicated to tracking these */
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	// -----------------------------------
	
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
		// Create our player. // TODO Add actual spawn point!!!
		player = new Player(50, 50) {
			@Override
			public void onWeaponFire(Projectile projectile) {
				super.onWeaponFire(projectile);
				Level.this.onWeaponFire(this, projectile);
			}
		};
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
		// ------------ Update the level world. -----------
		levelWorld.update();
		
		// ---------------------- Handle input. ---------------------
		player.processInput();
	}

	/**
	 * Called whenever a player/enemy fires their current weapon.
	 * @param character
	 * @param projectile
     */
	public void onWeaponFire(Character character, Projectile projectile) {
		// TODO We may need to check that generating projectile physics entities
		// partly in static blocks doesn't cause a problem here.
		// The player was able to fire! Play twang sound effect.
		Audio.getSoundEffect(Audio.SoundEffect.BLIP_SELECT).play();
		// Add our projectile.
		projectiles.add(projectile);
		// Add our player to the level world.
		this.levelWorld.getPhysicsWorld().addBox(projectile.getPhysicsBox());
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
	 * Get the level  projectiles.
	 * @return projectiles.
	 */
	public ArrayList<Projectile> getProjectiles() { return projectiles; }

	/**
	 * Get the current dialog box.
	 * @return dialog box.
     */
	public DialogBox getCurrentDialogBox() { return currentDialogBox; }

	/**
	 * Set all level tiles.
	 * @param levelTiles
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
