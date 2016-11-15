package com.dumbpug.crossbowknight.gamemenu.tabs.inventory;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.gamemenu.tabs.ItemSlot;
import com.dumbpug.crossbowknight.resources.FontPack;

/**
 * Represents a collection of InventoryMenuTab item slots.
 * @author nikolas.howard
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
		parameter.size = C.FONT_SIZE_XXSMALL;
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
			if((slot.getCoordX() == selectedSlotPosX) && (slot.getCoordY() == selectedSlotPosY)) {
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
}
