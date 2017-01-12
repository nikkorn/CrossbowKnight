package com.dumbpug.crossbowknight.entities.characters.player;

import java.util.ArrayList;
import java.util.Iterator;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemType;

/**
 * Represents the players inventory.
 * @author nikolas.howard
 */
public class Inventory {
	/** The list of items in the inventory. */
	private ArrayList<Item> items;
	/** The amount of gold. */
	private int gold = 0;
	/** The number of keys. */
	private int keys = 0;
	
	/**
	 * Create a new instance of the Inventory class.
	 */
	public Inventory() {
		// Create a new items list.
		items = new ArrayList<Item>();
	}
	
	/**
	 * Add an item to the Inventory.
	 * Returns whether the item was successfully 
	 * added to the items.
	 * @param item
	 */
	public boolean add(Item item) {
		// If this item is gold or a key then it is not actually treated as an item.
		if(item.getType() == ItemType.GOLD) {
			gold += item.getQuantity();
			return true;
		} else if(item.getType() == ItemType.KEY) {
			keys++;
			return true;
		} else {
			// Is this item quantifiable? If it is then we will simply 
			// increase the quantity of the item in the inventory IF
			// it already exists. Otherwise, it will take its own slot.
			if(item.isQuantifiable()) {
				// Try to find the same item in the inventory.
				for(Item existingItem : items) {
					if(item.getType() == existingItem.getType()) {
						// We found an item of the same type. 
						// We cannot exceed the slot size limit though.
						if(existingItem.getQuantity() < C.INVENTORY_MAX_SLOT_QUANTITY) {
							// Add the quantity.
							int quantity = existingItem.getQuantity() + item.getQuantity();
							// Cap the quantity if we need to.
							quantity = quantity > C.INVENTORY_MAX_SLOT_QUANTITY ? C.INVENTORY_MAX_SLOT_QUANTITY : quantity;
							existingItem.setQuantity(quantity);
							// We were able to add some items.
							return true;
						} else {
							// This item slot if full so we cannot do anything!
							return false;
						}
					}
				}
				// We were not able to find the the same item type. 
				// We have to add it in a new slot.
				// Do not over-fill the inventory!
				if(items.size() == C.INVENTORY_MAX_SIZE) {
					return false;
				}
				items.add(item);
			} else {
				// Do not over-fill the inventory!
				if(items.size() == C.INVENTORY_MAX_SIZE) {
					return false;
				}
				items.add(item);
			}
		}
		return false;
	}
	
	/**
	 * Remove the item from the Inventory, regardless of its quantity.
	 * @param item
	 */
	public void remove(Item item) {
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
		    Item currentItem = iterator.next();
		    if(currentItem.equals(item)) {
		        iterator.remove();
		    }
		}
	}
	
	/**
	 * Remove a quantity of item from the Inventory.
	 * @param item
	 */
	public void remove(Item item, int quantity) {
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
		    Item currentItem = iterator.next();
		    if(currentItem.equals(item)) {
		    	if(item.isQuantifiable() && (quantity < item.getQuantity())) {
		    		// We are just removing some of the items quantity so it doesn't actually need to be removed.
		    		item.setQuantity(item.getQuantity() - quantity);
		    	} else {
		    		// Either this item is not quantifiable or we are removing all of the items quantity.
		    		iterator.remove();
		    	}
		    }
		}
	}

	/**
	 * Get the inventory items.
	 * @return items
     */
	public ArrayList<Item> getItems() { return items; }

	/**
	 * Get the number of keys.
	 * @return keys
	 */
	public int getNumberOfKeys() { return keys; }
	
	/**
	 * Get the gold amount.
	 * @return gold
	 */
	public int getGold() { return gold; }
}
