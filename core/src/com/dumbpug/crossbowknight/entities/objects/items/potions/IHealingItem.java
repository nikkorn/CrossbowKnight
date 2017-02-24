package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * Represents common functionality for all healing items.
 * @author nikolas.howard
 */
public interface IHealingItem {
	
	/**
	 * Get the amount of HP to replenish.
	 * @return HP to replenish
	 */
	public int getHP();
}
