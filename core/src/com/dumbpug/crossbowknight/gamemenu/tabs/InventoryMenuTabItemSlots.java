package com.dumbpug.crossbowknight.gamemenu.tabs;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * Represents a collection of InventoryMenuTab item slots.
 * @author nikolas.howard
 *
 */
public class InventoryMenuTabItemSlots {
	/** The list of inventory item slots. */
	private ArrayList<ItemSlot> itemSlots;
	/** The position from which to start drawing slots to the screen. */
	private float slotStartPositionX = C.INGAME_MENU_POS_X + (C.INVENTORY_SLOT_SIZE/3f);
	private float slotStartPositionY = C.INGAME_MENU_POS_Y + (C.INVENTORY_SLOT_SIZE/3f);

	/**
	 * Create a new instance of the InventoryMenuTabItemSlots.
	 */
	public InventoryMenuTabItemSlots() {
		// Create our item slots list.
		itemSlots = new ArrayList<ItemSlot>();
		// Populate our item slots list.
		Texture itemSlotBackground       = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot.png");
		Texture itemSlotQuantityOverlay  = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_quantity_overlay.png");
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XSMALL;
		BitmapFont quantityCountFont  = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		quantityCountFont.setColor(Color.BLACK);
		for(int slotX = 0; slotX < C.MENU_INVENTORY_ITEM_SLOT_WIDTH; slotX++) {
			for(int slotY = C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1; slotY >= 0; slotY--) {
				itemSlots.add(new ItemSlot(itemSlotBackground, itemSlotQuantityOverlay, quantityCountFont, slotX, slotY));
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
			slot.draw(batch, slotStartPositionX, slotStartPositionY);
		}
	}

	/**
	 * Map the items in an inventory to the item slots.
	 * @param inventory
     */
	public void mapItemsToSlots(Inventory inventory) {
		int slotIndex = 0;
		for(ItemSlot slot : itemSlots) {
			if(slotIndex < inventory.getItems().size()) {
				slot.setMappedItem(inventory.getItems().get(slotIndex++));
			} else {
				slot.setMappedItem(null);
			}
		}
	}

	/**
	 * Represents an item slot.
	 * @author nikolas.howard
	 */
	private class ItemSlot {
		/** The background for an item slot. */
		private Texture background;
		/** The background for an item slot. */
		private Texture quantityOverlay;
		/** The font with which to draw item quantities. */
		private BitmapFont quantityCountFont;
		/** The position of the item slot in the inventory tab. */
		private int slotPosX;
		private int slotPosY;
		/** Is this slot selected? */
		private boolean isSelected = false;
		/** The item mapped to this slot. */
		private Item mappedItem = null;
		
		/**
		 * Create a new instance of the ItemSlot class.
		 * @param background
		 * @param quantityOverlay
		 * @param slotPosX
		 * @param slotPosY
		 * @param quantityCountFont
		 */
		public ItemSlot(Texture background, Texture quantityOverlay, BitmapFont quantityCountFont, int slotPosX, int slotPosY) {
			this.background        = background;
			this.quantityOverlay   = quantityOverlay;
			this.quantityCountFont = quantityCountFont;
			this.slotPosX          = slotPosX;
			this.slotPosY          = slotPosY;
		}

		/**
		 * Get the item mapped to this slot.
		 * @return item
         */
		public Item getMappedItem() { return mappedItem; }

		/**
		 * Set the item mapped to this slot.
		 * @param mappedItem
         */
		public void setMappedItem(Item mappedItem) { this.mappedItem = mappedItem; }

		/**
		 * Draw the item slot.
		 * @param batch
		 * @param slotStartPositionX
		 * @param slotStartPositionY
         */
		public void draw(SpriteBatch batch, float slotStartPositionX, float slotStartPositionY) {
			// Draw the item slot background.
			batch.draw(this.background, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
					slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
			// Draw the mapped item (if there is one).
			if(this.mappedItem != null && this.mappedItem.isDisplayedInInventory()) {
				// We have an item mapped to this slot.
				// Firstly, get the texture for this item and draw it.
				Texture itemTexture = ItemResources.getItemResources().getItemTexture(this.mappedItem.getType());
				batch.draw(itemTexture, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX) + (C.INVENTORY_SLOT_SIZE*0.15f),
						slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY) + (C.INVENTORY_SLOT_SIZE*0.15f), C.INVENTORY_SLOT_SIZE*0.7f, C.INVENTORY_SLOT_SIZE*0.7f);
				// We may also need to draw the quantity.
				if(this.mappedItem.isQuantifiable()) {
					// Draw the item quantity overlay.
					batch.draw(quantityOverlay, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
							slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
					// Draw the quantity.
					String quantity = this.mappedItem.getQuantity() >= 0 ? this.mappedItem.getQuantity()+"" : "--";
					quantityCountFont.draw(batch, quantity, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX) + (C.INVENTORY_SLOT_SIZE*0.12f),
							slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY) + (C.INVENTORY_SLOT_SIZE*0.20f));
				}
			}
		}

		/**
		 * Get whether this item slot is the selected one.
		 * @return isSelected.
		 */
		public boolean isSelected() { return isSelected; }

		/**
		 * Set whether this item slot is the selected one.
		 * @param isSelected
		 */
		public void setSelected(boolean isSelected) { this.isSelected = isSelected; }
	}
}
