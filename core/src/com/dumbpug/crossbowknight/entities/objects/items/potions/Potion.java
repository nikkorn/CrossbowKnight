package com.dumbpug.crossbowknight.entities.objects.items.potions;

import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * Represents a potion item.
 * Created by nik on 19/10/16.
 */
public abstract class Potion extends Item {
	/** The potency of this potion. */
	private PotionPotency potency = PotionPotency.WEAK;
	
	/**
	 * The potency of a potion.
	 * @author nikolas.howard
	 */
	public enum PotionPotency {
		STRONG,
		AVERAGE,
		WEAK
	}
	
	/**
	 * Set the potency of this potion.
	 * @param potency
	 */
	public void setPotency(PotionPotency potency) { this.potency = potency; }
	
	/**
	 * Get the potency of this potion.
	 * @return potency
	 */
	public PotionPotency getPotency() { return this.potency; }
	
	/**
	 * Get the category of the item.
	 * @return category.
	 */
	public ItemCategory getCategory() { return ItemCategory.CONSUMABLE; }
}
