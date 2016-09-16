package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.Texture;
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
}
