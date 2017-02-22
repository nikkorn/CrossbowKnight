package com.dumbpug.crossbowknight.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public LevelDrawer(Level level) { this.level = level; }
	
	/**
	 * Draw the level.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		drawBackgroundLayer(batch);
		drawMiddleLayer(batch);
		drawForegroundLayer(batch);
		drawCharacters(batch);
		drawForegroundEffects(batch);
	}

	/**
	 * Draw everything in the levels background layer.
	 * @param batch
	 */
	private void drawBackgroundLayer(SpriteBatch batch) {
		// Draw all level tile backgrounds.
		for(Tile tile : level.getLevelWorld().getTiles()) {
			tile.drawBackground(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
		}
		// Draw all level tile decorations.
		for(Tile tile : level.getLevelWorld().getTiles()) {
			tile.drawDecoration(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY()); 
		}
		// Draw the top layers of our tiles.
		for(Tile tile : level.getLevelWorld().getTiles()) {
			tile.drawTopLayer(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY()); 
		}
	}

	/**
	 * Draw everything in the levels middle layer.
	 * @param batch
	 */
	private void drawMiddleLayer(SpriteBatch batch) {
		// Draw the level projectiles.
		level.getLevelWorld().getProjectilePool().draw(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
		// Draw the level items.
		level.getLevelWorld().getItemPool().draw(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
	}

	/**
	 * Draw everything in the levels foreground layer.
	 * @param batch
	 */
	private void drawForegroundLayer(SpriteBatch batch) {
		// Draw all level blocks.
		for(Tile tile : level.getLevelWorld().getTiles()) {
			if(tile.getPhysicsBlock() != null) {
				tile.getPhysicsBlock().draw(batch, level.getLevelCamera().getX(), level.getLevelCamera().getY());
			}
		}
		// Draw any interfaces which are part of player/tile interactions.
		level.getLevelWorld().getTileInteractionFacilitator().drawTileInteractionElements(batch);
	}
	
	/**
	 * Draw characters in the level.
	 * @param batch
	 */
	private void drawCharacters(SpriteBatch batch) {
		// Draw the characters.
		level.getLevelWorld().getCharacterPool().draw(batch, level.getLevelCamera());
	}
	
	/**
	 * Draw effects (e.g. particles) at the top-most layer.
	 * @param batch
	 */
	private void drawForegroundEffects(SpriteBatch batch) {
		// Draw all emitter particles.
		level.getLevelWorld().getEmitterPool().draw();
	}
}
