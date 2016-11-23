package com.dumbpug.crossbowknight.entities.objects.items.shields;

import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * Represents a shield item.
 * Created by nik on 19/10/16.
 */
public abstract class Shield extends Item {
	/** The total resilience of the shield. */
	/** The current resilience of the shield. */
	/** The defense buff of the shield. */
	
	/**
	 * Get the category of the item.
	 * @return category.
	 */
	public ItemCategory getCategory() { return ItemCategory.SHIELD; }
	
	/**
	 * Is this item quantifiable?
	 * @return quantifiable
     */
	public boolean isQuantifiable() { return false; }
}
