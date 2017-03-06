package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.leveleditor.Connector;
import com.dumbpug.crossbowknight.leveleditor.LevelSegment;

/**
 * Creates levels and level segments.
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
		level.getLevelWorld().setTiles(LevelReader.readLevelTilesFromDisk(levelName, C.SAVED_LEVELS_DIR));
		
		// TODO Read spawn point.
		
		// Return our built level.
		return level;
	}
	
	/**
	 * Read an existing level segment from disk.
	 * @param levelName
	 * @return level
	 */
	public static LevelSegment getLevelSegmentFromDisk(String segmentName) {
		// Initialise a new Level.
		LevelSegment levelSegment = new LevelSegment();
		
		// Populate level segment object from disk.
		levelSegment.setTiles(LevelReader.readLevelTilesFromDisk(segmentName, C.SAVED_LEVEL_SEGMENTS_DIR));
		
		// Read level connectors.
		for(Connector connector : LevelReader.readLevelConnectorsFromDisk(segmentName, C.SAVED_LEVEL_SEGMENTS_DIR)) {
			levelSegment.addConnector(connector);
		}
		
		// TODO Read special tiles
		
		// TODO Read enemy tiles
		
		// Return our built level.
		return levelSegment;
	}
}
