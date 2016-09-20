package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a living being.
 * @author nikolas.howard
 */
public abstract class Character {
	/** The health status of this character. */
	private HealthStatus healthStatus;
	/** The stats of this character. */
	private Stats stats;
	
	/**
	 * Get health status.
	 * @return health status.
	 */
	public HealthStatus getHealthStatus() {
		return this.healthStatus;
	}
	
	/**
	 * Set health status.
	 * @param healthStatus
	 */
	public void setHealthStatus(HealthStatus healthStatus) {
		this.healthStatus = healthStatus;
	}
	
	/**
	 * Get stats.
	 * @return stats.
	 */
	public Stats getStats() {
		return this.stats;
	}
	
	/**
	 * Set stats.
	 * @param stats.
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	/**
	 * Draw the character.
	 * @param batch
	 * @param offsetx
	 * @param offsety
	 */
	public abstract void draw(SpriteBatch batch, float offsetX, float offsetY);
}
