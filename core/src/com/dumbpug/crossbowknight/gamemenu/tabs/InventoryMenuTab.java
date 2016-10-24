package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;

/**
 * In-Game menu tab for the player inventory.
 * @author nikolas.howard
 */
public class InventoryMenuTab implements GameMenuTab {
	/** The background for this tab. */
	private Texture background;
	/** The inventory menu slots. */
	private InventoryMenuTabItemSlots slots;
	/** The players inventory. */
	private Inventory inventory;

	/**
	 * Create a new instance of the InventoryMenuTab.
	 * @param inventory
     */
	public InventoryMenuTab(Inventory inventory) {
		this.background = new Texture("graphics/gamemenu/inventory/gamemenu_background.png");
		this.slots      = new InventoryMenuTabItemSlots();
		this.inventory  = inventory;
	}

	@Override
	public void update() {
		// Map the inventory items to the slots.
		// TODO We should eventually only map the items to the slots when the inventory changes.
		slots.mapItemsToSlots(inventory);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
		// TODO Draw the gold amount.
		// TODO Draw the key number.
		// Draw the inventory slots.
		slots.draw(batch);
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.INVENTORY; }
}
