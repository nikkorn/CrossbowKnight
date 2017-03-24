package com.dumbpug.crossbowknight.level.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.Debug;
import com.dumbpug.crossbowknight.Debug.Type;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;
import com.dumbpug.crossbowknight.level.LevelWorld;
import com.dumbpug.crossbowknight.leveleditor.Connector;
import com.dumbpug.crossbowknight.leveleditor.ConnectorType;
import com.dumbpug.crossbowknight.lotto.Lotto;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.door.Door;

/**
 * Responsible for procedurally generating level worlds using level segments.
 * @author nikolas.howard
 */
public class LevelWorldGenerator {
	/** The segments to use in the construction of a level. */
	private ArrayList<LevelSegment> segments = new ArrayList<LevelSegment>();
	/** The RNG to use in level generation. */
	private Random random = new Random();
	
	/**
	 * Create a new instance of the LevelGenerator class.
	 */
	public LevelWorldGenerator() {
		// Read all level segments from disk.
		populateSegmentsList();
	}
	
	/** 
	 * Read all level segments into our segments list
	 * for later use in generating complete level worlds.
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
	 * Randomly generate a level world.
	 * @param sourceDoor
	 * @param sourceLevel
	 * @return level
	 */
	public LevelWorld generateLevelWorld(Door sourceDoor, Level sourceLevel) {
		// Create our segment partition list.
		ArrayList<SegmentPartition> partitions = new ArrayList<SegmentPartition>();
		// Determine the segment depth of this level.
		int levelTotalDepth = 10; // TODO Randomise.
		// The number of times we hit our fail limit in trying to generate a partition.
		int overallFailCount = 0;
		
		// Get a random LEFT_EDGE segment to start with. Must have a door!
		// Pass it into our initial segment partition.
		SegmentPartition initialPartition = new SegmentPartition();
		initialPartition.segments.add(this.grabRandomSegmentOfType(LevelSegmentType.LEFT_EDGE, 0));
		partitions.add(initialPartition);
		
		// Create segment partitions until we reach our max depth.
		int depth = 1;
		while(depth < levelTotalDepth) {
			// Whether we were successful in creating a partition at the current depth.
			boolean success = false;
			// The number of time we have failed to create a partition at the current depth.
			int partitionGenerationFailCount = 0;
			// Try to generate a partition at the current depth as long as we haven't failed too many times and we haven't already succeeded.
			while(partitionGenerationFailCount <= C.PROC_GEN_PARTITION_CREATE_RETRY && !success) {
				
				// If we have hit our retry limit then we need to remove the last partition.
				if(partitionGenerationFailCount == C.PROC_GEN_PARTITION_CREATE_RETRY) {
					// Remove last partition (cannot be our first).
					if(partitions.size() > 1) {
						partitions.remove(partitions.size() - 1);
					}
					// We have gone down a depth if we are not already at the bottom depth.
					if(depth > 0) {
						depth--;
					}
					continue;
				}
				
				// Generate a new partition.
				SegmentPartition next = this.generateSegmentPartition(partitions.get(depth - 1), depth == levelTotalDepth - 1);
				
				// Add it to our partition list.
				partitions.add(next);
				
				// Check for failure due to overlaps.
				boolean failure = doPartitionSegmentTilesOverlap(partitions);
				
				// If we had a failure, update our failure count and remove the last partition.
				if(failure) {
					partitionGenerationFailCount++;
					partitions.remove(next);
				} else {
					// If the new partition does not clash with any others, add it to our partitions list and carry on to the next depth.
					depth++;
					success = true;
				}
			}
			
			// Check to see whether we hit out fail limit in attempting to create a partition at the current depth.
			if(!success) {
				if(++overallFailCount == C.PROC_GEN_PARTITION_CREATE_RETRY) {
					// We failed to create our level and cannot go on.
					Debug.out(Type.ERROR, "Reached fail limit when attempting to generate level.");
					System.exit(1);
				}
			}
		}
		
		LevelWorld levelWorld = new LevelWorld();
		// TODO Fill level with stuff.
		return levelWorld;
	}
	
