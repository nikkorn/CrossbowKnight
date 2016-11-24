package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Material;

/**
 * Represents a shield item.
 * Created by nik on 19/10/16.
 */
public abstract class Shield extends DynamicItem {
	/** The total durability of the shield. */
	/** The current durability of the shield. */
	/** The defense buff of the shield. */
	/** The material of the shield. */
	private Material material;
	
	/**
	 * Create a new instance of the Shield Class.
	 * @param id
	 * @param material
	 */
	public Shield(int id, Material material) {
		this.material = material;
		// Set the type of this item, which can be deduced using its material and id.
		this.setType(ItemType.valueOf("SHIELD_" + getMaterial() + "_" + id));
	}
	
	/**
	 * Get the material of this item.
	 * @return material
	 */
	public Material getMaterial() { return this.material; }
	
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
