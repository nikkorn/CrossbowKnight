package com.dumbpug.crossbowknight.entities.characters.player;

import com.dumbpug.crossbowknight.entities.objects.items.Ammo;
import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * Handles the usage of equipment.
 * @author nikolas.howard
 */
public class EquipmentUsage {
	
	/**
	 * Called when an item is actually able to be used.
	 * @param player
	 * @param item
	 */
	public static void onItemUse(Player player, Item item) {
		// Use the item for real now.
		switch(item.getType()) {
			case HEALTH_POTION:
				break;
			case HEALTH_POTION_LARGE:
				break;
			case HEALTH_POTION_SUPER:
				break;
			case KEY:
				break;
			default:
				break;
		}
		// TODO Play an item usage sound!
	}
	
	/**
	 * Called when ammo is actually able to be used.
	 * @param player
	 * @param item
	 */
	public static void onAmmoUse(Player player, Ammo ammo) {
		// TODO Use the ammo for real now.
	}
}
