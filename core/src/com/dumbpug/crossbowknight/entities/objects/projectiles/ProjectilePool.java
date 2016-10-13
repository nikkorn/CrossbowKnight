package com.dumbpug.crossbowknight.entities.objects.projectiles;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Level;
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
	/** The level. */
	private Level level;
	
	/**
	 * Create a new instance of the ProjectilePool class.
	 */
	public ProjectilePool(Level level) {
		projectiles = new ArrayList<Projectile>();
		this.level  = level;
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
		level.getLevelWorld().getPhysicsWorld().addBox(projectile.getPhysicsBox());
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
		for(NBPBox existingBox : level.getLevelWorld().getPhysicsWorld().getWorldBoxes()) {
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
	public void draw(SpriteBatch batch) {
		// Draw the level projectiles.
		for(Projectile projectile : projectiles) {
			projectile.draw(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
		}
	}
}
