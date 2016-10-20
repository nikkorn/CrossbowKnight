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
}
