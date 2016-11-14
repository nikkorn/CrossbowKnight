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
	/** The position of the item slot in the inventory tab. */
	private int slotPosX;
	private int slotPosY;
	/** The item mapped to this slot. */
	private Item mappedItem = null;
	
	/**
	 * Create a new instance of the ItemSlot class.
	 * @param background
	 * @param quantityOverlay
	 * @param selectedOverlay
	 * @param slotPosX
	 * @param slotPosY
	 * @param quantityCountFont
	 */
	public ItemSlot(Texture background, Texture quantityOverlay, Texture selectedOverlay, BitmapFont quantityCountFont, int slotPosX, int slotPosY) {
		this.background        = background;
		this.quantityOverlay   = quantityOverlay;
		this.selectedOverlay   = selectedOverlay;
		this.quantityCountFont = quantityCountFont;
		this.slotPosX          = slotPosX;
		this.slotPosY          = slotPosY;
	}
	
	/**
	 * Get the X position of this slot.
	 * @return x position
	 */
	public int getPosX() { return this.slotPosX; }
	
	/**
	 * Get the Y position of this slot.
	 * @return y position
	 */
	public int getPosY() { return this.slotPosY; }

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
	 * Draw the item slot.
	 * @param batch
	 * @param slotStartPositionX
	 * @param slotStartPositionY
	 * @param isSelected
     */
	public void draw(SpriteBatch batch, float slotStartPositionX, float slotStartPositionY, boolean isSelected) {
		// Draw the item slot background.
		batch.draw(this.background, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
				slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
		// If this slot is currently selected then draw the selected slot overlay.
		if(isSelected) {
			batch.draw(selectedOverlay, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
					slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
		}
		// Draw the mapped item (if there is one).
		if(this.mappedItem != null && this.mappedItem.isDisplayedInInventory()) {
			// We have an item mapped to this slot.
			// Firstly, get the texture for this item and draw it.
			Texture itemTexture = ItemResources.getItemResources().getItemTexture(this.mappedItem.getType());
			batch.draw(itemTexture, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX) + (C.INVENTORY_SLOT_SIZE*0.15f),
					slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY) + (C.INVENTORY_SLOT_SIZE*0.15f), C.INVENTORY_SLOT_SIZE*0.7f, C.INVENTORY_SLOT_SIZE*0.7f);
			// We may also need to draw the quantity.
			if(this.mappedItem.isQuantifiable()) {
				// Draw the item quantity overlay.
				batch.draw(quantityOverlay, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX),
						slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY), C.INVENTORY_SLOT_SIZE, C.INVENTORY_SLOT_SIZE);
				// Draw the quantity.
				String quantity = this.mappedItem.getQuantity() >= 0 ? this.mappedItem.getQuantity()+"" : "--";
				quantityCountFont.draw(batch, quantity, slotStartPositionX + (C.INVENTORY_SLOT_SIZE*slotPosX) + (C.INVENTORY_SLOT_SIZE*0.12f),
						slotStartPositionY + (C.INVENTORY_SLOT_SIZE*slotPosY) + (C.INVENTORY_SLOT_SIZE*0.20f));
			}
		}
	}
}