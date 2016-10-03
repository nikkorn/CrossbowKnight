package com.dumbpug.crossbowknight.leveleditor;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Block;
import com.dumbpug.crossbowknight.level.Block.TileBlockFillType;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.tiles.Tile;

public class EditableLevel extends Level {
	
	/**
	 * Create a new instance of EditableLevel.
	 */
	public EditableLevel() {
		super();
		// Set our empty level tiles list.
		this.setLevelTiles(new ArrayList<Tile>());
	}
	
	/**
	 * Get a tile at a specific x/y position.
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getTileAt(int x, int y) {
		for(Tile tile: this.getLevelTiles()) {
			if(tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		// Didn't find the tile.
		return null;
	}
	
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
		this.getLevelTiles().add(tile);
		return tile;
	}
	
	/**
	 * Remove a level tile.
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		this.getLevelTiles().remove(tile);
	}
	
	/**
	 * Returns true if this tile has nothing on it.
	 * @param targetTile
	 * @return is blank
	 */
	public boolean isTileBlank(Tile targetTile) {
		return (targetTile.getBackgroundTexture() == null && targetTile.getDecorationTexture() == null && targetTile.getPhysicsBlock() == null);
	}
	
	/**
	 * Draw the editable level.
	 * @param batch
	 * @param tileOffsetX
	 * @param tileOffsetY
	 */
	public void draw(SpriteBatch batch, int tileOffsetX, int tileOffsetY) {
		// Draw all tile backgrounds.
		for(Tile tile : getLevelTiles()) {
			if(tile.getBackgroundTexture() != null) {
				batch.draw(tile.getBackgroundTexture().getTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw all tile decorations.
		for(Tile tile : getLevelTiles()) {
			if(tile.getDecorationTexture() != null) {
				batch.draw(tile.getDecorationTexture().getTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw all level blocks.
		for(Tile tile : getLevelTiles()) {
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
			
		// ...
		
	}
}
