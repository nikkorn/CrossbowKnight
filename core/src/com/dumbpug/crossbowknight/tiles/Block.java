package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * Represents a plain world block.
 * @author nikolas.howard
 */
public class Block extends NBPBox {
	/** The texture for this block */
	private IndexedTileTexture blockTexture = null;
	/** The tile fill type of this block */
	private TileBlockFillType fillType;

	/** The position of this block within it's parent tile */
	public enum TileBlockFillType {
		FULL,
		TOP_HALF,
		BOTTOM_HALF,
		LEFT_HALF,
		RIGHT_HALF
	}
	
	/**
	 * Creates an instance of the Block class.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Block(float x, float y, float width, float height, TileBlockFillType fillType) {
		super(x, y, width, height, NBPBoxType.STATIC);
		this.setFillType(fillType);
	}
	
	/**
	 * Creates an instance of the Block class.
	 * Copying properties of another Block and applying a x/y offset.
	 * @param another
	 * @param offsetX
	 * @param offsetY
	 */
	public Block(Block another, int offsetX, int offsetY) {
		super(another.getX() + (offsetX * C.LAYOUT_TILE_SIZE), 
				another.getY() + (offsetY * C.LAYOUT_TILE_SIZE), 
				another.getWidth(), 
				another.getHeight(), 
				NBPBoxType.STATIC);
		this.setFillType(another.getFillType());
		this.setBlockTexture(another.getBlockTexture());
	}

	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onBeforeUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onAfterUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDeletion() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get this blocks texture.
	 * @return block texture.
     */
	public IndexedTileTexture getBlockTexture() { return blockTexture; }

	/**
	 * Set this blocks texture.
	 * @param blockTexture
     */
	public void setBlockTexture(IndexedTileTexture blockTexture) { this.blockTexture = blockTexture; }
	
	/**
	 * Get the block fill type for the parent tile.
	 * @return fillType
	 */
	public TileBlockFillType getFillType() { return fillType; }

	/**
	 * Set the block fill type for the parent tile.
	 * @param fillType
	 */
	public void setFillType(TileBlockFillType fillType) { this.fillType = fillType; }
	
	/**
	 * Draw the texture for this block.
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void draw(SpriteBatch batch, float xOffset, float yOffset) {
		// Draw the texture for this block.
		if(blockTexture != null) {
			batch.draw(blockTexture.getTexture(),
					(this.getX() * C.LAYOUT_MULTIPLIER) + xOffset,
					(this.getY() * C.LAYOUT_MULTIPLIER) + yOffset,
					(this.getWidth() * C.LAYOUT_MULTIPLIER),
					(this.getHeight() * C.LAYOUT_MULTIPLIER));
		}
	}
}
