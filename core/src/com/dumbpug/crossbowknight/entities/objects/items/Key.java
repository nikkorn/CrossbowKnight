package com.dumbpug.crossbowknight.entities.objects.items;

/**
 * A key item.
 * Created by nik on 19/10/16.
 */
public class Key extends Item {

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return false; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.KEY; }
    
    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "A Key"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return ""; }
	
	/**
	 * Keys are super rare to get from normal spawns as the game
	 * would be too easy if they were common.
	 */
	@Override
	public ItemRarity getRarity() { return ItemRarity.MYTHICAL; }
}
