package com.dumbpug.crossbowknight.hud;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * The consumables bar displayed in the bottom left of the screen.
 */
public class HUDConsumablesBar extends HUDItemBar {

	@Override
	public float getPositionX() { return ((C.HUD_ITEM_BAR_MARGIN*2) + C.HUD_ITEM_BAR_WIDTH); }

	@Override
	public float getPositionY() { return C.HUD_ITEM_BAR_MARGIN; }

	@Override
	public Item getTargetItem(Player player) { return player.getEquipment().getPrimaryItemSlot(); }
}
