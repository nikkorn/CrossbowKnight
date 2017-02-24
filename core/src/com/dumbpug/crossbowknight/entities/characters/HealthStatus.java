package com.dumbpug.crossbowknight.entities.characters;

/**
 * Represents the health status of a character. 
 * @author nikolas.howard
 */
public class HealthStatus {
	/** The character stats. */
	private Stats stats;
	/** The total health of the character. */
    private int health;
    
    /**
     * Create a new instance of the HealthStatus class.
     * @param stats
     */
    public HealthStatus(Stats stats) { 
    	this.stats  = stats; 
    	this.health = stats.getMaxHealth();
    }
    
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
		if(this.health > stats.getMaxHealth()) {
			// Can't have health over the max health. 
			this.health = stats.getMaxHealth();
		} 
    }
    
    /**
     * Is health depleted.
     * @return depleted
     */
    public boolean isHealthDepleted() { return health == 0; }
	
	/**
	 * Get the current health.
	 * @return health.
	 */
	public int getHealth() { return health; }
}
