package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.EquippedItems;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabType;

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
	
	/**
	 * Create a new instance of the EquipmentMenuTab.
	 * @param inventory
     */
	public EquipmentMenuTab(Inventory inventory, EquippedItems equippedItems) {
		this.slots         = new EquipmentMenuTabItemSlots();
		this.inventory     = inventory;
		this.equippedItems = equippedItems;
	}
	
	@Override
	public void update() {
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
		// TODO Draw the equipment sub-section headings.
		// TODO Draw the item selection menu.
		// Draw the equipment slots.
		slots.draw(batch);
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.EQUIPMENT; }
}
