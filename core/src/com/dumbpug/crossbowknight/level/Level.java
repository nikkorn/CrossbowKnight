package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.BasicBolt;
import com.dumbpug.crossbowknight.entities.objects.items.Gold;
import com.dumbpug.crossbowknight.entities.objects.items.HealthPotion;
import com.dumbpug.crossbowknight.entities.objects.items.HealthPotionLarge;
import com.dumbpug.crossbowknight.entities.objects.items.HealthPotionSuper;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPhysicsBox;
import com.dumbpug.crossbowknight.entities.objects.items.Key;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Projectile;
import com.dumbpug.crossbowknight.tiles.Tile;

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
	
	// -----------------------------------
	// ---------- Level Entities ---------
	/** The Player. */
	private Player player;
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
			public boolean onWeaponFire(Projectile projectile) {
				// Attempt to ad this players fired projectile to the levels projectile pool.
				// Also, return whether we were successfully able to do this.
				return levelWorld.getProjectilePool().add(projectile);
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
		// --------------- Handle input. ------------------
		player.processInput();
		
		// ...
		
		// TEST ADDING AN ITEM TO THE WORLD TODO REMOVE!
		if(CrossbowKnight.getPlayerInput().isCycleLeftButtonPressed()) {
			Item item = null;
			// Randomly generate an item!
			Item.ItemType type = Item.ItemType.values()[new Random().nextInt(Item.ItemType.values().length)];
			switch(type) {
			case BOLT_BASIC:
				item = new BasicBolt();
				break;
			case GOLD:
				item = new Gold();
				item.setQuantity(new Random().nextInt(100));
				break;
			case HEALTH_POTION:
				item = new HealthPotion();
				break;
			case HEALTH_POTION_LARGE:
				item = new HealthPotionLarge();
				break;
			case HEALTH_POTION_SUPER:
				item = new HealthPotionSuper();
				break;
			case KEY:
				item = new Key();
				break;
			default:
				break;
			}
			item.setItemPhysicsBox(new ItemPhysicsBox(item, player.getPlayerPhysicsBox().getX(), player.getPlayerPhysicsBox().getY() + 50));
			levelWorld.getItemPool().add(item);
		}
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
	 * Get the player.
	 * @return player.
	 */
	public Player getPlayer() { return player; }
	
	/**
	 * Get all level tiles.
	 * @return level tiles.
	 */
	public ArrayList<Tile> getLevelTiles() { return levelTiles; }

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
	 * Get the level world.
	 * @return level world.
	 */
	public LevelWorld getLevelWorld() { return this.levelWorld; }
	
	/**
	 * Draw this level
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw our level using our level drawer.
		levelDrawer.draw(batch);	
	}
}
