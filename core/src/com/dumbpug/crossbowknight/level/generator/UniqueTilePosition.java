package com.dumbpug.crossbowknight.level.generator;

import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents a unique tile position.
 * @author nikolas.howard
 */
public class UniqueTilePosition {
	/** The x tile position. */
	public int x = 0;
	/** The y tile position. */
	public int y = 0;
	/** Whether this tile has been overlapped */
	public boolean overlapped = false;
	/** The tile associated with this tile position. */
	public Tile tile = null;
}
