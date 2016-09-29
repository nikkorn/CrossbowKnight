package com.dumbpug.crossbowknight.leveleditor;

import com.dumbpug.crossbowknight.level.LevelFactory;

/**
 * Creates editable levels.
 * @author nikolas.howard
 */
public class EditableLevelFactory extends LevelFactory {
	
	/**
	 * Read an existing level from disk into an editable level.
	 * @param levelName
	 * @return editable level
	 */
	public static EditableLevel getEditableLevelFromDisk(String levelName) {
		// Initialise a new editable Level.
		EditableLevel level = new EditableLevel();
		// Set the level tiles.
		buildLevelFromDisk(level, levelName);
		
		//...
		
		return level;
	}
}
