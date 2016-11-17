package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

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

/**
 * In-Game menu tab for the player equipment.
 * @author nikolas.howard
 */
public class EquipmentMenuTab implements GameMenuTab {
	/** The equipment menu slots. */
	private EquipmentMenuTabItemSlots slots;
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
		this.slots         = new EquipmentMenuTabItemSlots();
		this.inventory     = inventory;
		this.equippedItems = equippedItems;
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
		// TODO Stuff.
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
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.EQUIPMENT; }
}
