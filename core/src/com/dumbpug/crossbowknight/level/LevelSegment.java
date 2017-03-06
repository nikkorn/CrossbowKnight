package com.dumbpug.crossbowknight.level;

import java.util.ArrayList;

import com.dumbpug.crossbowknight.leveleditor.Connector;
import com.dumbpug.crossbowknight.leveleditor.ConnectorType;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * Represents part of a level with entrance/exit connectors.
 * @author nikolas.howard
 */
public class LevelSegment {
	/** The segment connectors. */
	private ArrayList<Connector> connectors = new ArrayList<Connector>();
	/** The tiles in this segment. */
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	/**
	 * Get a tile at a specific x/y position.
	 * @param x
	 * @param y
	 * @return tile
	 */
	public Tile getTileAt(int x, int y) {
		for(Tile tile: this.tiles) {
			if(tile.getX() == x && tile.getY() == y) {
				return tile;
			}
		}
		// Didn't find the tile.
		return null;
	}
	
	/**
	 * Get the tiles in this segment.
	 * @return tiles.
	 */
	public ArrayList<Tile> getTiles() { return this.tiles; }
	
	/**
	 * Set the tiles in this segment.
	 * @param tiles
	 */
	public void setTiles(ArrayList<Tile> tiles) { this.tiles = tiles; }
	
	/**
	 * Returns true if this tile has nothing on it.
	 * @param targetTile
	 * @return is blank
	 */
	public boolean isTileBlank(Tile targetTile) {
		return targetTile.getBackgroundTexture() == null && 
				targetTile.getDecorationTexture() == null && 
				targetTile.getPhysicsBlock() == null;
	}
	
	/**
	 * Add a new level connector
	 * @param connecter
	 */
	public void addConnector(Connector connecter) { this.connectors.add(connecter); }
	
	/**
	 * Get the level connectors.
	 * @return connectors.
	 */
	public ArrayList<Connector> getConnectors() { return this.connectors; }
	
	/**
	 * Get the structural type of this level segment.
	 * @return
	 */
	public LevelSegmentType getLevelSegmentType() {
		// If we have no connectors then this segment is stand-alone.
		if(this.connectors.isEmpty()) {
			return LevelSegmentType.DISCONNECTED;
		}
		// Find out the number of entrances/exits for this segment.
		int entrances = 0;
		int exits     = 0;
		for(Connector connector : this.connectors) {
			if(connector.getConnecterType() == ConnectorType.ENTRANCE) {
				entrances++;
			} else {
				exits++;
			}
		}
		// Does this segment have any entrances, if not then its a left edge (level start).
		if(entrances == 0) {
			return LevelSegmentType.LEFT_EDGE;
		} else if(exits == 0) {
			return LevelSegmentType.RIGHT_EDGE;
		} else {
			// If we have more than one exit then this is a fork, otherwise it is a corridor.
			if(exits > 1) {
				return LevelSegmentType.FORK;
			} else {
				return LevelSegmentType.CORRIDOR;
			}
		}
	}
}
