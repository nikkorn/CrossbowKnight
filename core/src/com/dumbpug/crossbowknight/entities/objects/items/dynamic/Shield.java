package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Material;

/**
 * Represents a shield item.
 * Created by nik on 19/10/16.
 */
public class Shield extends DynamicItem {
	/** The total durability of the shield. */
	private float totalDurability = 0;
	/** The current durability of the shield. */
	private float currentDurability = 0;
	/** The defense buff of the shield. */
	private float defenseBuff = 0;
	/** The material of the shield. */
	private Material material;
	
	/**
	 * Create a new instance of the Shield Class.
	 * @param id
	 * @param material
	 */
	public Shield(int id, Material material) {
		this.material          = material;
		this.setTotalDurability(totalDurability);
		this.setCurrentDurability(totalDurability);
		// Set the type of this item, which can be deduced using its material and id.
		this.setType(ItemType.valueOf("SHIELD_" + getMaterial() + "_" + id));
	}
	
	/**
	 * Get the defense buff of this shield. 
	 * @return defense buff
	 */
	public float getDefenseBuff() { return defenseBuff; }

	/**
	 * Set the defense buff of this shield.
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

	/**
	 * Is this item displayed in the inventory menu?
	 * @return isDisplayedInInventory
	 */
	public boolean isDisplayedInInventory() { return true; }

	/**
	 * Get this shields total durability. 
	 * @return total durability
	 */
	public float getTotalDurability() { return totalDurability; }

	/**
	 * Set this shields total durability. 
	 * @param totalDurability
	 */
	public void setTotalDurability(float totalDurability) { this.totalDurability = totalDurability; }

	/**
	 * Get this shields current durability. 
	 * @return current Durability
	 */
	public float getCurrentDurability() { return currentDurability; }

	/**
	 * Set this shields current durability. 
	 * @param currentDurability
	 */
	public void setCurrentDurability(float currentDurability) { this.currentDurability = currentDurability; }
}
