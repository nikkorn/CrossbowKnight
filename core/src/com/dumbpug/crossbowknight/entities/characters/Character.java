package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPPoint;

/**
 * Represents a living being.
 * @author nikolas.howard
 */
public abstract class Character {
	/** The physics box for this character. */
    protected CharacterPhysicsBox<? extends Character> characterPhysicsBox;
	/** The health status of this character. */
	private HealthStatus healthStatus = new HealthStatus();
	/** The stats of this character. */
	private Stats stats = new Stats();
	
	/**
	 * Get health status.
	 * @return health status.
	 */
	public HealthStatus getHealthStatus() { return this.healthStatus; }
	
	/**
	 * Set health status.
	 * @param healthStatus
	 */
	public void setHealthStatus(HealthStatus healthStatus) { this.healthStatus = healthStatus; }
	
	/**
	 * Get stats.
	 * @return stats.
	 */
	public Stats getStats() { return this.stats; }
	
	/**
	 * Set stats.
	 * @param stats
	 */
	public void setStats(Stats stats) { this.stats = stats; }
	
	/**
	 * Called by physics entity when the player box lands on a static floor.
	 */
	public void onLanding() {
		// The character landed so play a thump.
		Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
	}
	
	/**
	 * Get this characters current point of origin.
	 * @return players point of origin.
	 */
	public NBPPoint getCurrentOriginPoint() {
		// This characters point of origin will match the origin of its physics box.
		return characterPhysicsBox.getCurrentOriginPoint();
	}
	
	/**
	 * Get whether the character is currently idle (not moving at all)
	 * @return is idle.
	 */
	public boolean isIdle() {
		return (characterPhysicsBox.getVelx() < 0.2f && characterPhysicsBox.getVelx() > -0.2f) &&
				(characterPhysicsBox.getVely() < 0.2f && characterPhysicsBox.getVely() > -0.2f);
	}
	
	/**
	 * Get whether the character is touching the floor (if false then the character is airborne).
	 * @return is touching floor.
	 */
	public boolean isTouchingFloor() { return characterPhysicsBox.isTouchingFloor(); }

	/**
	 * Get this characters physics box.
	 * @return characterPhysicsBox
	 */
	public CharacterPhysicsBox<? extends Character> getPhysicsBox() { return characterPhysicsBox; }
	
	/**
	 * Handles the character being pushed by a force bloom in the world.
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 * @param distance
	 */
	public void onPushedByForce(NBPBloom bloom, float angleOfForce, float force, float distance) {}
	
	/**
	 * Draw the character.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public abstract void draw(SpriteBatch batch, float offsetX, float offsetY);
}
