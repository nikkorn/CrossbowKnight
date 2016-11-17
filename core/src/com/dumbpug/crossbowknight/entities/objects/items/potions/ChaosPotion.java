package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A chaos potion item.
 * @author nikolas.howard
 */
public class ChaosPotion extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.CHAOS_POTION; }

	@Override
	public String getName() { return "Chaos Potion"; }

	@Override
	public String getDescription() { return "Brewed by a playful demon, its effect is unknown. Care to roll the dice?"; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.ULTRA_RARE; }
}
