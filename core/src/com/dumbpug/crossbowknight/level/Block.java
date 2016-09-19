package com.dumbpug.crossbowknight.level;

import com.badlogic.gdx.graphics.Texture;
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
	private Texture blockTexture = null;
	
	/**
	 * Creates an instance of the Block class.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Block(float x, float y, float width, float height) {
		super(x, y, width, height, NBPBoxType.STATIC);
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
	public void setBlockTexture(Texture blockTexture) { this.blockTexture = blockTexture; }
	
	/**
	 * Draw this block 
	 * @param batch
	 * @param xPosition
	 * @param yPosition
	 */
	public void draw(SpriteBatch batch, float xPosition, float yPosition) {
		// Draw the texture for this block.
		if(blockTexture != null) {
			batch.draw(blockTexture, xPosition, yPosition, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		}
	}
}
