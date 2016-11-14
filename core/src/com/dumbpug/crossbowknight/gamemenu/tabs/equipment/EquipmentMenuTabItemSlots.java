package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.gamemenu.tabs.ItemSlot;
import com.dumbpug.crossbowknight.resources.FontPack;

/**
 * Represents a collection of EquipmentMenuTab equipped item slots.
 * @author nikolas.howard
 */
public class EquipmentMenuTabItemSlots {
	/** The list of equipment item slots. */
	private ArrayList<EquipmentItemSlot> equippedItemSlots;
	/** The position from which to start drawing slots to the screen. */
	private float slotStartPositionX = C.INGAME_MENU_POS_X + (C.INVENTORY_SLOT_SIZE/3f);
	private float slotStartPositionY = C.INGAME_MENU_POS_Y + (C.INVENTORY_SLOT_SIZE/3f);
	/** The position of the selected slot. */
	private int selectedSlotPosX = 0;
	private int selectedSlotPosY = C.MENU_INVENTORY_ITEM_SLOT_HEIGHT - 1;

	/**
	 * Create a new instance of the InventoryMenuTabItemSlots.
	 */
	public EquipmentMenuTabItemSlots() {
		// Create our item slots list.
		equippedItemSlots = new ArrayList<EquipmentItemSlot>();
		// Populate our equipment slots list.
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XXSMALL;
		BitmapFont quantityCountFont  = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		quantityCountFont.setColor(Color.BLACK);
		Texture itemSlotQuantityOverlay = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_quantity_overlay.png");
		Texture itemSlotSelectedOverlay = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot_selectedoverlay.png");
		
		// Add primary item equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_item_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 0, 1, EquipmentSlotType.PRIMARY_ITEM));
		
		// Add secondary item equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_item_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 0, 0, EquipmentSlotType.SECONDARY_ITEM));
		
		// ...
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
	 * Get the equipment slot which is currently selected.
	 * @return item slot.
	 */
	private EquipmentItemSlot getSelectedSlot() {
		for(EquipmentItemSlot slot : equippedItemSlots) {
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
		// Draw the equipment slots.
		for(ItemSlot slot : equippedItemSlots) {
			slot.draw(batch, slotStartPositionX, slotStartPositionY, slot == this.getSelectedSlot());
		}
	}
}
