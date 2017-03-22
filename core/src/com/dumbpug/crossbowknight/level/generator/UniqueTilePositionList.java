package com.dumbpug.crossbowknight.level.generator;

import java.util.ArrayList;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.Debug;
import com.dumbpug.crossbowknight.Debug.Type;

/**
 * Represents a list of tile positions where not positions can overlap.
 * @author nikolas.howard
 */
public class UniqueTilePositionList {
	/** The tile positions that have been encountered. */
	private ArrayList<UniqueTilePosition> tilePositions = new ArrayList<UniqueTilePosition>();
	/** Whether an overlap has happened. */
	private boolean overlapHappened = false;
	
	/**
	 * Attempt to add a new unique tile position. Returns true if successful.
	 * @param newPosition
	 * @return successful
	 */
	public void add(UniqueTilePosition newPosition) {
		// Check to make sure we don't have an overlap
		for(UniqueTilePosition existing : tilePositions) {
			if((existing.x == newPosition.x) && (existing.y == newPosition.y)) {
				// We had an overlap, don't add the new position and return false.
				existing.overlapped = true;
				overlapHappened     = true;
				return;
			}
		}
		// We had no overlap. Add the new tile position.
		tilePositions.add(newPosition);
	}
	
	/**
	 * Returns true if any tiles that were added to this list have overlapped.
	 * @return overlap happened.
	 */
	public boolean hasOverlaps() {
		// If we are writing to debug window. Draw a representation of how the tiles have overlapped.
		if(overlapHappened && C.WRITE_DEBUG_OUTPUT) {
			drawStateToDebug();
		}
		return overlapHappened;
	}
	
	/**
	 * Draw a representation of the tile positions
	 */
	private void drawStateToDebug() {
		// Get the dimensions of the arranged tiles.
		int highestXPos = 0;
		int lowestXPos  = 0;
		int highestYPos = 0;
		int lowestYPos  = 0;
		for(UniqueTilePosition tile : tilePositions) {
			if(tile.x > highestXPos) highestXPos = tile.x;
			if(tile.x < lowestXPos) lowestXPos = tile.x;
			if(tile.y > highestYPos) highestYPos = tile.y;
			if(tile.y < lowestYPos) lowestYPos = tile.y;
		}
		// We cannot draw anything if we are not at least one tile wide/high.
		int width  = highestXPos - lowestXPos;
		int height = highestYPos - lowestYPos;
		if(width == 0 || height == 0) {
			return;
		}
		Debug.out(Type.WARNING, "Overlapping Tiles!");
		System.out.println();
		// Print our tiles to the console, showing overlaps if any.
		for(int y = highestYPos; y >= lowestYPos; y--) {
			for(int x = lowestXPos; x <= highestXPos; x++) {
				UniqueTilePosition position = null;
				// Look for a tile position at these x/y coords.
				for(UniqueTilePosition existing : tilePositions) {
					if((existing.x == x) && (existing.y == y)) {
						position = existing;
						break;
					}
				}
				// Print a tile or empty space. 
				if(position == null) {
					System.out.print(" ");
				} else {
					// Overlapped tiles should be represented with a X.
					if(position.overlapped) {
						System.out.print("X");
					} else {
						// Tiles which have physics blocks should be displayed differently.
						System.out.print(position.tile.getPhysicsBlock() != null ? "#" : "O");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
