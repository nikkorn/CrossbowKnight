package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A large health potion item.
 * @author nikolas.howard
 */
public class HealthPotionSuper extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.HEALTH_POTION_SUPER; }

	@Override
	public String getName() { return "Super Health Potion"; }

	@Override
	public String getDescription() { return "A very valuable potion which restores a vast portion of health."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.RARE; }
}
