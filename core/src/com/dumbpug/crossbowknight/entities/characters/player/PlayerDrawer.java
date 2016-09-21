package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * Draws the player.
 * @author nikolas.howard
 */
public class PlayerDrawer {
	/** The player. */
	private Player player;
	/** Drawing resources */
	private Texture testKnightTexture;
	
	/**
	 * Create a new instance of the PlayerDrawer class.
	 * @param player
	 */
	public PlayerDrawer(Player player) {
		this.player = player;
		// Set up resources for drawing.
		testKnightTexture = new Texture("graphics/characters/player/knight.png");
	}
	
	/**
	 * Draw the player.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public void draw(SpriteBatch batch, float offsetX, float offsetY) {
		// Our player will always be in the center of our screen. Get tehse offsets
		
		// Get the players position.
		float playerPosX = player.getCurrentOriginPoint().getX() - (C.PLAYER_SIZE_WIDTH/2);
		float playerPosY = player.getCurrentOriginPoint().getY() - (C.PLAYER_SIZE_HEIGHT/2);
		// Draw our player.
		batch.draw(testKnightTexture,
				(playerPosX * C.LAYOUT_MULTIPLIER) + offsetX,
				(playerPosY * C.LAYOUT_MULTIPLIER) + offsetY,
				(C.PLAYER_SIZE_WIDTH * C.LAYOUT_MULTIPLIER),
				(C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER));
	}
}
