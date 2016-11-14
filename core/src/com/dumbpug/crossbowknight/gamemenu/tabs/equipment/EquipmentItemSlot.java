package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.dumbpug.crossbowknight.gamemenu.tabs.ItemSlot;

/**
 * A type of ItemSlot which maps to an equipment type.
 * @author nikolas.howard
 */
public class EquipmentItemSlot extends ItemSlot {
	/** The equipment that this slot maps to. */
	EquipmentSlotType equipmentSlotType;
	
	/**
	 * Create a new instance of the EquipmentItemSlot class.
	 * @param background
	 * @param quantityOverlay
	 * @param selectedOverlay
	 * @param slotPosX
	 * @param slotPosY
	 * @param quantityCountFont
	 * @param equipmentSlotType
	 */
	public EquipmentItemSlot(Texture background, Texture quantityOverlay, Texture selectedOverlay, 
			BitmapFont quantityCountFont, int slotPosX, int slotPosY, EquipmentSlotType equipmentSlotType) {
		super(background, quantityOverlay, selectedOverlay, quantityCountFont, slotPosX, slotPosY);
		this.equipmentSlotType = equipmentSlotType;
	}
	
	/**
	 * Get the equipment type that this slot maps to.
	 * @return EquipmentSlotType
	 */
	public EquipmentSlotType getEquipmentSlotType() { return this.equipmentSlotType; }
}
