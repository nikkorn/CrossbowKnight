package com.dumbpug.crossbowknight.entities.characters;

/**
 * Represents the health status of a character. 
 * @author nikolas.howard
 */
public class HealthStatus {
	/** The total health of the player. */
    private int health;
    /** The maximum health of the player. */
    private int maxHealth;
    
    /**
     * Apply damage.
     * @param damage
     */
    public void applyDamage(int damage) {
    	this.health -= damage;
		if(health <= 0) {
			// Can't have minus number for health. 
			health = 0;
		} 
    }
    
    /**
     * Apply health.
     * @param health
     */
    public void applyHealth(int health) {
    	this.health += health;
		if(health > this.maxHealth) {
			// Can't have health over the max health. 
			health = this.maxHealth;
		} 
    }
    
    /**
     * Is health depleted.
     * @return depleted
     */
    public boolean isHealthDepleted() {
    	return health == 0;
    }

    /**
     * Get the maximum health.
     * @return max health
     */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
     * Set the maximum health.
     * @param max health
     */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
