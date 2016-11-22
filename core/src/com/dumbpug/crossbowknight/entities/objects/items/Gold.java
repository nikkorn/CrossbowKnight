package com.dumbpug.crossbowknight.entities.objects.items;

/**
 * A Gold item.
 * Created by nik on 19/10/16.
 */
public class Gold extends Item {

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return false; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public Item.ItemType getType() { return Item.ItemType.GOLD; }
    
    /**
	 * Get the category of the item.
	 * @return category.
	 */
	public ItemCategory getCategory() { return ItemCategory.DEFAULT; }
    
    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Gold"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return ""; }
}
