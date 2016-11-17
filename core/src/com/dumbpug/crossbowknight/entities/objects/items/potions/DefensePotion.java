package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A defense potion item.
 * @author nikolas.howard
 */
public class DefensePotion extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.DEFENSE_POTION; }

	@Override
	public String getName() { return "Defense Potion"; }

	@Override
	public String getDescription() { return "A potion used to increase defense."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.UNCOMMON; }
}

