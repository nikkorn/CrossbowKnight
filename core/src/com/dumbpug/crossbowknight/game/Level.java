package com.dumbpug.crossbowknight.game;

import java.util.ArrayList;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** All tiles in this level. */
	private ArrayList<Tile> levelTiles = new ArrayList<Tile>();
	
	/**
	 * Create a new instance of the Level class.
	 */
	public Level() {
		
	}

	/**
	 * Get all level tiles.
	 * @return level tiles.
	 */
	public ArrayList<Tile> getLevelTiles() {
		return levelTiles;
	}

	/**
	 * Set all level tiles.
	 * @param levelTiles.
	 */
	public void setLevelTiles(ArrayList<Tile> levelTiles) {
		this.levelTiles = levelTiles;
	}
}
