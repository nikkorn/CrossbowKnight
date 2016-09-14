package com.dumbpug.crossbowknight.game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.Helpers;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Creates level.
 * @author nikolas.howard
 */
public class LevelFactory {
	
	/**
	 * Read an existing level from disk.
	 * @param levelName
	 * @return level
	 */
	public static Level getLevelFromDisk(String levelName) {
		// Initialise a new Level.
		Level level = new Level();
		// Set the level tiles.
		level.setLevelTiles(LevelFactory.readLevelTilesFromDisk(levelName));
		
		//...
		
		return null;
	}
	
	/**
	 * Randomly generate a level.
	 * @param lowerLevelLimit
	 * @param upperLevelLimit
	 * @return level
	 */
	public static Level generateLevel(int lowerLevelLimit, int upperLevelLimit) {
		return null;
	}
	
	/**
	 * Randomly generate a level which leads on from an existing one.
	 * @param existing level.
	 * @param The id of the door in the existing level we are coming from.
	 * @return level
	 */
	public static Level generateLevel(Level existingLevel, int doorId) {
		return null;
	}
	
	/**
	 * Read level tiles from disk.
	 * @param levelName
	 * @return level tiles.
	 */
	private static ArrayList<Tile> readLevelTilesFromDisk(String levelName) {
		// Create a map of tiles where a x/y position maps to a tile.
		HashMap<String, Tile> tileMap = new HashMap<String, Tile>();
		// Ensure that we have the resources necessary to create our tiles.
		File tileBackgroundsFile = new File(C.SAVED_LEVELS_DIR + levelName + "/background");
		File tileBlocksFile      = new File(C.SAVED_LEVELS_DIR + levelName + "/blocks");
		File tileDecorationsFile = new File(C.SAVED_LEVELS_DIR + levelName + "/decorations");
		// Error if we have missing resources.
		if(!tileBackgroundsFile.exists() || !tileBackgroundsFile.exists() || !tileBackgroundsFile.exists()) {
			throw new RuntimeException("Missing map resources!");
		}
		// Convert our map resources to JSON.	
		JSONArray tileBackgroundsJSONArray = Helpers.readJSONArrayFromFile(tileBackgroundsFile);
		JSONArray tileBlocksJSONArray      = Helpers.readJSONArrayFromFile(tileBlocksFile);
		JSONArray tileDecorationsJSONArray = Helpers.readJSONArrayFromFile(tileDecorationsFile);
		
		// ...
		
		// Return the tiles in our map as a list.
		return new ArrayList<Tile>(tileMap.values());
	}
}
