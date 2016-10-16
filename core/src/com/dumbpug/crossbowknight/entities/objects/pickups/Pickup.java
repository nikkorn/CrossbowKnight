package com.dumbpug.crossbowknight.entities.objects.pickups;

/**
 * Base class for Pickups.
 * @author nikolas.howard
 */
public abstract class Pickup {
	
	/**
	 * Type of pickups.
	 * @author nikolas.howard
	 */
	public enum PickupType {
		// --- Misc ---
		//GOLD,
		//KEY,
		
		// --- Projectiles ---
		BOLT_BASIC,
		
		// --- Consumables ---
		//HEALTH_POTION
	}
	
	/**
	 * Called when the pickup was collected by the player.
	 */
	public abstract void onPickup();
	
	/**
	 * Get the type of the pickup.
	 * @return
	 */
	public abstract PickupType getType();
}
