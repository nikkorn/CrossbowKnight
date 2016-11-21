package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.EquippedItems;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.Ammo;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * The viewer from which an item can be selected. 
 * @author nikolas.howard
 */
public class EquipmentSelectionViewer {
	/** The characters equipment. */
	private EquippedItems equipment;
	/** The position of the viewer. */
	private float posX = 0;
	private float posY = 0;
	/** Whether the selection viewer is open. */
	private boolean isOpen = false;
	/** The list of eqippable items from which to choose. */
	private ArrayList<Item> equippableItems = null;
	/** The equipment slot type. */
	private EquipmentSlotType equipmentSlotType = null;
	/** The currently selected item index. */
	private int selectionIndex = 0;
	/** The viewer border size. */
	private float borderSize = C.EQUIPMENT_SELECTION_VIEW_HEIGHT / 4;
	/** The height of the content to be displayed. */
	float contentHeight = C.EQUIPMENT_SELECTION_VIEW_HEIGHT / 2;
	/** The Y offset of the item name text. */
	float itemNameYOffset = C.EQUIPMENT_SELECTION_VIEW_HEIGHT * 0.62f;
	/** Resources with which to draw this selection viewer. */
	private Texture background;
	private BitmapFont itemNameFont;
	
	/**
	 * The direction in which selection can be moved.
	 * @author nikolas.howard
	 */
	private enum SelectionDirection {
		LEFT,
		RIGHT
	}
	
	/**
	 * Create a new instance of the EquipmentSelectionViewer class.
	 * @param equipment
	 */
	public EquipmentSelectionViewer(EquippedItems equipment) { 
		this.equipment  = equipment; 
		this.background = new Texture("graphics/gamemenu/equipment/equipment_gamemenu_selectionviewer.png");
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XXSMALL;
		itemNameFont   = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
	}
	
