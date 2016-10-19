package com.dumbpug.crossbowknight.gamemenu.tabs;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * Represents a collection of InventoryMenuTab item slots.
 * @author nikolas.howard
 *
 */
public class InventoryMenuTabItemSlots {
	/** The list of inventory item slots. */
	private ArrayList<ItemSlot> itemSlots;

	/**
	 * Create a new instance of the InventoryMenuTabItemSlots.
	 */
	public InventoryMenuTabItemSlots() {
		// Create our item slots list.
		itemSlots = new ArrayList<ItemSlot>();
		// Populate our item slots list.
		Texture itemSlotBackground  = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot.png");
		for(int slotX = 0; slotX < C.MENU_INVENTORY_ITEM_SLOT_WIDTH; slotX++) {
			for(int slotY = 0; slotY < C.MENU_INVENTORY_ITEM_SLOT_HEIGHT; slotY++) {
				itemSlots.add(new ItemSlot(itemSlotBackground, slotX, slotY));
			}
		}
	}
	
	/**
	 * Draw the inventory item slots.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw the item slots.
		for(ItemSlot slot : itemSlots) {
			slot.draw(batch);
		}
	}

	/**
	 * Represents an item slot.
	 * @author nikolas.howard
	 */
	private class ItemSlot {
		/** The background for an item slot. */
		private Texture background;
		/** The position of the item slot in the inventory tab. */
		int slotPosX;
		int slotPosY;
		/** Is this slot selected? */
		private boolean isSelected = false;
		
		/**
		 * Create a new instance of the ItemSlot class.
		 * @param background
		 * @param slotPosX
		 * @param slotPosY
		 */
		public ItemSlot(Texture background, int slotPosX, int slotPosY) {
			this.background = background;
			this.slotPosX   = slotPosX;
			this.slotPosY   = slotPosY;
		}
		
		/**
		 * Draw the item slot.
		 * @param batch
		 */
		public void draw(SpriteBatch batch) {
			// TODO Draw the item slots.
		}

		/**
		 * Get whether this item slot is the selected one.
		 * @return isSelected.
		 */
		public boolean isSelected() { return isSelected; }

		/**
		 * Set whether this item slot is the selected one.
		 * @param isSelected.
		 */
		public void setSelected(boolean isSelected) { this.isSelected = isSelected; }
	}
}
