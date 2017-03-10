package com.dumbpug.crossbowknight.leveleditor;

/** 
 * Represents a tile-positioned level entity suck as a door, chest, enemy etc.
 * @author nikolas.howard
 */
public class Marker {
	/** The type of marker. */
	private MarkerType type;
	/** The tile position of this marker. */
	private int x, y;
	
	/**
	 * The possible types of marker.
	 * @author nikolas.howard
	 *
	 */
	public enum MarkerType {
		NORMAL,
		ENEMY
	}
	
	/**
	 * Create a new instance of the Marker class. 
	 * @param type
	 * @param x
	 * @param y
	 */
	public Marker(MarkerType type, int x, int y) {
		this.type = type;
		this.x    = x;
		this.y    = y;
	}
	
	/**
	 * Get the type of this marker.
	 * @return type.
	 */
	public MarkerType getMarkerType() { return this.type; }
	
	/**
	 * Get the x tile position of this marker.
	 * @return x position.
	 */
	public int getTilePositionX() { return this.x; }
	
	/**
	 * Get the y tile position of this marker.
	 * @return y position.
	 */
	public int getTilePositionY() { return this.y; }
}
