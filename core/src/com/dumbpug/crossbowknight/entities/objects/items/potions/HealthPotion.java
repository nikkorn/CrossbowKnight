package com.dumbpug.crossbowknight.entities.objects.items.potions;

import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemType;

/**
 * A health potion item.
 * @author nikolas.howard
 */
public class HealthPotion extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.HEALTH_POTION; }

	@Override
	public String getName() { return "Health Potion"; }

	@Override
	public String getDescription() { return "A potion which restores a small portion of health."; }
}
