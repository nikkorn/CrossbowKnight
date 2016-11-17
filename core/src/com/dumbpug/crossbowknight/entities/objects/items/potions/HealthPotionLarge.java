package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A large health potion item.
 * @author nikolas.howard
 */
public class HealthPotionLarge extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.HEALTH_POTION_LARGE; }

	@Override
	public String getName() { return "Large Health Potion"; }

	@Override
	public String getDescription() { return "A potion which restores a large portion of health."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.UNCOMMON; }
}
