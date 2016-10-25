package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPool;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectilePool;
import com.dumbpug.nbp.NBPWorld;

/**
 * Represents the physical world.
 * @author nikolas.howard
 */
public class LevelWorld {
	/** The physics world of our level */
	private NBPWorld physicsWorld;
	/** The pool for items which are currently present in the level world. */
	private ItemPool itemPool;
	/** The Projectiles pool which holds all in-game projectiles for their lifetime. */
	private ProjectilePool projectilePool;
	
	/**
	 * Create a new instance of the LevelWorld class.
	 */
	public LevelWorld() {
		// Create our items list.
		this.itemPool = new ItemPool(this);
		// Create our projectile pool.
		this.projectilePool = new ProjectilePool(this);
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
		
		// ...
		
	}
	
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
		this.physicsWorld.addBox(player.getPlayerPhysicsBox());
	}
}
