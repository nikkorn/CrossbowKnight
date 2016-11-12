package com.dumbpug.crossbowknight.gamemenu.tabs.equipment;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabType;

/**
 * In-Game menu tab for the player equipment.
 * @author nikolas.howard
 */
public class EquipmentMenuTab implements GameMenuTab {

	@Override
	public void update() {
		System.out.println("UPDATE_EQUIPMENT");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		System.out.println("DRAW_EQUIPMENT");
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.EQUIPMENT; }
}
