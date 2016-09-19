package com.dumbpug.crossbowknight.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	private Texture blockTexture = null;
	/** The texture region for this block when we are not using the whole tile texture. */
	private TextureRegion blockTextureRegion = null;
	/** The tile fill type of this block */
	private TileBlockFillType fillType;

	/** The position of this block within it's parent tile */
	public enum TileBlockFillType {
		FULL,
		TOP_HALF,
		BOTTOM_HALF
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
		this.fillType = fillType;
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
	public Texture getBlockTexture() { return blockTexture; }

	/**
	 * Set this blocks texture.
	 * @param blockTexture
     */
	public void setBlockTexture(Texture blockTexture) {
		// Set the block texture.
		this.blockTexture = blockTexture;
		// Based on the tile fill type of this block, we may need to grab only a texture region.
		if(fillType != TileBlockFillType.FULL) {
			// Get only the portion of the tile we want to draw
			blockTextureRegion = new TextureRegion(blockTexture, 0f,
					0f, 1f, 0.5f);
		}
	}
	
	/**
	 * Draw the texture for this block.
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void draw(SpriteBatch batch, float xOffset, float yOffset) {
		// Draw the texture for this block.
		if(blockTextureRegion != null) {
			batch.draw(blockTextureRegion,
					(this.getX() * C.LAYOUT_MULTIPLIER) + xOffset,
					(this.getY() * C.LAYOUT_MULTIPLIER) + yOffset,
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER),
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER) / 2);
		} else if(blockTexture != null) {
			batch.draw(blockTexture,
					(this.getX() * C.LAYOUT_MULTIPLIER) + xOffset,
					(this.getY() * C.LAYOUT_MULTIPLIER) + yOffset,
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER),
					(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER));
		}
	}
}
