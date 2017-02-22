package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.CharacterPool;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPool;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectilePool;
import com.dumbpug.crossbowknight.forces.Force;
import com.dumbpug.crossbowknight.particles.EmitterPool;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.TileInteractionFacilitator;
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
	/** The Character pool which holds all the living characters in the level world, including the player. */
	private CharacterPool characterPool;
	/** The tile interaction facilitator. */
	private TileInteractionFacilitator tileInteractionFacilitator = null;
	
	/**
	 * Create a new instance of the LevelWorld class.
	 */
	public LevelWorld() {
		// Create our items pool.
		this.itemPool = new ItemPool(this);
		// Create our characters pool.
		this.characterPool = new CharacterPool(this);
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
		// Update our characters.
		characterPool.update();
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
	public void setTiles(ArrayList<Tile> tiles) {
		this.levelTiles = tiles;
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
	public ArrayList<Tile> getTiles() { return levelTiles; }
	
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
	 * Get the level worlds character pool.
	 * @return character pool.
	 */
	public CharacterPool getCharacterPool() { return this.characterPool; }
	
	/**
	 * Apply a force to this level world.
	 * @param force
	 */
	public void applyForce(Force force) { this.getPhysicsWorld().addBloom(force); }

	/**
	 * Get the physics world.
	 * @return physics world
	 */
	public NBPWorld getPhysicsWorld() { return physicsWorld; }

	/**
	 * Get the tile interaction facilitator.
	 * @return tile interaction facilitator
	 */
	public TileInteractionFacilitator getTileInteractionFacilitator() { return tileInteractionFacilitator; }

	/**
	 * Set the tile interaction facilitator.
	 * @param tile interaction facilitator
	 */
	public void setTileInteractionFacilitator(TileInteractionFacilitator tileInteractionFacilitator) { this.tileInteractionFacilitator = tileInteractionFacilitator; }
}
