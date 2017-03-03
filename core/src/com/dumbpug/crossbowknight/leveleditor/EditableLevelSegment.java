package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Block;
import com.dumbpug.crossbowknight.level.Block.TileBlockFillType;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents a level segment to which we can add tiles.
 * @author nikolas.howard
 *
 */
public class EditableLevelSegment extends LevelSegment {
	
	/**
	 * Add a new tile at the x/y position.
	 * @param x
	 * @param y
	 * @return The new tile.
	 */
	public Tile addNewTileAt(int x, int y) {
		Tile tile = new Tile();
		tile.setX(x);
		tile.setY(y);
		this.getTiles().add(tile);
		return tile;
	}
	
	/**
	 * Remove a segment tile.
	 * @param tile
	 */
	public void removeTile(Tile tile) { this.getTiles().remove(tile); }
	
	/**
	 * Draw the editable level.
	 * @param batch
	 * @param levelEditorTextures
	 * @param tileOffsetX
	 * @param tileOffsetY
	 */
	public void draw(SpriteBatch batch, LevelEditorTextures levelEditorTextures, int tileOffsetX, int tileOffsetY) {
		// Draw all tile backgrounds.
		for(Tile tile : this.getTiles()) {
			if(tile.getBackgroundTexture() != null) {
				batch.draw(tile.getBackgroundTexture().getTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw all tile decorations.
		for(Tile tile : this.getTiles()) {
			if(tile.getDecorationTexture() != null) {
				batch.draw(tile.getDecorationTexture().getTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw all level blocks.
		for(Tile tile : this.getTiles()) {
			Block block = tile.getPhysicsBlock();
			if(block != null) {
				// Get the size of this block relative to the size of the editor.
				float blockWidth  = (block.getFillType() == TileBlockFillType.LEFT_HALF 
						|| block.getFillType() == TileBlockFillType.RIGHT_HALF) ? C.TILE_SIZE / 2 : C.TILE_SIZE;
				float blockHeight = (block.getFillType() == TileBlockFillType.TOP_HALF 
						|| block.getFillType() == TileBlockFillType.BOTTOM_HALF) ? C.TILE_SIZE / 2 : C.TILE_SIZE;
				// Get the position of this block relative to the size of the editor.
				float blockXPosition = (tile.getX() - tileOffsetX) * C.TILE_SIZE;
				blockXPosition += (block.getFillType() == TileBlockFillType.RIGHT_HALF) ? C.TILE_SIZE / 2 : 0;
				float blockYPosition = (tile.getY() - tileOffsetY) * C.TILE_SIZE;
				blockYPosition += (block.getFillType() == TileBlockFillType.TOP_HALF) ? C.TILE_SIZE / 2 : 0;
				// Draw the block.
				batch.draw(block.getBlockTexture().getTexture(), blockXPosition, blockYPosition, blockWidth, blockHeight);
			}
		}
		// Draw connectors.
		for(Connector connector : this.getConnectors()) {
			// Get the correct texture based on the connector type 
			Texture texture = connector.getConnecterType() == ConnectorType.ENTRANCE ? 
					levelEditorTextures.getConnectorEntranceTexture() : 
					levelEditorTextures.getConnectorExitTexture();
			// Draw the texture for each tile the connector covers.
			for(int heightOffset = 0; heightOffset < connector.getTileHeight(); heightOffset++) {
				batch.draw(texture, (connector.getTilePositionX() - tileOffsetX) * C.TILE_SIZE, ((connector.getTilePositionY() + heightOffset) - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
	}
}
