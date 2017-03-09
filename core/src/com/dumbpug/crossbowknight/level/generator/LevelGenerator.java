package com.dumbpug.crossbowknight.level.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;
import com.dumbpug.crossbowknight.tiles.door.Door;

/**
 * Responsible for procedurally generating levels using level segments.
 * @author nikolas.howard
 */
public class LevelGenerator {
	/** The segments to use in the construction of a level. */
	private ArrayList<LevelSegment> segments = new ArrayList<LevelSegment>();
	/** The RNG to use in level generation. */
	private Random random = new Random(12345);
	
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
		// Create our segment partition list.
		ArrayList<SegmentPartition> partitions = new ArrayList<SegmentPartition>();
		// Determine the segment depth of this level.
		int levelTotalDepth   = 5; // TODO Randomise.
		
		// TODO Get a random LEFT_EDGE segment to start with. Must have a door!
		// TODO Pass it into our initial segment partition.
		SegmentPartition initialPartition = new SegmentPartition();
		initialPartition.segments.add(this.grabRandomSegmentOfType(LevelSegmentType.LEFT_EDGE));
		partitions.add(initialPartition);
		
		// Create segment partitions until we rach our max depth.
		for(int depth = 0; depth < levelTotalDepth; depth++) {
			// Generate a new partition.
			SegmentPartition next = this.generateSegmentPartition(partitions.get(depth), depth == levelTotalDepth - 1);
			
			// TODO Check that no tiles in any segments in the new partition overlap any other segment tiles.
			// If any do, keep generating a new partition until they do not.
			// This may happen forever, so if we generate a partition like a 100 times an it always
			// clashes with previous ones then discard the last partition in the partitions list, minus the depth, and try again.
			
			// TODO if the new partition does not clash with any others, add it to our partitions list and carry on to the next depth.
			partitions.add(next);
		}
		
		Level level = new Level();
		// TODO Fill level with stuff.
		return level;
	}
	
	/**
	 * Takes a segment partition and creates the next partition along containing connecting segments.
	 * @param current
	 * @param atDepthLimit If true, this partition must close off with RIGHT_EDGE segments.
	 * @return next segment partition
	 */
	private SegmentPartition generateSegmentPartition(SegmentPartition current, boolean atDepthLimit) {
		
		// TODO Will need to pass in all partitions as segments of different lengths can cross over partitions.
		
		return null;
	}
	
	/** 
	 * Grab a random segment matching the specified type.
	 * @param type
	 * @return segment
	 */
	private LevelSegment grabRandomSegmentOfType(LevelSegmentType type) {
		// Get a list of all segments which match the specified type.
		ArrayList<LevelSegment> matchingSegments = new ArrayList<LevelSegment>();
		for(LevelSegment segment : this.segments) {
			if(segment.getLevelSegmentType() == type) {
				matchingSegments.add(segment);
			}
		}
		// We cannot continue if we don't have a single segment with a matching type.
		if(matchingSegments.isEmpty()) {
			System.out.println("Error: no matching segments with type of " + type);
			System.exit(1);
		}
		// Randomly select one of these matching segments to return.
		return matchingSegments.get(random.nextInt(matchingSegments.size()));
	}
}
