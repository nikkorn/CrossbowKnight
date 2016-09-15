package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
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
		physicsWorld = new NBPWorld(C.PHYSICS_GRAVITY);
	}
}
