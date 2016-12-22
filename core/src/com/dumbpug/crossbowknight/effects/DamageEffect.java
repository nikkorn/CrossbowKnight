package com.dumbpug.crossbowknight.effects;

/**
 * Represents an effect which can cause damage.
 * @author nikolas.howard
 */
public class DamageEffect {
	/** The amount of damage this effect causes when first applied. */
	private int intiallyAppliedDamage = 0;
	/** The type damage this effect applies. */
	private DamageType type = DamageType.NORMAL;
	
	/**
	 * Get amount of damage this effect causes when first applied. 
	 * @return intiallyAppliedDamage.
	 */
	public int getIntiallyAppliedDamage() { return intiallyAppliedDamage; }

	/**
	 * Set amount of damage this effect causes when first applied. 
	 * @param intiallyAppliedDamage
	 */
	public void setIntiallyAppliedDamage(int intiallyAppliedDamage) { this.intiallyAppliedDamage = intiallyAppliedDamage; }
	
	/**
	 * Is this a prolonged damage effect or is it applied instantly.
	 * @return is prolonged.
	 */
	public boolean isProlonged() { return false; }

	/**
	 * Get the type of this effect.
	 * @return type.
	 */
	public DamageType getType() { return type; }

	/**
	 * Set the type of this effect.
	 * @param type
	 */
	public void setType(DamageType type) { this.type = type; }
}
