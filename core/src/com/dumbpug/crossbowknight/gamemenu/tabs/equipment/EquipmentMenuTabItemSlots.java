package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.EquippedItems;
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
	private int selectedSlotPosY = 0;

	/**
	 * Create a new instance of the InventoryMenuTabItemSlots.
	 */
	public EquipmentMenuTabItemSlots() {
		// Create our item slots list.
		equippedItemSlots = new ArrayList<EquipmentItemSlot>();
		// Populate our equipment slots list.
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XSMALL;
		BitmapFont quantityCountFont  = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		quantityCountFont.setColor(Color.BLACK);
		Texture itemSlotQuantityOverlay = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_quantity_overlay.png");
		Texture itemSlotSelectedOverlay = new Texture("graphics/gamemenu/inventory/inventory_gamemenu_itemslot_selectedoverlay.png");
		
		// Add crossbow limb equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_limbs_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 0, 0, EquipmentSlotType.LIMBS, 0, C.INGAME_MENU_WIDTH * 0.16f));
				
		// Add crossbow stock equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_stock_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 1, 0, EquipmentSlotType.STOCK, C.INGAME_MENU_WIDTH * 0.16f, C.INGAME_MENU_WIDTH * 0.16f));
		
		// Add crossbow string equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_string_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 0, 1, EquipmentSlotType.STRING, 0, 0));
		
		// Add crossbow sight equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_sight_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 1, 1, EquipmentSlotType.SIGHT, C.INGAME_MENU_WIDTH * 0.16f, 0));
		
		// Add shield equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_shield_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 2, 0, EquipmentSlotType.SHIELD, C.INGAME_MENU_WIDTH * 0.37f, C.INGAME_MENU_WIDTH * 0.16f));
		
		// Add helmet equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_helmet_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 2, 1, EquipmentSlotType.HELMET, C.INGAME_MENU_WIDTH * 0.37f, 0));
		
		// Add primary ammo equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_ammo_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 3, 0, EquipmentSlotType.PRIMARY_AMMO, C.INGAME_MENU_WIDTH * 0.575f, C.INGAME_MENU_WIDTH * 0.16f));
		
		// Add secondary ammo equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_ammo_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 3, 1, EquipmentSlotType.SECONDARY_AMMO, C.INGAME_MENU_WIDTH * 0.575f, 0));
		
		// Add primary item equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_item_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 4, 0, EquipmentSlotType.PRIMARY_ITEM, C.INGAME_MENU_WIDTH * 0.78f, C.INGAME_MENU_WIDTH * 0.16f));
		
		// Add secondary item equipment slot.
		equippedItemSlots.add(new EquipmentItemSlot(new Texture("graphics/gamemenu/equipment/equipment_gamemenu_item_slot.png"), 
			itemSlotQuantityOverlay, itemSlotSelectedOverlay, quantityCountFont, 4, 1, EquipmentSlotType.SECONDARY_ITEM, C.INGAME_MENU_WIDTH * 0.78f, 0));
	}
	
	/**
	 * Map the equipment items in an inventory to the item slots.
	 * @param equipment
     */
	public void mapEquipmentToSlots(EquippedItems equipment) {
		for(EquipmentItemSlot slot : equippedItemSlots) {
			switch(slot.getEquipmentSlotType()) {
				case PRIMARY_AMMO:
					slot.setMappedItem(equipment.getPrimaryAmmoSlot());
					break;
				case PRIMARY_ITEM:
					slot.setMappedItem(equipment.getPrimaryItemSlot());
					break;
				case SECONDARY_AMMO:
					slot.setMappedItem(equipment.getSecondaryAmmoSlot());
					break;
				case SECONDARY_ITEM:
					slot.setMappedItem(equipment.getSecondaryItemSlot());
					break;
				case HELMET:
					//slot.setMappedItem(equipment.);
					break;
				case LIMBS:
					//slot.setMappedItem(equipment);
					break;
				case SHIELD:
					//slot.setMappedItem(equipment);
					break;
				case SIGHT:
					//slot.setMappedItem(equipment);
					break;
				case STOCK:
					//slot.setMappedItem(equipment);
					break;
				case STRING:
					//slot.setMappedItem(equipment);
					break;
			}
		}
	}
	
	/**
	 * Move the slot selection up.
	 */
	public void selectionUp() {
		selectedSlotPosY = (selectedSlotPosY == (C.MENU_EQUIPMENT_ITEM_SLOT_HEIGHT - 1)) ? 0 : selectedSlotPosY+1;
	}
	
	/**
	 * Move the slot selection down.
	 */
	public void selectionDown() {
		selectedSlotPosY = (selectedSlotPosY == 0) ? C.MENU_EQUIPMENT_ITEM_SLOT_HEIGHT - 1 : selectedSlotPosY-1;
	}
	
	/**
	 * Move the slot selection left.
	 */
	public void selectionLeft() {
		selectedSlotPosX = (selectedSlotPosX == 0) ? C.MENU_EQUIPMENT_ITEM_SLOT_WIDTH - 1 : selectedSlotPosX-1;
	}
	
	/**
	 * Move the slot selection right.
	 */
	public void selectionRight() {
		selectedSlotPosX = (selectedSlotPosX == (C.MENU_EQUIPMENT_ITEM_SLOT_WIDTH - 1)) ? 0 : selectedSlotPosX+1;
	}
	
	/**
	 * Get the equipment slot which is currently selected.
	 * @return item slot.
	 */
	public EquipmentItemSlot getSelectedSlot() {
		for(EquipmentItemSlot slot : equippedItemSlots) {
			if((slot.getCoordX() == selectedSlotPosX) && (slot.getCoordY() == selectedSlotPosY)) {
				return slot;
			}
		}
		return null;
	}

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