	/**
	 * Takes a segment partition and creates the next partition along containing connecting segments.
	 * @param current
	 * @param atDepthLimit If true, this partition must close off with RIGHT_EDGE segments.
	 * @return next segment partition
	 */
	private SegmentPartition generateSegmentPartition(SegmentPartition current, boolean atDepthLimit) {
		// Create our new partition.
		SegmentPartition newPartition = new SegmentPartition();
		// For each segment in the last partition, create a new segment which connects to each exit connector.
		for(LevelSegment segment : current.segments) {
			for (Connector connector : segment.getConnectors()) {
				// We only care about exit connectors of the previous segment.
				if(connector.getConnecterType() == ConnectorType.ENTRANCE) {
					continue;
				}
				// Determine the type of level segment we want. If we are at the depth limit then this has to be a right edge.
				LevelSegmentType type = LevelSegmentType.RIGHT_EDGE;
				if(!atDepthLimit) {
					// Generate either a corridor or a fork type. Favour corridors.
					type = new Lotto<LevelSegmentType>(random)
						.add(LevelSegmentType.RIGHT_EDGE, 1)
						.add(LevelSegmentType.FORK, 2)
						.add(LevelSegmentType.CORRIDOR, 3)
						.draw();
				}
				// Get a random segment of this type where the entrance connector height matches the current exit connector height.
				LevelSegment connectingSegment = this.grabRandomSegmentOfType(type, connector.getTileHeight());
				// Get the entrance connector of this segment.
				Connector connectingSegmentEntrance = this.getEntranceConnectorOfSegment(connectingSegment);
				// We need to offset the connecting segment based on the position of the previous one and the position of the connectors. TODO Check this!
				connectingSegment.setOffsetX(((segment.getOffsetX() + connector.getTilePositionX()) - connectingSegmentEntrance.getTilePositionX()) + 1);
				connectingSegment.setOffsetY((segment.getOffsetY() + connector.getTilePositionY()) - connectingSegmentEntrance.getTilePositionY());
				// Add this new segment to our new partition.
				newPartition.segments.add(connectingSegment);
			}
		}
		return newPartition;
	}
	
	/**
	 * Get the entrance connector of a segment. It is assumed that segment is not a left edge.
	 * @param segment
	 * @return entrance connector
	 */
	private Connector getEntranceConnectorOfSegment(LevelSegment segment) {
		for(Connector connector : segment.getConnectors()) {
			if(connector.getConnecterType() == ConnectorType.ENTRANCE) {
				return connector;
			}
		}
		throw new RuntimeException("segment has no entrance connector.");
	}
	
	/** 
	 * Grab a random segment matching the specified type.
	 * @param type
	 * @param entranceConnectorHeight If > 0, then we need the segments entrance connector to have this as a height.
	 * @return segment
	 */
	private LevelSegment grabRandomSegmentOfType(LevelSegmentType type, int entranceConnectorHeight) {
		// Get a list of all segments which match the specified type.
		ArrayList<LevelSegment> matchingSegments = new ArrayList<LevelSegment>();
		for(LevelSegment segment : this.segments) {
			if(segment.getLevelSegmentType() == type) {
				// If entranceConnectorHeight > 0  then we are looking to a segment with an 
				// entrance connector with a height matching entranceConnectorHeight.
				if(entranceConnectorHeight > 0) {
					if(this.getEntranceConnectorOfSegment(segment).getTileHeight() == entranceConnectorHeight) {
						matchingSegments.add(segment);
					}
				} else {
					matchingSegments.add(segment);
				}
			}
		}
		// We cannot continue if we don't have a single segment with a matching type.
		if(matchingSegments.isEmpty()) {
			System.out.println("Error: no matching segments with type of " + type);
			System.exit(1);
		}
		// Randomly select a copy of one of these matching segments to return.
		return new LevelSegment(matchingSegments.get(random.nextInt(matchingSegments.size())));
	}
	
	/**
	 * Returns true if any tiles in any segments overlap.
	 * @param existingPartitions
	 * @param newPartition
	 * @return overlap
	 */
	private boolean doPartitionSegmentTilesOverlap(ArrayList<SegmentPartition> partitions) {
		// The unique tile position list.
		UniqueTilePositionList positions = new UniqueTilePositionList();
		for(SegmentPartition partition : partitions) {
			for(UniqueTilePosition utp : this.getTilePositionsFromSegmentPartition(partition)) {
				positions.add(utp);
			}
		}
		// Return whether we had any overlaps.
		return positions.hasOverlaps();
	}
	
	/**
	 * Generate a list of unique tile positions from all segment tiles in a partition. Taking segment offset into account.
	 * @param partition
	 * @return list of unique tile positions
	 */
	private ArrayList<UniqueTilePosition> getTilePositionsFromSegmentPartition(SegmentPartition partition) {
		ArrayList<UniqueTilePosition> positions = new ArrayList<UniqueTilePosition>();
		for(LevelSegment segment : partition.segments) {
			for(Tile tile : segment.getTiles()) {
				// Create a unique tile position for this tiles position.
				UniqueTilePosition position = new UniqueTilePosition();
				position.x    = tile.getX() + segment.getOffsetX();
				position.y    = tile.getY() + segment.getOffsetY();
				position.tile = tile;
				positions.add(position);
			}
		}
		return positions;
	}
}
