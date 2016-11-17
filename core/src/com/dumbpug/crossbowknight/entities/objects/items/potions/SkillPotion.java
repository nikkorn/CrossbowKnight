package com.dumbpug.crossbowknight.entities.objects.items.potions;

/**
 * A skill potion item.
 * @author nikolas.howard
 */
public class SkillPotion extends Potion {

	@Override
	public boolean isDisplayedInInventory() { return true; }

	@Override
	public ItemType getType() { return ItemType.SKILL_POTION; }

	@Override
	public String getName() { return "Skill Potion"; }

	@Override
	public String getDescription() { return "A sickly sweet smelling potion which temporarily boosts a skill."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.RARE; }
}

