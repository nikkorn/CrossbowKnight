package com.dumbpug.crossbowknight.gamemenu.tabs.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.Inventory;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemType;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabType;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * In-Game menu tab for the player inventory.
 * @author nikolas.howard
 */
public class InventoryMenuTab implements GameMenuTab {
	/** The background for this tab. */
	private Texture background;
	/** The font to use for this tab. */
	private BitmapFont font;
	private BitmapFont keyGoldFont;
	/** The inventory menu slots. */
	private InventoryMenuTabItemSlots slots;
	/** The players inventory. */
	private Inventory inventory;
	/** The size of the gold/key icons. */
	private float iconSize = C.INGAME_MENU_HEIGHT * 0.1f;
	/** The width and position of the info box */
	private float infoBoxWidth = C.INGAME_MENU_WIDTH * 0.3f;
	private float infoBoxPosX  = C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.65f);
	private float infoBoxPosY  = C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.7f);

	/**
	 * Create a new instance of the InventoryMenuTab.
	 * @param inventory
     */
	public InventoryMenuTab(Inventory inventory) {
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size   = C.FONT_SIZE_XSMALL;
		this.background  = new Texture("graphics/gamemenu/inventory/gamemenu_background.png");
		this.slots       = new InventoryMenuTabItemSlots();
		this.font        = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		parameter.size   = C.FONT_SIZE_SMALL;
		this.keyGoldFont = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		this.inventory   = inventory;
	}

	@Override
	public void update() {
		// Map the inventory items to the slots.
		// TODO We should eventually only map the items to the slots when the inventory changes.
		slots.mapItemsToSlots(inventory);
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
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
		// Draw the gold amount.
		batch.draw(ItemResources.getItemResources().getItemTexture(ItemType.GOLD), 
				C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.65f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.785f), iconSize, iconSize);
		keyGoldFont.draw(batch, inventory.getGold() + "", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.71f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.86f));
		// Draw the key number.
		batch.draw(ItemResources.getItemResources().getItemTexture(ItemType.KEY), 
				C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.85f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.78f), iconSize, iconSize);
		keyGoldFont.draw(batch, inventory.getNumberOfKeys() + "", C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.92f), C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.86f));
		// If we have a slot selected which is mapped to an item, write the item info to the info box.
		Item selectedItem = slots.getSelectedItem();
		if(selectedItem != null) {
			font.draw(batch, selectedItem.getName() + "\n\n" + selectedItem.getDescription(), infoBoxPosX, infoBoxPosY, infoBoxWidth, Align.left, true);
		}
		// Draw the inventory slots.
		slots.draw(batch);
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.INVENTORY; }
}
