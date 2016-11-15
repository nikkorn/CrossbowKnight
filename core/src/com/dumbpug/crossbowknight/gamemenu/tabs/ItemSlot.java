package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * Represents an item slot.
 * @author nikolas.howard
 */
public class ItemSlot {
	/** The background for an item slot. */
	private Texture background;
	/** The quantity overlay for an item slot. */
	private Texture quantityOverlay;
	/** The selected overlay for an item slot. */
	private Texture selectedOverlay;
	/** The font with which to draw item quantities. */
	private BitmapFont quantityCountFont;
	/** The coords of the item slot in the inventory tab. */
	private int slotCoordsX;
	private int slotCoordsY;
	/** The item mapped to this slot. */
	private Item mappedItem = null;
	
	/**
	 * Create a new instance of the ItemSlot class.
	 * @param background
	 * @param quantityOverlay
	 * @param selectedOverlay
	 * @param slotCoordsX
	 * @param slotCoordsY
	 * @param quantityCountFont
	 */
	public ItemSlot(Texture background, Texture quantityOverlay, Texture selectedOverlay, BitmapFont quantityCountFont, int slotCoordsX, int slotCoordsY) {
		this.background        = background;
		this.quantityOverlay   = quantityOverlay;
		this.selectedOverlay   = selectedOverlay;
		this.quantityCountFont = quantityCountFont;
		this.slotCoordsX       = slotCoordsX;
		this.slotCoordsY       = slotCoordsY;
	}
	
	/**
	 * Get the X coord of this slot.
	 * @return x coord
	 */
	public int getCoordX() { return this.slotCoordsX; }
	
	/**
	 * Get the Y coord of this slot.
	 * @return y coord
	 */
	public int getCoordY() { return this.slotCoordsY; }

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
	 * Get the drawable x position of this slot.
	 * @return drawable x position 
	 */
	protected float getDrawablePosX() { return C.INVENTORY_SLOT_SIZE * slotCoordsX; } 
	
	/**
	 * Get the drawable y position of this slot.
	 * @return drawable y position 
	 */
	protected float getDrawablePosY() { return C.INVENTORY_SLOT_SIZE * slotCoordsY; } 
	
	/**
	 * Get the size of the slot.
	 * @return slot size
	 */
	protected float getSlotSize() { return C.INVENTORY_SLOT_SIZE; } 

	/**
	 * Draw the item slot.
	 * @param batch
	 * @param slotStartPositionX
	 * @param slotStartPositionY
	 * @param isSelected
     */
	public void draw(SpriteBatch batch, float slotStartPositionX, float slotStartPositionY, boolean isSelected) {
		// Draw the item slot background.
		batch.draw(this.background, slotStartPositionX + getDrawablePosX(),
				slotStartPositionY + getDrawablePosY(), getSlotSize(), getSlotSize());
		// If this slot is currently selected then draw the selected slot overlay.
		if(isSelected) {
			batch.draw(selectedOverlay, slotStartPositionX + getDrawablePosX(),
					slotStartPositionY + getDrawablePosY(), getSlotSize(), getSlotSize());
		}
		// Draw the mapped item (if there is one).
		if(this.mappedItem != null && this.mappedItem.isDisplayedInInventory()) {
			// We have an item mapped to this slot.
			// Firstly, get the texture for this item and draw it.
			Texture itemTexture = ItemResources.getItemResources().getItemTexture(this.mappedItem.getType());
			batch.draw(itemTexture, slotStartPositionX + getDrawablePosX() + (getSlotSize()*0.15f),
					slotStartPositionY + getDrawablePosY() + (getSlotSize()*0.15f), getSlotSize()*0.7f, getSlotSize()*0.7f);
			// We may also need to draw the quantity.
			if(this.mappedItem.isQuantifiable()) {
				// Draw the item quantity overlay.
				batch.draw(quantityOverlay, slotStartPositionX + getDrawablePosX(),
						slotStartPositionY + getDrawablePosY(), getSlotSize(), getSlotSize());
				// Draw the quantity.
				String quantity = this.mappedItem.getQuantity() >= 0 ? this.mappedItem.getQuantity()+"" : "--";
				quantityCountFont.draw(batch, quantity, slotStartPositionX + getDrawablePosX() + (getSlotSize()*0.12f),
						slotStartPositionY + getDrawablePosY() + (getSlotSize()*0.20f));
			}
		}
	}
}