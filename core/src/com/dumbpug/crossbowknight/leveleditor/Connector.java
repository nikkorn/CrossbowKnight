package com.dumbpug.crossbowknight.leveleditor;

/**
 * Represents a connection between two level segments.
 * @author nikolas.howard
 */
public class Connector {
	/** The type of connecter. */
	private ConnectorType type;
	/** The tile position of this connecter. */
	private int x, y;
	/** The tile height of this connecter. */
	private int height;
	
	/**
	 * Create a new instance of the Connecter class.
	 * @param type
	 * @param x
	 * @param y
	 * @param height
	 */
	public Connector(ConnectorType type, int x, int y, int height) {
		this.type   = type;
		this.x      = x;
		this.y      = y;
		this.height = height;
	}
	
	/**
	 * Get the type of this connecter.
	 * @return type.
	 */
	public ConnectorType getConnecterType() { return this.type; }
	
	/**
	 * Get the x tile position of this connecter.
	 * @return x position.
	 */
	public int getTilePositionX() { return this.x; }
	
	/**
	 * Get the y tile position of this connecter.
	 * @return y position.
	 */
	public int getTilePositionY() { return this.y; }
	
	/**
	 * Get the tile height of this connecter.
	 * @return tile height.
	 */
	public int getTileHeight() { return this.height; }
}
