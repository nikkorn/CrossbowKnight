package com.dumbpug.crossbowknight.entities.objects.items;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.LevelWorld;

/**
 * Manager for in-game items.
 * @author nikolas.howard
 */
public class ItemPool {
	/** The list of items. */
	private ArrayList<Item> items;
	/** The level world. */
	private LevelWorld levelWorld;
	
	/**
	 * Create a new instance of the ItemPool class.
	 */
	public ItemPool(LevelWorld levelWorld) {
		items           = new ArrayList<Item>();
		this.levelWorld = levelWorld;
	}
	
	/**
	 * Add a item to the pool.
	 * Returns true if the item was successfully added.
	 * @param projectile
	 */
	public void add(Item item) {
		// Add our item.
        items.add(item);
		// Add our items physics box to the level world.
		levelWorld.getPhysicsWorld().addBox(item.getItemPhysicsBox());
	}
	
	/**
	 * Draw everything in our item pool.
	 * @param batch
	 */
	public void draw(SpriteBatch batch, float offsetX, float offsetY) {
		// Draw the items.
		for(Item item : items) {
			item.draw(batch, offsetX, offsetY);
		}
	}

	/**
	 * Iterate over the item pool and remove any items which 
	 * are no longer needed. At the moment, these are items 
	 * that have inactive physics boxes, likely due to the fact
	 * that the player has picked the item up.
	 */
	public void removeInactiveItems() {
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
		    Item item = iterator.next();
		    // Remove this item from the pool if it is no longer needed. (i.e. picked up)
		    if(!item.getItemPhysicsBox().isActive()) {
		        iterator.remove();
		    }
		}
	}
}
