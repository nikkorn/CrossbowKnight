package com.dumbpug.crossbowknight.entities.objects.projectiles;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.LevelWorld;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPMath;

/**
 * Manager for in-game projectiles.
 * @author nikolas.howard
 */
public class ProjectilePool {
	/** The list of projectiles. */
	private ArrayList<Projectile> projectiles;
	/** The level world. */
	private LevelWorld levelWorld;
	
	/**
	 * Create a new instance of the ProjectilePool class.
	 */
	public ProjectilePool(LevelWorld levelWorld) {
		projectiles     = new ArrayList<Projectile>();
		this.levelWorld = levelWorld;
	}
	
	/**
	 * Add a projectile to the pool.
	 * Returns true if the projectile was successfully added.
	 * @param projectile
	 * @returns success
	 */
	public boolean add(Projectile projectile) {
		// We cannot add projectiles at positions that intersect existing static boxes as shit gets nuts.
		if(doesProjectileIntersectStaticBox(projectile)) {
			return false;
		}
		// Add our projectile.
		projectiles.add(projectile);
		// Add our projectile physics box to the level world.
		levelWorld.getPhysicsWorld().addBox(projectile.getPhysicsBox());
		// We were able to add our projectile.
		return true;
	}
	
	/**
	 * Check that the physics box of this projectile doesn't 
     * intersect with any existing static boxes.
	 * @param projectile
	 * @return intersects
	 */
	private boolean doesProjectileIntersectStaticBox(Projectile projectile) {
		for(NBPBox existingBox : levelWorld.getPhysicsWorld().getWorldBoxes()) {
			if(existingBox.getType() == NBPBoxType.STATIC) {
				if(NBPMath.doBoxesCollide(existingBox, projectile.getPhysicsBox())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Draw everything in our projectile pool.
	 * @param batch
	 */
	public void draw(SpriteBatch batch, float offsetX, float offsetY) {
		// Draw the projectiles.
		for(Projectile projectile : projectiles) {
			projectile.draw(batch, offsetX, offsetY);
		}
	}
}
