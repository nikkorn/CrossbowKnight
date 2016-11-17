package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A strength potion item.
 * @author nikolas.howard
 */
public class StrengthPotion extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.STRENGTH_POTION; }

	@Override
	public String getName() { return "Strength Potion"; }

	@Override
	public String getDescription() { return "A potion used to increase strength."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.RARE; }
}

