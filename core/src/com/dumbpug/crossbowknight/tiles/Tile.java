package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.level.Block;

/**
 * Represents a world tile.
 * @author nikolas.howard
 */
public class Tile {
	/** X/Y position of this tile. */
	protected int x;
	protected int y;
	/** The background texture for this tile */
	private IndexedTileTexture backgroundTexture = null;
	/** The decoration texture for this tile */
	private IndexedTileTexture decorationTexture = null;
	/** A physical block attached to this tile. */
	private Block physicsBlock = null;
	
	/**
	 * Get this tiles background texture.
	 * @return background texture.
     */
	public IndexedTileTexture getBackgroundTexture() { return backgroundTexture; }

	/**
	 * Set this tiles background texture.
	 * @param backgroundTexture
     */
	public void setBackgroundTexture(IndexedTileTexture backgroundTexture) { this.backgroundTexture = backgroundTexture; }
	
	/**
	 * Get this tiles decoration texture.
	 * @return decoration texture.
     */
	public IndexedTileTexture getDecorationTexture() { return decorationTexture; }

	/**
	 * Set this tiles decoration texture.
	 * @param decorationTexture
     */
	public void setDecorationTexture(IndexedTileTexture decorationTexture) { this.decorationTexture = decorationTexture; }

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
	 * Get the type of this door.
	 * @return type
	 */
	public TileType getType() { return TileType.NORMAL; }
	
	/**
	 * Draw the background of this tile.
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawBackground(SpriteBatch batch, float xOffset, float yOffset) {
		if(backgroundTexture != null) {
			batch.draw(backgroundTexture.getTexture(),
					(x * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + xOffset,
					(y * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + yOffset,
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER),
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER));
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
			batch.draw(decorationTexture.getTexture(),
					(x * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + xOffset,
					(y * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + yOffset,
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER),
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER));
		}
	}
	
	/**
	 * Draw the top layer of this tile.
	 * This is only supposed to be implemented by specific tile types (e.g doors).
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawTopLayer(SpriteBatch batch, float xOffset, float yOffset) {}
}
