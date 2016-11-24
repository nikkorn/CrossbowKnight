package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * Represents an item which is generated procedurally.
 * @author nikolas.howard
 */
public abstract class DynamicItem extends Item {
	/** The name of the item. */
	private String name = "";
	/** The description of the item. */
	private String description = "";
	/** The type of the item. */
	private ItemType type = null;
	/** The category of the item. */
	private ItemCategory category = null;
	/** The rarity of the item. */
	private ItemRarity rarity = null;
	
	/**
	 * Set the name of this dynamic item.
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * Set the description of this dynamic item.
	 * @param description
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * Set the type of this dynamic item.
	 * @param type
	 */
	public void setType(ItemType type) { this.type = type; }
	
	/**
	 * Set the category of this dynamic item.
	 * @param category
	 */
	public void setCategory(ItemCategory category) { this.category = category; }
	
	/**
	 * Set the rarity of this dynamic item.
	 * @param rarity
	 */
	public void setRarity(ItemRarity rarity) { this.rarity = rarity; }

	@Override
	public ItemType getType() { return type; }

	@Override
	public ItemCategory getCategory() { return category; }

	@Override
	public String getName() { return name; }

	@Override
	public String getDescription() { return description; }
	
	@Override
	public ItemRarity getRarity() { return rarity; }
}
