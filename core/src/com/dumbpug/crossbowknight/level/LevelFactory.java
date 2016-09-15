package com.dumbpug.crossbowknight.level;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.crossbowknight.tiles.TileTextures;
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
		
		return level;
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
	 * @param existingLevel existing level.
	 * @param doorId id of the door in the existing level we are coming from.
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
			throw new RuntimeException("Missing map resources in '" + new File(C.SAVED_LEVELS_DIR + levelName).getAbsolutePath() + "'");
		}
		// Convert our map resources to JSON.	
		JSONArray tileBackgroundsJSONArray = Helpers.readJSONArrayFromFile(tileBackgroundsFile);
		JSONArray tileBlocksJSONArray      = Helpers.readJSONArrayFromFile(tileBlocksFile);
		JSONArray tileDecorationsJSONArray = Helpers.readJSONArrayFromFile(tileDecorationsFile);
		// Create tiles which have backgrounds.
		for(int tileBackgroundIndex = 0; tileBackgroundIndex < tileBackgroundsJSONArray.length(); tileBackgroundIndex++) {
			int xPos   = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("x");
			int yPos   = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("y");
			int bgType = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("typeId");
			// Get our background texture.
			TileTextures.BackgroundTile backgroundTile = TileTextures.BackgroundTile.values()[bgType];
			Texture backgroundTexture = TileTextures.getTileTextures().getBackgroundTileTexture(backgroundTile);
			// Does our map contain a tile at the same position as the current entry
			if(tileMap.containsKey(xPos + "-" + yPos)) {
				// Get our existing tile from the map.
				Tile tile = tileMap.get(xPos + "-" + yPos);
				// Set the background texture on our tile.
				tile.setBackgroundTexture(backgroundTexture);
			} else {
				Tile tile = new Tile();
				tile.setX(xPos);
				tile.setY(yPos);
				// Set the background texture on our tile.
				tile.setBackgroundTexture(backgroundTexture);
				// Add our newly created tile to our map.
				tileMap.put(xPos + "-" + yPos, tile);
			}
		}

		// ...
		
		// Return the tiles in our map as a list.
		return new ArrayList<Tile>(tileMap.values());
	}
}
