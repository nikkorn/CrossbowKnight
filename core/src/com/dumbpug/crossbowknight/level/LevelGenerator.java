package com.dumbpug.crossbowknight.level;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.leveleditor.LevelSegment;
import com.dumbpug.crossbowknight.tiles.door.Door;

/**
 * Responsible for procedurally generating levels using level segments.
 * @author nikolas.howard
 */
public class LevelGenerator {
	/** The segments to use in the construction of a level. */
	private ArrayList<LevelSegment> segments = new ArrayList<LevelSegment>();
	
	/**
	 * Create a new instance of the LevelGenerator class.
	 */
	public LevelGenerator() {
		// Read all level segments from disk.
		populateSegmentsList();
	}
	
	/** 
	 * Read all level segments into our segments list
	 * for later use in generating complete levels.
	 */
	private void populateSegmentsList() {
		// Get the directory in which the level segments are stored on disk.
		File segmentsDir = new File(C.SAVED_LEVEL_SEGMENTS_DIR);
		// Check that our directory exists.
		if(!(segmentsDir.exists() && segmentsDir.isDirectory())) {
			// We cannot continue without our level segments!
			System.out.println("Error: Cannot load level segments from disk, '" + segmentsDir.getAbsolutePath() + "' directory does not exist");
			Gdx.app.exit();
		}
		// Load every segment individually.
		for(File segmentDir : segmentsDir.listFiles()) {
			segments.add(LevelFactory.getLevelSegmentFromDisk(segmentDir.getName()));
		}
	}
	
	/**
	 * Randomly generate a level.
	 * @param sourceDoor
	 * @param sourceLevel
	 * @return level
	 */
	public Level generateLevel(Door sourceDoor, Level sourceLevel) {
		return null;
	}
}
