package com.dumbpug.crossbowknight.level;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.level.generator.LevelSegment;
import com.dumbpug.crossbowknight.leveleditor.Connector;
import com.dumbpug.crossbowknight.leveleditor.Marker;

/**
 * Creates levels and level segments.
 * @author nikolas.howard
 */
public class LevelFactory {
	
	/**
	 * Read an existing level world from disk.
	 * @param levelName
	 * @return level
	 */
	public static LevelWorld getLevelWorldFromDisk(String levelName) {
		// Initialise a new level world.
		LevelWorld levelWorld = new LevelWorld();
		// Set the level tiles.
		levelWorld.setTiles(LevelReader.readLevelTilesFromDisk(levelName, C.SAVED_LEVELS_DIR));
		
		// TODO Read spawn point.
		
		// Return our built level.
		return levelWorld;
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
		// Read level markers.
		for(Marker marker : LevelReader.readLevelMarkersFromDisk(segmentName, C.SAVED_LEVEL_SEGMENTS_DIR)) {
			levelSegment.addMarker(marker);
		}
		// Return our built level.
		return levelSegment;
	}
}
