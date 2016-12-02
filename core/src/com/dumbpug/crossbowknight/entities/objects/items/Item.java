package com.dumbpug.crossbowknight.entities.objects.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * Base class for Items.
 * @author nikolas.howard
 */
public abstract class Item {
	/** The quantity of this item. -1 == infinite. */
	private int quantity = 1;
	/** The physics box that represents this item in the level world. */
	private ItemPhysicsBox itemPhysicsBox = null;
	
	/**
	 * The levels of rarity at which an item can be.
	 * @author nikolas.howard
	 */
	public enum ItemRarity {
		COMMON,
		UNCOMMON,
		RARE,
		ULTRA_RARE,
		MYTHICAL
	}

	/**
	 * Category of item.
	 * @author nikolas.howard
	 */
	public enum ItemCategory {
		DEFAULT,
		CONSUMABLE,
		AMMO,
		SHIELD,
		HELMET,
		LIMBS,
		STRING,
		STOCK,
		SIGHT
	}
	
	/**
	 * Type of item.
	 * @author nikolas.howard
	 */
	public enum ItemType {
		// --- Misc ---
		GOLD,
		KEY,
		
		// --- Projectiles ---
		BOLT_BASIC,
		BOLT_HEAVY,
		BOLT_EXPLOSIVE,
		BOLT_BARBED,
		BOLT_RUBBER,
		BOLT_ANTIQUE,
		BOLT_SLEEK,
		BOLT_IGNITED,
		
		// --- Consumables ---
		HEALTH_POTION,
		HEALTH_POTION_LARGE,
		HEALTH_POTION_SUPER, 
		CHAOS_POTION,
		DEFENSE_POTION, 
		STRENGTH_POTION, 
		SKILL_POTION,
		
		// --- Shields ---
		SHIELD_WOOD_0
	}
	
	/**
	 * Is this item quantifiable?
	 * @return quantifiable
     */
	public boolean isQuantifiable() { return true; }
	
	/**
	 * Get the rarity of this item. Common by default.
	 * @return item rarity
     */
	public ItemRarity getRarity() { return ItemRarity.COMMON; }

	/**
	 * Get the item quantity.
	 * @return quantity
     */
	public int getQuantity() { return this.quantity; }

	/**
	 * Set the item quantity
	 * @param quantity
     */
	public void setQuantity(int quantity) { this.quantity = quantity; }

	/**
	 * Is this item displayed in the inventory menu?
	 * @return isDisplayedInInventory
	 */
	public abstract boolean isDisplayedInInventory();

	/**
	 * Get the type of the item.
	 * @return type.
	 */
	public abstract ItemType getType();
	
	/**
	 * Get the category of the item.
	 * @return category.
	 */
	public abstract ItemCategory getCategory();
	
	/**
	 * Get the name of the item. 
	 * @return name
	 */
	public abstract String getName();
	
	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public abstract String getDescription();

	/**
	 * Get the physics box that represents this item in the level world.
	 * @return itemPhysicsBox
	 */
	public ItemPhysicsBox getItemPhysicsBox() { return itemPhysicsBox; }

	/**
	 * Set the physics box that represents this item in the level world.
	 * @param itemPhysicsBox
	 */
	public void setItemPhysicsBox(ItemPhysicsBox itemPhysicsBox) { this.itemPhysicsBox = itemPhysicsBox; }
	
	/**
	 * Draw this item.
	 * @param batch
	 * @param levelCameraXOffset
	 * @param levelCameraYOffset
	 */
    public void draw(SpriteBatch batch, float offsetx, float offsety) {
    	Texture itemTexture = ItemResources.getItemResources().getItemTexture(this.getType());
		batch.draw(itemTexture, (itemPhysicsBox.getPickupSensor().getX()*C.LAYOUT_MULTIPLIER) + offsetx, (itemPhysicsBox.getPickupSensor().getY()*C.LAYOUT_MULTIPLIER) + offsety,
				(itemPhysicsBox.getPickupSensor().getWidth()*C.LAYOUT_MULTIPLIER), (itemPhysicsBox.getPickupSensor().getHeight()*C.LAYOUT_MULTIPLIER));
    }
}
