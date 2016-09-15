package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** The name of this level */
	private String name = "";
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

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }
}
