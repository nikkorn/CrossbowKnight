package com.dumbpug.crossbowknight.entities.characters;

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
}
