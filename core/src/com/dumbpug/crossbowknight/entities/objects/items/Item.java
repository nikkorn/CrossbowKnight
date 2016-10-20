package com.dumbpug.crossbowknight.entities.objects.items;

/**
 * Base class for Items.
 * @author nikolas.howard
 */
public abstract class Item {
	/** The quantity of this item. -1 == not quantifiable. */
	private int quantity = 1;

	/**
	 * Type of item.
	 * @author nikolas.howard
	 */
	public enum ItemType {
		// --- Misc ---
		GOLD,
		KEY,
		
		// --- Projectiles ---
		BOLT_BASIC,
		
		// --- Consumables ---
		//HEALTH_POTION
	}

	/**
	 * Get the item quantity.
	 * @return quantity
     */
	public int getQuantity() { return this.quantity; }

	/**
	 * Set the item quantity
	 * @param quantity
     */
	public void setQuantity(int quantity) { this.quantity = quantity; }

	/**
	 * Is this item displayed in the inventory menu?
	 * @return isDisplayedInInventory
	 */
	public abstract boolean isDisplayedInInventory();

	/**
	 * Get the type of the item.
	 * @return type.
	 */
	public abstract ItemType getType();
}
