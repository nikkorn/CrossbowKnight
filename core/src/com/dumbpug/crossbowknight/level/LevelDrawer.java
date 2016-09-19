package com.dumbpug.crossbowknight.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Responsible for drawing a level.
 * @author nikolas.howard
 */
public class LevelDrawer {
	/** The level to draw. */
	private Level level;
	
	/**
	 * Creates a new instance of the LevelDrawer class.
	 * @param level The level to draw.
	 */
	public LevelDrawer(Level level) {
		this.level = level;
	}
	
	/**
	 * Draw the level.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		drawBackgroundLayer(batch);
		drawMiddleLayer(batch);
		drawForegroundLayer(batch);
	}

	/**
	 * Draw everything in the levels background layer.
	 * @param batch
	 */
	private void drawBackgroundLayer(SpriteBatch batch) {
		// Draw all level tile backgrounds.
		for(Tile tile : level.getLevelTiles()) {
			tile.drawBackground(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
		}
		// Draw all level tile decorations.
		for(Tile tile : level.getLevelTiles()) {
			tile.drawDecoration(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY()); 
		}
	}

	/**
	 * Draw everything in the levels middle layer.
	 * @param batch
	 */
	private void drawMiddleLayer(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Draw everything in the levels foreground layer.
	 * @param batch
	 */
	private void drawForegroundLayer(SpriteBatch batch) {
		// Draw all level blocks.
		for(Tile tile : level.getLevelTiles()) {
			Block block = tile.getPhysicsBlock();
			if(block != null) {
				float drawPositionX = (tile.getX() * C.LAYOUT_TILE_SIZE) + level.getLevelCamera().getX();
				float drawPositionY = (tile.getY() * C.LAYOUT_TILE_SIZE) + level.getLevelCamera().getY();
				block.draw(batch, drawPositionX, drawPositionY);
			}
		}
	}
}
