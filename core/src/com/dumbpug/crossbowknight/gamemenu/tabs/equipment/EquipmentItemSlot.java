package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.gamemenu.tabs.ItemSlot;

/**
 * A type of ItemSlot which maps to an equipment type.
 * @author nikolas.howard
 */
public class EquipmentItemSlot extends ItemSlot {
	/** The equipment that this slot maps to. */
	EquipmentSlotType equipmentSlotType;
	/** 
	 * The equipment item slots will have an actual position defined,
	 * rather than this being determined by its coordinates
	 */
	float posX;
	float posY;
	
	/**
	 * Create a new instance of the EquipmentItemSlot class.
	 * @param background
	 * @param quantityOverlay
	 * @param selectedOverlay
	 * @param slotCoordsX
	 * @param slotCoordsY
	 * @param quantityCountFont
	 * @param equipmentSlotType
	 * @param posX
	 * @param posY
	 */
	public EquipmentItemSlot(Texture background, Texture quantityOverlay, Texture selectedOverlay, 
			BitmapFont quantityCountFont, int slotCoordsX, int slotCoordsY, EquipmentSlotType equipmentSlotType, float posX, float posY) {
		super(background, quantityOverlay, selectedOverlay, quantityCountFont, slotCoordsX, slotCoordsY);
		this.equipmentSlotType = equipmentSlotType;
		this.posX              = posX;
		this.posY              = posY;
	}
	
	/**
	 * Get the equipment type that this slot maps to.
	 * @return EquipmentSlotType
	 */
	public EquipmentSlotType getEquipmentSlotType() { return this.equipmentSlotType; }
	
	/**
	 * Get the drawable x position of this slot.
	 * @return drawable x position 
	 */
	protected float getDrawablePosX() { return posX; } 
	
	/**
	 * Get the drawable y position of this slot.
	 * @return drawable y position 
	 */
	protected float getDrawablePosY() { return posY; } 
	
	/**
	 * Get the size of the slot.
	 * @return slot size
	 */
	protected float getSlotSize() { return C.EQUIPMENT_SLOT_SIZE; } 
}
