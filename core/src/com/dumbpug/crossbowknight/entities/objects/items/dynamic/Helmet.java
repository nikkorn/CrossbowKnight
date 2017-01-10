package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Material;

/**
 * Represents a helmet item.
 * Created by nik on 19/10/16.
 */
public abstract class Helmet extends DynamicItem {
	/** The material of the helmet. */
	private Material material;
	/** The defense buff of the shield. Cannot be more than 1 (100% damage absorbtion) */
	private float defenseBuff = 0;
	
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

	/**
	 * Get the defense buff of this helmet. 
	 * @return defense buff
	 */
	public float getDefenseBuff() { return defenseBuff; }

	/**
	 * Set the defense buff of this helmet.
	 * A defense buff cannot be more than 1 (100% damage absorbtion)
	 * @param defenseBuff
	 */
	public void setDefenseBuff(float defenseBuff) { 
		if(defenseBuff > 1) {
			this.defenseBuff = 1f; 
		} else {
			this.defenseBuff = defenseBuff; 
		}
	}
}
