package com.dumbpug.crossbowknight.resources;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemType;

/**
 * Holds item resources.
 * @author nikolas.howard
 */
public class ItemResources {
	/** Singleton instance of item resources. */
	private static ItemResources instance;
	/** Maps which holds textures for item types. */
	private HashMap<ItemType, Texture> itemTextures = new HashMap<ItemType, Texture>();
	
	/**
	 * Creates an instance of the ItemResources class.
	 */
	private ItemResources() {
		// Populate our item type textures map.
		for(ItemType itemType : ItemType.values()) {
			String texturePath = "graphics/items/ITEM_" + itemType + ".png";
			itemTextures.put(itemType, new Texture(texturePath));
		}
	}
	
	/**
	 * Get our ItemResources singleton instance.
	 * @return ItemResources
	 */
	public static ItemResources getItemResources() {
		if(instance == null) {
			instance = new ItemResources();
		}
		return instance;
	}
	
	/**
	 * Get item texture.
	 * @param itemType
	 * @return texture
	 */
	public Texture getItemTexture(ItemType itemType) {
		return this.itemTextures.get(itemType);
	}
}
