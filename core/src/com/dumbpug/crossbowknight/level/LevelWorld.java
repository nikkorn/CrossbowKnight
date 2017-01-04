package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPool;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectilePool;
import com.dumbpug.crossbowknight.particles.EmitterPool;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.nbp.NBPWorld;
import java.util.ArrayList;

/**
 * Represents the physical world.
 * @author nikolas.howard
 */
public class LevelWorld {
	/** All tiles in this level. */
	private ArrayList<Tile> levelTiles = new ArrayList<Tile>();
	/** The physics world of our level */
	private NBPWorld physicsWorld;
	/** The pool for items which are currently present in the level world. */
	private ItemPool itemPool;
	/** The Projectiles pool which holds all in-game projectiles for their lifetime. */
	private ProjectilePool projectilePool;
	/** The Emitter pool which holds all the active emitters in the level world. */
	private EmitterPool emitterPool;
	
	// TODO Add CharacterPool!!!!
	
	/**
	 * Create a new instance of the LevelWorld class.
	 */
	public LevelWorld() {
		// Create our items list.
		this.itemPool = new ItemPool(this);
		// Create our projectile pool.
		this.projectilePool = new ProjectilePool(this);
		// Create our emitters list.
		this.emitterPool = new EmitterPool();
		// Create our levels physics world.
		this.physicsWorld = new NBPWorld(C.PHYSICS_GRAVITY);
	}
	
	/**
	 * Update the level world.
	 */
	public void update() {
		// Firstly, update our physics world.
		physicsWorld.update();
		// Clear our item pool of any inactive items.
		itemPool.removeInactiveItems();
		// Remove any inactive projectiles from the projectile pool.
		projectilePool.removeInactiveProjectiles();
		// Update our Emitters.
		emitterPool.update();
		
		// ...
		
	}

	/**
	 * Set all level tiles.
	 * @param levelTiles
	 */
	public void setLevelTiles(ArrayList<Tile> levelTiles) {
		this.levelTiles = levelTiles;
		// Now that we have set our tiles, we can add and tile blocks to our physics world.
		for(Tile tile : levelTiles) {
			if(tile.getPhysicsBlock() != null) {
				this.getPhysicsWorld().addBox(tile.getPhysicsBlock());
			}
		}
	}

	/**
	 * Get all level tiles.
	 * @return level tiles.
	 */
	public ArrayList<Tile> getLevelTiles() { return levelTiles; }
	
	/**
	 * Get the level worlds item pool.
	 * @return item pool.
	 */
	public ItemPool getItemPool() { return this.itemPool; }
	
	/**
	 * Get the level worlds projectile pool.
	 * @return projectile pool.
	 */
	public ProjectilePool getProjectilePool() { return this.projectilePool; }
	
	/**
	 * Get the level worlds emitter pool.
	 * @return emitter pool.
	 */
	public EmitterPool getEmitterPool() { return this.emitterPool; }

	/**
	 * Get the physics world.
	 * @return physics world
	 */
	public NBPWorld getPhysicsWorld() {
		return physicsWorld;
	}

	/**
	 * Add the player to the physics world.
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.physicsWorld.addBox(player.getPhysicsBox());
	}
}