	/**
	 * Update the selection viewer.
	 */
	public void update() {
		// Did the player make a selection or cancellation?
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) { 
			onSelection();
		}
		if(CrossbowKnight.getPlayerInput().isCancelButtonPressed()) { 
			onCancel();
		}
		// Did the player change their selection?
		if(CrossbowKnight.getPlayerInput().isLeftButtonPressed()) { 
			onChangeSelection(SelectionDirection.LEFT);
		} else if(CrossbowKnight.getPlayerInput().isRightButtonPressed()) { 
			onChangeSelection(SelectionDirection.RIGHT);
		}
	}
	
	/**
	 * Draw the selection viewer.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Get the selected item from the list.
		Item selection = equippableItems.get(selectionIndex);
		// Draw the background for this selection viewer.
		batch.draw(this.background, posX, posY, C.EQUIPMENT_SELECTION_VIEW_WIDTH, C.EQUIPMENT_SELECTION_VIEW_HEIGHT);
		// Are we currently on the 'empty' selection slot.
		if(selection != null) {
			// Draw the item image.
			Texture itemTexture = ItemResources.getItemResources().getItemTexture(selection.getType());
			batch.draw(itemTexture, posX + (borderSize*2.2f), posY + borderSize, contentHeight, contentHeight);
			// Get the item name.
			String name = selection.getName();
			// If this item is quantifiable then add the quantity.
			if(selection.isQuantifiable()) {
				String quanitity = (selection.getQuantity() < 0) ? "--" : selection.getQuantity() + "";
				name += " (" + quanitity + ")";
			}
			// Change the font colour to reflect the rarity of the selection.
			switch(selection.getRarity()) {
				case COMMON:
					itemNameFont.setColor(Color.WHITE);
					break;
				case MYTHICAL:
					itemNameFont.setColor(Color.ORANGE);
					break;
				case RARE:
					itemNameFont.setColor(Color.CYAN);
					break;
				case ULTRA_RARE:
					itemNameFont.setColor(Color.VIOLET);
					break;
				case UNCOMMON:
					itemNameFont.setColor(Color.LIME);
					break;
			}
			// Draw the item name.
			itemNameFont.draw(batch, name, posX, posY + itemNameYOffset, C.EQUIPMENT_SELECTION_VIEW_WIDTH, Align.center, false);
			// Set the font back to its default.
			itemNameFont.setColor(Color.WHITE);
		} else {
			itemNameFont.draw(batch, "EMPTY", posX, posY + itemNameYOffset, C.EQUIPMENT_SELECTION_VIEW_WIDTH, Align.center, false);
		}
	}
	
	/**
	 * Called when the player moves the selection.
	 * @param direction
	 */
	private void onChangeSelection(SelectionDirection direction) {
		// Check whether the equipped items contains anything (other than our null).
		if(equippableItems.size() > 1) {
			// Move our selection.
			switch(direction) {
				case LEFT:
					if(selectionIndex == 0) {
						selectionIndex = equippableItems.size() - 1;
					} else {
						selectionIndex--;
					}
					break;
				case RIGHT:
					if(selectionIndex == (equippableItems.size() - 1)) {
						selectionIndex = 0;
					} else {
						selectionIndex++;
					}
					break;
			}
			// We moved, make a sound.
			Audio.getSoundEffect(Audio.SoundEffect.SWAP).play();
		} else {
			// We don't have anything to put in our slot.
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
	}
	
	/**
	 * Called when the player presses the accept button.
	 */
	private void onSelection() {
		// The player has decided to set something on the slot.
		// Get the selected item from the list.
		Item selection = equippableItems.get(selectionIndex);
		// Equip the selected item.
		switch(equipmentSlotType) {
			case PRIMARY_AMMO:
				equipment.setPrimaryAmmoSlot((Ammo) selection);
				break;
			case PRIMARY_ITEM:
				equipment.setPrimaryItemSlot(selection);
				break;
			case SECONDARY_AMMO:
				equipment.setSecondaryAmmoSlot((Ammo) selection);
				break;
			case SECONDARY_ITEM:
				equipment.setSecondaryItemSlot(selection);
				break;
			case HELMET:
				// TODO slot.setMappedItem(equipment.);
				break;
			case LIMBS:
				// TODO slot.setMappedItem(equipment);
				break;
			case SHIELD:
				// TODO slot.setMappedItem(equipment);
				break;
			case SIGHT:
				// TODO slot.setMappedItem(equipment);
				break;
			case STOCK:
				// TODO slot.setMappedItem(equipment);
				break;
			case STRING:
				// TODO slot.setMappedItem(equipment);
				break;
		}
		// Make a little selection sound!
		Audio.getSoundEffect(Audio.SoundEffect.BLIP_HIGH).play();
	}
	
	/**
	 * Called when the player presses the cancel button.
	 */
	private void onCancel() {
		// This viewer is no longer open.
		this.setOpen(false);
	}
	
	/**
	 * Set whether this selection viewer is open.
	 * @param isOpen
	 */
	public void setOpen(boolean isOpen) { this.isOpen = isOpen; }
	
	/**
	 * Get whether this selection viewer is open.
	 * @return isOpen
	 */
	public boolean isOpen() { return this.isOpen; }
	
	/**
	 * Set the position of this selection viewer on the screen.
	 * @param positionX
	 * @param positionY
	 */
	public void setPosition(float positionX, float positionY) {
		this.posX = positionX;
		this.posY = positionY;
	}
	
	/**
	 * Set the items which are equippable with this selection view.
	 * @param equippableItems
	 * @param equipmentSlotType
	 */
	public void setEquippableItems(ArrayList<Item> equippableItems, EquipmentSlotType equipmentSlotType) { 
		// Set the equippable items.
		this.equippableItems = equippableItems; 
		// We need an entry in the equippable items list which represents an empty slot.
		this.equippableItems.add(null); // TODO Fix this shite!
		// Set the slot type.
		this.equipmentSlotType = equipmentSlotType;
	}
}
