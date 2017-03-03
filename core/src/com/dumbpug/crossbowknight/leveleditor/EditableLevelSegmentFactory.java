package com.dumbpug.crossbowknight.leveleditor;

import com.dumbpug.crossbowknight.level.LevelFactory;

/**
 * Creates editable levels.
 * @author nikolas.howard
 */
public class EditableLevelSegmentFactory extends LevelFactory {
	
	/**
	 * Read an existing level segment from disk into an editable level.
	 * @param segmentName
	 * @return editable level segment
	 */
	public static EditableLevelSegment getEditableSegmentFromDisk(String segmentName) {
		return (EditableLevelSegment) LevelFactory.getLevelSegmentFromDisk(segmentName);
	}
}
