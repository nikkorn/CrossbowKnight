package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.EquippedItems;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabType;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemCategory;

/**
 * In-Game menu tab for the player equipment.
 * @author nikolas.howard
 */
public class EquipmentMenuTab implements GameMenuTab {
	/** The equipment menu slots. */
	private EquipmentMenuTabItemSlots slots;
	/** The equipment selection viewer. */
	private EquipmentSelectionViewer equipmentSelectionViewer;
	/** The players inventory. */
	private Inventory inventory;
	/** The players equipment. */
	private EquippedItems equippedItems;
	/** The font with which to make the sub-section headers. */
	BitmapFont sectionHeaderFont;
	
	/**
	 * Create a new instance of the EquipmentMenuTab.
	 * @param inventory
     */
	public EquipmentMenuTab(Inventory inventory, EquippedItems equippedItems) {
		this.slots                    = new EquipmentMenuTabItemSlots();
		this.equipmentSelectionViewer = new EquipmentSelectionViewer(equippedItems);
		this.inventory                = inventory;
		this.equippedItems            = equippedItems;
		// Make the section header font.
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size                                        = C.FONT_SIZE_SMALL;
		sectionHeaderFont                                     = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		sectionHeaderFont.setColor(Color.WHITE);
	}
	
	@Override
	public void update() {
		// Map our equipment to our menu slots.
		slots.mapEquipmentToSlots(equippedItems);
		// Is our selection viewer open?
		if(this.equipmentSelectionViewer.isOpen()) {
			// Our selection viewer is open, update it.
			equipmentSelectionViewer.update();
			return;
		}
		// Check whether the player wants to change their slot selection.
		if(CrossbowKnight.getPlayerInput().isLeftButtonPressed()) { 
			slots.selectionLeft(); 
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
		if(CrossbowKnight.getPlayerInput().isRightButtonPressed()) { 
			slots.selectionRight(); 
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
		if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) { 
			slots.selectionUp(); 
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
		if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) { 
			slots.selectionDown(); 
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
		// Check for accept/cancel button presses.
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) { 
			onSelect();
		}
		if(CrossbowKnight.getPlayerInput().isCancelButtonPressed()) { 
			onCancel();
		}
	}

	/**
	 * Called when the user presses the select button.
	 */
	private void onSelect() {
		// A slot has been selected, open the selection viewer.
		// We only want to be able to select the type of items which match the slot.
		EquipmentSlotType slotType = slots.getSelectedSlot().getEquipmentSlotType();
		ItemCategory itemCategory  = null;
		switch(slotType) {
			case HELMET:
				itemCategory = ItemCategory.HELMET;
				break;
			case LIMBS:
				itemCategory = ItemCategory.LIMBS;
				break;
			case PRIMARY_ITEM:
			case SECONDARY_ITEM:
				itemCategory = ItemCategory.CONSUMABLE;
				break;
			case PRIMARY_AMMO:
			case SECONDARY_AMMO:
				itemCategory = ItemCategory.AMMO;
				break;
			case SHIELD:
				itemCategory = ItemCategory.SHIELD;
				break;
			case SIGHT:
				itemCategory = ItemCategory.SIGHT;
				break;
			case STOCK:
				itemCategory = ItemCategory.STOCK;
				break;
			case STRING:
				itemCategory = ItemCategory.STRING;
				break;
		}
		// Set the items that can be selected for this slot.
		ArrayList<Item> appropriateItems = new ArrayList<Item>();
		for(Item item : inventory.getItems()) {
			if(item.getCategory() == itemCategory) {
				appropriateItems.add(item);
			}
		}
		// Set the equippable items for this slot.
		equipmentSelectionViewer.setEquippableItems(appropriateItems, slots.getSelectedSlot().getEquipmentSlotType());
		equipmentSelectionViewer.setPosition(slots.getSelectedSlot().getDrawablePosX() + C.INGAME_MENU_POS_X,
				slots.getSelectedSlot().getDrawablePosY() + C.INGAME_MENU_POS_Y);
		equipmentSelectionViewer.setOpen(true);
	}
	
	/**
	 * Called when the user presses the cancel button.
	 */
	private void onCancel() {
		// TODO Stuff.
	}

	@Override
	public void draw(SpriteBatch batch) {
		// Draw the equipment sub-section headings.
		sectionHeaderFont.draw(batch, "Crossbow", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.11f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.79f));
		sectionHeaderFont.draw(batch, "Defense", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.41f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.79f));
		sectionHeaderFont.draw(batch, "Ammo", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.635f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.79f));
		sectionHeaderFont.draw(batch, "Items", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.832f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.79f));
		// TODO Draw the item selection menu.
		// Draw the equipment slots.
		slots.draw(batch);
		// Is our selection viewer open?
		if(this.equipmentSelectionViewer.isOpen()) {
			// Our selection viewer is open, draw it.
			equipmentSelectionViewer.draw(batch);
		}
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.EQUIPMENT; }
}
