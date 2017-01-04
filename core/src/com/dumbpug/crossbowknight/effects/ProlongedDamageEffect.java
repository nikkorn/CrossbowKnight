package com.dumbpug.crossbowknight.effects;

/**
 * Represents an effect which can cause damage over time.
 * @author nikolas.howard
 */
public class ProlongedDamageEffect extends DamageEffect {
	/** The time at which this effect was first applied to a character. */
	public long startTime = 0;
	/** The intervals at which this effect is applied. */
	public long interval;
	/** The last time that damage was applied at an interval. */
	public long lastDamageApplicationTime;
	/** The duration of this prolonged effect. */
	public long duration;
	/** The amount of damage this effect causes at intervals. */
	private int damageAppliedAtIntervals = 0;
	
	/**
	 * Apply this damage effect to a character
	 * @param character
	 * @return whether the effect is finished.
	 */
	public boolean apply(Character character) {
		// If this effect has never been applied before, set the start time.
		// This is needed to determine the lifetime of the prolonged effect.
		if(startTime == 0) {
			startTime = System.currentTimeMillis();
			// As this is the first time this effect has been applied, do initial damage
			// TODO Damage character with initial damage.
		}
		
		// TODO check to see if we have passed an interval and damage if so.
		
		// Only return true if the duration of this effect has expired.
		return System.currentTimeMillis() >= (startTime + duration);
	}
	
	/**
	 * Get the amount of damage which is applied at the defined intervals.
	 * @return damage
	 */
	public int getDamageAppliedAtIntervals() { return damageAppliedAtIntervals; }
	
	/**
	 * Set the amount of damage which is applied at the defined intervals.
	 * @param damageA
	 */
	public void setDamageAppliedAtIntervals(int damage) { this.damageAppliedAtIntervals = damage; }
	
	/**
	 * Is this a prolonged damage effect or is it applied instantly.
	 * @return is prolonged.
	 */
	public boolean isProlonged() { return true; }
}
