package com.dumbpug.crossbowknight.leveleditor;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.tiles.Tile;

public class EditableLevel extends Level {
	
	/**
	 * Create a new instance of EditableLevel.
	 */
	public EditableLevel() {
		super(true);
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
	 * Remove a level tile.
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		this.getLevelTiles().remove(tile);
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
	 * Returns true if this tile has nothing on it.
	 * @param targetTile
	 * @return is blank
	 */
	public boolean isTileBlank(Tile targetTile) {
		return (targetTile.getBackgroundTexture() == null && targetTile.getDecorationTexture() == null && targetTile.getPhysicsBlock() == null);
	}
	
	/**
	 * Draw the editable level.
	 */
	public void draw(SpriteBatch batch, int tileOffsetX, int tileOffsetY) {
		// Draw all tile backgrounds.
		for(Tile tile : getLevelTiles()) {
			if(tile.getBackgroundTexture() != null) {
				batch.draw(tile.getBackgroundTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw all tile decorations.
		for(Tile tile : getLevelTiles()) {
			if(tile.getDecorationTexture() != null) {
				batch.draw(tile.getDecorationTexture(), (tile.getX() - tileOffsetX) * C.TILE_SIZE, (tile.getY() - tileOffsetY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
			
		// ...
		
	}
}
