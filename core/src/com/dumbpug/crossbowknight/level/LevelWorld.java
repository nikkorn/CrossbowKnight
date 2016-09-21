package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.nbp.NBPWorld;

/**
 * Represents the physical world.
 * @author nikolas.howard
 */
public class LevelWorld {
	/** The physics world of our level */
	private NBPWorld physicsWorld;
	
	/**
	 * Create a new instance of the LevelWorld class.
	 */
	public LevelWorld() {
		// Create our levels physics world.
		this.physicsWorld = new NBPWorld(C.PHYSICS_GRAVITY);
	}
	
	/**
	 * Update the level world.
	 */
	public void update() {
		// Firstly, update our physics world.
		physicsWorld.update();
		
		// ...
		
	}

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
