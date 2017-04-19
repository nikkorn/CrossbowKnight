package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.tiles.door.Door;

/**
 * Represents a world tile.
 * @author nikolas.howard
 */
public class Tile {
	/** X/Y position of this tile. */
	protected int x, y;
	/** The background texture for this tile */
	private IndexedTileTexture backgroundTexture = null;
	/** The decoration texture for this tile */
	private IndexedTileTexture decorationTexture = null;
	/** A physical block attached to this tile. */
	private Block physicsBlock = null;
	/** A physical block attached to this tile. */
	private Door door = null;
	
	/**
	 * Create a new instance of the Tile class.
	 */
	public Tile() {	}
	
	/**
	 * Create a new instance of the Tile class.
	 * Copying properties of another tile and applying a x/y offset.
	 * @param another
	 * @param offsetX
	 * @param offsetY
	 */
	public Tile(Tile another, int offsetX, int offsetY) {
		// Set the position at the offset.
		this.x = another.getX() + offsetX;
		this.y = another.getY() + offsetY;
		// Copy tile textures.
		this.backgroundTexture = another.getBackgroundTexture();
		this.decorationTexture = another.getDecorationTexture();
		// Copy block if there is one.
		if(another.getPhysicsBlock() != null) {
			// Make a copy of the block, applying a tile offset to the position.
			this.physicsBlock = new Block(another.getPhysicsBlock(), offsetX, offsetY);
		}
	}
	
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
	 * Get the door attached to this tile.
	 * @return door
	 */
	public Door getDoor() { return this.door; }
	
	/**
	 * Set the door attached to this tile.
	 * @param door
	 */
	public void setDoor(Door door) { this.door = door; }

	/**
	 * Set the physics block of this tile.
	 * @param physicsBlock
	 */
	public void setPhysicsBlock(Block physicsBlock) { this.physicsBlock = physicsBlock; }
	
	/**
	 * Get the type of this tile.
	 * @return type
	 */
	public TileType getType() { 
		// If this tile has a door, then it is a door tile.
		if (this.door != null) {
			return TileType.DOOR;
		}
		// This is just a normal tile.
		return TileType.NORMAL; 
	}
	
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
	public void drawTopLayer(SpriteBatch batch, float xOffset, float yOffset) {
		// If this is a door tile then draw the door.
		if (this.getDoor() != null) {
			this.getDoor().draw(batch, x, y, xOffset, yOffset);
		}
	}
}
