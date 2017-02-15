package com.dumbpug.crossbowknight.entities.characters;

/**
 * Represents the stamina status of a character. 
 * @author nikolas.howard
 */
public class StaminaStatus {
	/** The character stats. */
	private Stats stats;
	/** The total stamina of the character. */
    private int stamina;
    /** The last time that a unit of stamina was replenished. */
	private long lastReplenishTime;
    
    /**
     * Create a new instance of the StaminaStatus class.
     * @param stats
     */
    public StaminaStatus(Stats stats) { 
    	this.stats   = stats; 
    	this.stamina = stats.getMaxStamina();
    }
    
    /**
     * Called every game tick in order to slowly replenish stamina
     */
    public void tick() {
    	// Check to see if we have passed an interval and need to replenish a unit of stamina.
		if(lastReplenishTime < (System.currentTimeMillis() - stats.getStaminaRefillRate())) {
			lastReplenishTime = System.currentTimeMillis();
			applyStamina(1);
		}
    }
    
    /**
     * Expend some stamina.
     * @param damage
     * @returns returns true if the amount we are trying to expend is available.
     */
    public boolean expendStamina(int staminaToExpend) {
    	// Return false if we don't have enough stamina to expend. 
    	if(staminaToExpend > this.stamina) {
    		return false;
    	}
    	// We have enough stamina to spare.
    	this.stamina -= staminaToExpend;
		return true;
    }
    
    /**
     * Apply stamina.
     * @param stamina
     */
    public void applyStamina(int stamina) {
    	int maxStamina = stats.getMaxStamina();
    	this.stamina += stamina;
		if(this.stamina > maxStamina) {
			// Can't have stamina over the max stamina. 
			this.stamina = maxStamina;
		} 
    }
    
    /**
     * Is stamina depleted.
     * @return depleted
     */
    public boolean isStaminaDepleted() { return stamina == 0; }
	
	/**
	 * Get the current stamina.
	 * @return stamina.
	 */
	public int getStamina() { return stamina; }
}
