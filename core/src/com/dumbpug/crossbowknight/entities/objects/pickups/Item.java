package com.dumbpug.crossbowknight.entities.objects.pickups;

/**
 * Base class for Items.
 * @author nikolas.howard
 */
public abstract class Item {
	
	/**
	 * Type of item.
	 * @author nikolas.howard
	 */
	public enum ItemType {
		// --- Misc ---
		//GOLD,
		//KEY,
		
		// --- Projectiles ---
		BOLT_BASIC,
		
		// --- Consumables ---
		//HEALTH_POTION
	}
	
	/**
	 * Called when the item was collected by the player.
	 */
	public abstract void onPickup();
	
	/**
	 * Get the type of the pickup.
	 * @return
	 */
	public abstract ItemType getType();
}
