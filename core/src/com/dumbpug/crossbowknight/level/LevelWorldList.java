package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;

/**
 * Holds LevelWorlds.
 * @author nikolas.howard
 */
public class LevelWorldList {
	/** The list of all level worlds. */
	private ArrayList<LevelWorld> levelWorlds = new ArrayList<LevelWorld>();
	/** The active level world. */
	private LevelWorld active = null;
	
	/**
	 * Get a level world in this list which has the id specified. 
	 * Returns null if no level world with matching id is found.
	 * @param id
	 * @return matching level world
	 */
	public LevelWorld getById(String id) {
		// Iterate over all level worlds and return the one with a matching id.
		for(LevelWorld world : levelWorlds) {
			if(world.getId().equals(id)) {
				return world;
			}
		}
		// We had no luck in finding a matching level world.
		return null;
	}

	/**
	 * Get the active level world.
	 * @return active
	 */
	public LevelWorld getActive() { return active; }

	/**
	 * Set the active level world.
	 * Adds the level world to this list if it doesn't already exist in it.
	 * @param active
	 */
	public void setActive(LevelWorld active) { 
		// The active level world always needs to be in the list.
		if(!levelWorlds.contains(active)) {
			levelWorlds.add(active);
		}
		this.active = active; 
	}
}
