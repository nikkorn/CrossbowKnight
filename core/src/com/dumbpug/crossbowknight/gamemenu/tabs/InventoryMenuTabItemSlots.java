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
	/** The position of the selected slot. */
	private int selectedSlotPosX = 0;
	private int selectedSlotPosY = C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1;

	/**
	 * Create a new instance of the InventoryMenuTabItemSlots.
	 */
	public InventoryMenuTabItemSlots() {
		// Create our item slots list.
		itemSlots = new ArrayList<ItemSlot>();
		// Populate our item slots list.
		Texture itemSlotBackground       = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot.png");
		Texture itemSlotQuantityOverlay  = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_quantity_overlay.png");
		Texture itemSlotSelectedOverlay  = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot_selectedoverlay.png");
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XSMALL;
		BitmapFont quantityCountFont  = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		quantityCountFont.setColor(Color.BLACK);
		for(int slotX = 0; slotX < C.MENU_INVENTORY_ITEM_SLOT_WIDTH; slotX++) {
			for(int slotY = C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1; slotY >= 0; slotY--) {
				itemSlots.add(new ItemSlot(itemSlotBackground, itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, slotX, slotY));
			}
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
	 * Move the slot selection up.
	 */
	public void selectionUp() {
		selectedSlotPosY = (selectedSlotPosY == (C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1)) ? 0 : selectedSlotPosY+1;
	}
	
	/**
	 * Move the slot selection down.
	 */
	public void selectionDown() {
		selectedSlotPosY = (selectedSlotPosY == 0) ? C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1 : selectedSlotPosY-1;
	}
	
	/**
	 * Move the slot selection left.
	 */
	public void selectionLeft() {
		selectedSlotPosX = (selectedSlotPosX == 0) ? C.MENU_INVENTORY_ITEM_SLOT_WIDTH - 1 : selectedSlotPosX-1;
	}
	
	/**
	 * Move the slot selection right.
	 */
	public void selectionRight() {
		selectedSlotPosX = (selectedSlotPosX == (C.MENU_INVENTORY_ITEM_SLOT_WIDTH - 1)) ? 0 : selectedSlotPosX+1;
	}
	
	/**
	 * Get the item slot which is currently selected.
	 * @return item slot.
	 */
	private ItemSlot getSelectedSlot() {
		for(ItemSlot slot : itemSlots) {
			if((slot.getPosX() == selectedSlotPosX) && (slot.getPosY() == selectedSlotPosY)) {
				return slot;
			}
		}
		return null;
	}
	
	/**
	 * Get the item which is currently selected.
	 * Returns null if the slot is empty.
	 * @return item
	 */
	public Item getSelectedItem() { return this.getSelectedSlot().getMappedItem(); }

	/**
	 * Draw the inventory item slots.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw the item slots.
		for(ItemSlot slot : itemSlots) {
			slot.draw(batch, slotStartPositionX, slotStartPositionY, slot == this.getSelectedSlot());
		}
	}
	
	/**
	 * Represents an item slot.
	 * @author nikolas.howard
	 */
	private class ItemSlot {
		/** The background for an item slot. */
		private Texture background;
		/** The quantity overlay for an item slot. */
		private Texture quantityOverlay;
		/** The selected overlay for an item slot. */
		private Texture selectedOverlay;
		/** The font with which to draw item quantities. */
		private BitmapFont quantityCountFont;
		/** The position of the item slot in the inventory tab. */
		private int slotPosX;
		private int slotPosY;
		/** The item mapped to this slot. */
		private Item mappedItem = null;
		
		/**
		 * Create a new instance of the ItemSlot class.
		 * @param background
		 * @param quantityOverlay
		 * @param selectedOverlay
		 * @param slotPosX
		 * @param slotPosY
		 * @param quantityCountFont
		 */
		public ItemSlot(Texture background, Texture quantityOverlay, Texture selectedOverlay, BitmapFont quantityCountFont, int slotPosX, int slotPosY) {
			this.background        = background;
			this.quantityOverlay   = quantityOverlay;
			this.selectedOverlay   = selectedOverlay;
			this.quantityCountFont = quantityCountFont;
			this.slotPosX          = slotPosX;
			this.slotPosY          = slotPosY;
		}
		
		/**
		 * Get the X position of this slot.
		 * @return x position
		 */
		public int getPosX() { return this.slotPosX; }
		
		/**
		 * Get the Y position of this slot.
		 * @return y position
		 */
		public int getPosY() { return this.slotPosY; }

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
		 * @param isSelected
         */
		public void draw(SpriteBatch batch, float slotStartPositionX, float slotStartPositionY, boolean isSelected) {
			// Draw the item slot background.
			batch.draw(this.background, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
					slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
			// If this slot is currently selected then draw the selected slot overlay.
			if(isSelected) {
				batch.draw(selectedOverlay, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
						slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
			}
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
	}
}
