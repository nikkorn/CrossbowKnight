package com.dumbpug.crossbowknight.tiles;

/**
 * Represents actions which can be carried out when interacting with a tile. 
 * @author nikolas.howard
 */
public interface ITileInteractionResolver {
	
	/**
	 * Cycle left.
	 * @return whether this action was resolved.
	 */
	public boolean cycleLeft();
	
	/**
	 * Cycle right.
	 * @return whether this action was resolved.
	 */
	public boolean cycleRight();
	
	/**
	 * select.
	 * @return whether this action was resolved.
	 */
	public boolean select();
}
