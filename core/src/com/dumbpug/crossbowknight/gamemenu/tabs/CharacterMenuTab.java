package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * In-Game menu tab for player status.
 * @author nikolas.howard
 */
public class CharacterMenuTab implements GameMenuTab {

	@Override
	public void update() {
		System.out.println("UPDATE_CHARACTER");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		System.out.println("DRAW_CHARACTER");
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.CHARACTER; }
}
