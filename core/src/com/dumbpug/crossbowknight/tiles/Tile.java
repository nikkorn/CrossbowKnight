package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.level.Block;

/**
 * Represents a world tile.
 * @author nikolas.howard
 */
public class Tile {
	/** X/Y position of this tile. */
	private int x;
	private int y;
	/** The background texture for this tile */
	private Texture backgroundTexture = null;
	/** The decoration texture for this tile */
	private Texture decorationTexture = null;
	/** A physical block attached to this tile. */
	private Block physicsBlock = null;
	
	/**
	 * Get this tiles background texture.
	 * @return background texture.
     */
	public Texture getBackgroundTexture() { return backgroundTexture; }

	/**
	 * Set this tiles background texture.
	 * @param backgroundTexture
     */
	public void setBackgroundTexture(Texture backgroundTexture) { this.backgroundTexture = backgroundTexture; }
	
	/**
	 * Get this tiles decoration texture.
	 * @return decoration texture.
     */
	public Texture getDecorationTexture() { return decorationTexture; }

	/**
	 * Set this tiles decoration texture.
	 * @param decorationTexture
     */
	public void setDecorationTexture(Texture decorationTexture) { this.decorationTexture = decorationTexture; }

	/**
	 * Get the x position of this tile.
	 * @return x position.
	 */
	public int getX() { return x; }
	
	/**
	 * Get the y position of this tile.
	 * @return y position.
	 */
	public int getY() { return y; }

	/**
	 *
	 * Set the x position of this tile.
	 * @param x
     */
	public void setX(int x) { this.x = x; }

	/**
	 * Set the y position of this tile.
	 * @param y
	 */
	public void setY(int y) { this.y = y; }

	/**
	 * Get the physics block of this tile.
	 * @return block
	 */
	public Block getPhysicsBlock() { return physicsBlock; }

	/**
	 * Set the physics block of this tile.
	 * @param physicsBlock
	 */
	public void setPhysicsBlock(Block physicsBlock) { this.physicsBlock = physicsBlock; }
	
	/**
	 * Draw the background of this tile.
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawBackground(SpriteBatch batch, float xOffset, float yOffset) {
		if(backgroundTexture != null) {
			batch.draw(backgroundTexture, (x * C.LAYOUT_TILE_SIZE) + xOffset,
					(y * C.LAYOUT_TILE_SIZE) + yOffset, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		}
	}
	
	/**
	 * Draw the decoration of this tile.
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawDecoration(SpriteBatch batch, float xOffset, float yOffset) {
		if(decorationTexture != null) {
			batch.draw(decorationTexture, (x * C.LAYOUT_TILE_SIZE) + xOffset,
					(y * C.LAYOUT_TILE_SIZE) + yOffset, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		}
	}
}
