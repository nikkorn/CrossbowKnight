package com.dumbpug.crossbowknight.effects;

import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * Represents an effect which can cause damage over time.
 * @author nikolas.howard
 */
public class ProlongedDamageEffect extends DamageEffect {
	/** The time at which this effect was first applied to a character. */
	private long startTime = 0;
	/** The intervals at which this effect is applied. */
	private int intervalInSeconds;
	/** The last time that damage was applied at an interval. */
	private long lastDamageApplicationTime;
	/** The duration of this prolonged effect. */
	private int durationInSeconds;
	/** The amount of damage this effect causes at intervals. */
	private int damageAppliedAtIntervals = 0;
	
	/**
	 * Apply this damage effect to a player character.
	 * @param player
	 * @return whether the effect is finished.
	 */
	public boolean applyToPlayer(Player player) { return this.apply(player); }
	
	/**
	 * Apply this damage effect to a character
	 * @param character
	 * @return whether the effect is finished.
	 */
	public boolean apply(com.dumbpug.crossbowknight.entities.characters.Character character) {
		// If this effect has never been applied before, set the start time.
		// This is needed to determine the lifetime of the prolonged effect.
		if(startTime == 0) {
			startTime                 = System.currentTimeMillis();
			lastDamageApplicationTime = startTime;
			// Damage character with initial damage.
			character.getHealthStatus().applyDamage(getIntiallyAppliedDamage());
		}
		// Check to see if we have passed an interval and damage if so. 
		// TODO This needs to be made to work with game pausing.
		if(lastDamageApplicationTime < (System.currentTimeMillis() - (getIntervalInSeconds()*1000)))  {
			lastDamageApplicationTime = System.currentTimeMillis();
			character.getHealthStatus().applyDamage((int) damageAppliedAtIntervals);
		}
		// Only return true if the duration of this effect has expired.
		return System.currentTimeMillis() >= (startTime + (getDurationInSeconds()*1000));
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

	/**
	 * Get the duration of this effect in seconds.
	 * @return duration in seconds
	 */
	public long getDurationInSeconds() { return durationInSeconds; }

	/**
	 * Set the duration of this effect in seconds.
	 * @param durationInSeconds
	 */
	public void setDurationInSeconds(int seconds) { this.durationInSeconds = seconds; }

	/**
	 * Get the damage interval of this effect in seconds.
	 * @return damage interval in seconds.
	 */
	public long getIntervalInSeconds() { return intervalInSeconds; }

	/**
	 * Set the damage interval of this effect in seconds.
	 * @param intervalInSeconds
	 */
	public void setIntervalInSeconds(int seconds) { this.intervalInSeconds = seconds; }
}
