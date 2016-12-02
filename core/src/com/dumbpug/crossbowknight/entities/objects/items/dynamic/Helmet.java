package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Material;

/**
 * Represents a helmet item.
 * Created by nik on 19/10/16.
 */
public abstract class Helmet extends DynamicItem {
	/** The material of the helmet. */
	private Material material;
	
	/**
	 * Create a new instance of the Helmet Class.
	 * @param id
	 * @param material
	 */
	public Helmet(int id, Material material) {
		this.material = material;
		// Set the type of this item, which can be deduced using its material and id.
		this.setType(ItemType.valueOf("HELMET_" + getMaterial() + "_" + id));
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
	public ItemCategory getCategory() { return ItemCategory.HELMET; }
	
	/**
	 * Is this item quantifiable?
	 * @return quantifiable
     */
	public boolean isQuantifiable() { return false; }
}
