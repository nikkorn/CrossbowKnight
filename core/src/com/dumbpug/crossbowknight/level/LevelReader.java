package com.dumbpug.crossbowknight.level;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.Helpers;
import com.dumbpug.crossbowknight.leveleditor.Connector;
import com.dumbpug.crossbowknight.leveleditor.ConnectorType;
import com.dumbpug.crossbowknight.leveleditor.Marker;
import com.dumbpug.crossbowknight.resources.TileResources;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.door.Door;
import com.dumbpug.crossbowknight.tiles.door.DoorTarget;
import com.dumbpug.crossbowknight.tiles.door.DoorType;

/**
 * Reader for level resources.
 * @author nikolas.howard
 */
public class LevelReader {

	// TODO Read special tiles
	
	// TODO Read enemy tiles
	
	/**
	 * Read level tiles from disk.
	 * @param levelName
	 * @return level tiles.
	 */
	public static ArrayList<Tile> readLevelTilesFromDisk(String levelName, String path) {
		// Create a map of tiles where a x/y position maps to a tile.
		HashMap<String, Tile> tileMap = new HashMap<String, Tile>();
		// Ensure that we have the resources necessary to create our tiles.
		File tileBackgroundsFile = new File(path + levelName + "/background");
		File tileBlocksFile      = new File(path + levelName + "/blocks");
		File tileDecorationsFile = new File(path + levelName + "/decorations");
		File tileDoorsFile       = new File(path + levelName + "/doors");
		// Error if we have missing resources.
		if(!tileBackgroundsFile.exists() || !tileBlocksFile.exists() || !tileDecorationsFile.exists()) {
			throw new RuntimeException("Missing map resources in '" + path + "/" + levelName + "'");
		}
		// Convert our map resources to JSON.	
		JSONArray tileBackgroundsJSONArray = Helpers.readJSONArrayFromFile(tileBackgroundsFile);
		JSONArray tileDecorationsJSONArray = Helpers.readJSONArrayFromFile(tileDecorationsFile);
		JSONArray tileBlocksJSONArray      = Helpers.readJSONArrayFromFile(tileBlocksFile);
		
		// Read door tiles from disk if there are any.
		if(tileDoorsFile.exists()) {
			JSONArray tileDoorsJSONArray = Helpers.readJSONArrayFromFile(tileDoorsFile);
			
			// Create level door tiles.
			for(int tileDoorIndex = 0; tileDoorIndex < tileDoorsJSONArray.length(); tileDoorIndex++) {
				int xPos            = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getInt("x");
				int yPos            = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getInt("y");
				int typeId          = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getInt("typeId");
				String id           = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getString("id");
				String targetDoorId = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getString("targetDoorId");
				String targetLevel  = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getString("targetLevel");
				boolean locked      = tileDoorsJSONArray.getJSONObject(tileDoorIndex).getBoolean("locked");
				// Create the door.
				Door door = new Door(DoorType.values()[typeId], id);
				door.setX(xPos);
				door.setY(yPos);
				// Set the target of the door.
				DoorTarget target = new DoorTarget();
				target.doorId     = targetDoorId;
				target.level      = targetLevel;
				door.setTarget(target);
				// Unlock the door if it is not locked.
				if(!locked) {
					door.unlock();
				}
				// Add our newly created door tile to our map.
				tileMap.put(xPos + "-" + yPos, door);
			}
		}

		// Create tiles which have backgrounds or assign to existing tiles.
		for(int tileBackgroundIndex = 0; tileBackgroundIndex < tileBackgroundsJSONArray.length(); tileBackgroundIndex++) {
			int xPos   = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("x");
			int yPos   = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("y");
			int bgType = tileBackgroundsJSONArray.getJSONObject(tileBackgroundIndex).getInt("typeId");
			// Get our background texture.
			TileResources.BackgroundTile backgroundTile = TileResources.BackgroundTile.values()[bgType];
			IndexedTileTexture backgroundTexture = TileResources.getTileTextures().getBackgroundTileTexture(backgroundTile);
			// Does our map contain a tile at the same position as the current entry
			if(tileMap.containsKey(xPos + "-" + yPos)) {
				// Get our existing tile from the map.
				Tile tile = tileMap.get(xPos + "-" + yPos);
				// Set the background texture on our tile.
				tile.setBackgroundTexture(backgroundTexture);
			} else {
				Tile tile = new Tile();
				tile.setX(xPos);
				tile.setY(yPos);
				// Set the background texture on our tile.
				tile.setBackgroundTexture(backgroundTexture);
				// Add our newly created tile to our map.
				tileMap.put(xPos + "-" + yPos, tile);
			}
		}

		// Create tiles which have decorations or assign to existing tiles.
		for(int tileDecorationIndex = 0; tileDecorationIndex < tileDecorationsJSONArray.length(); tileDecorationIndex++) {
			int xPos   = tileDecorationsJSONArray.getJSONObject(tileDecorationIndex).getInt("x");
			int yPos   = tileDecorationsJSONArray.getJSONObject(tileDecorationIndex).getInt("y");
			int bgType = tileDecorationsJSONArray.getJSONObject(tileDecorationIndex).getInt("typeId");
			// Get our decoration texture.
			TileResources.DecorationTile decorationTile = TileResources.DecorationTile.values()[bgType];
			IndexedTileTexture decorationTexture = TileResources.getTileTextures().getDecorationTileTexture(decorationTile);
			// Does our map contain a tile at the same position as the current entry
			if(tileMap.containsKey(xPos + "-" + yPos)) {
				// Get our existing tile from the map.
				Tile tile = tileMap.get(xPos + "-" + yPos);
				// Set the decoration texture on our tile.
				tile.setDecorationTexture(decorationTexture);
			} else {
				Tile tile = new Tile();
				tile.setX(xPos);
				tile.setY(yPos);
				// Set the decoration texture on our tile.
				tile.setDecorationTexture(decorationTexture);
				// Add our newly created tile to our map.
				tileMap.put(xPos + "-" + yPos, tile);
			}
		}

		// Create tiles which have blocks or assign to existing tiles.
		for(int tileBlockIndex = 0; tileBlockIndex < tileBlocksJSONArray.length(); tileBlockIndex++) {
			int xPos         = tileBlocksJSONArray.getJSONObject(tileBlockIndex).getInt("x");
			int yPos         = tileBlocksJSONArray.getJSONObject(tileBlockIndex).getInt("y");
			int bgType       = tileBlocksJSONArray.getJSONObject(tileBlockIndex).getInt("typeId");
			int tileFillType = tileBlocksJSONArray.getJSONObject(tileBlockIndex).getInt("tileFillType");
			// Get the fill type of this block.
			Block.TileBlockFillType fillType = Block.TileBlockFillType.values()[tileFillType];
			// Create the new block.
			Block block = null;
			// The position of this block will depend on its position relative to the tile.
			switch(fillType) {
				case FULL:
					block = new Block(xPos * C.LAYOUT_TILE_SIZE, yPos * C.LAYOUT_TILE_SIZE,
							C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE, fillType);
					break;
				case TOP_HALF:
					block = new Block(xPos * C.LAYOUT_TILE_SIZE, (yPos * C.LAYOUT_TILE_SIZE) + (C.LAYOUT_TILE_SIZE / 2),
							C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE / 2, fillType);
					break;
				case BOTTOM_HALF:
					block = new Block(xPos * C.LAYOUT_TILE_SIZE, yPos * C.LAYOUT_TILE_SIZE,
							C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE / 2, fillType);
					break;
				case LEFT_HALF:
					block = new Block(xPos * C.LAYOUT_TILE_SIZE, yPos * C.LAYOUT_TILE_SIZE,
							C.LAYOUT_TILE_SIZE / 2, C.LAYOUT_TILE_SIZE, fillType);
					break;
				case RIGHT_HALF:
					block = new Block((xPos * C.LAYOUT_TILE_SIZE) + (C.LAYOUT_TILE_SIZE / 2), yPos * C.LAYOUT_TILE_SIZE,
							C.LAYOUT_TILE_SIZE / 2, C.LAYOUT_TILE_SIZE, fillType);
					break;
			default:
				break;
			}
			// Set the texture for the block.
			TileResources.BlockTile blockTile = TileResources.BlockTile.values()[bgType];
			IndexedTileTexture blockTexture = TileResources.getTileTextures().getBlockTileTexture(blockTile);
			block.setBlockTexture(blockTexture);
			// Does our map contain a tile at the same position as the current entry
			if(tileMap.containsKey(xPos + "-" + yPos)) {
				// Get our existing tile from the map.
				Tile tile = tileMap.get(xPos + "-" + yPos);
				// Set the physics block for this tile.
				tile.setPhysicsBlock(block);
			} else {
				Tile tile = new Tile();
				tile.setX(xPos);
				tile.setY(yPos);
				// Set the physics block for this tile.
				tile.setPhysicsBlock(block);
				// Add our newly created tile to our map.
				tileMap.put(xPos + "-" + yPos, tile);
			}
		}

		// Return the tiles in our map as a list.
		return new ArrayList<Tile>(tileMap.values());
	}
	
	/**
	 * Read level segment connectors from disk.
	 * @param levelName
	 * @return level segment connectors.
	 */
	public static ArrayList<Connector> readLevelConnectorsFromDisk(String levelName, String path) {
		// Create a map of connectors.
		ArrayList<Connector> connectors = new ArrayList<Connector>();
		// Ensure that we have the resources necessary to create our tiles.
		File connectorsFile = new File(path + levelName + "/connectors");
		// Read connectors from disk if there are any.
		if(connectorsFile.exists()) {
			JSONArray connectorsJSONArray = Helpers.readJSONArrayFromFile(connectorsFile);
			// Create each connector from file.
			for(int connectorIndex = 0; connectorIndex < connectorsJSONArray.length(); connectorIndex++) {
				int xPos            = connectorsJSONArray.getJSONObject(connectorIndex).getInt("x");
				int yPos            = connectorsJSONArray.getJSONObject(connectorIndex).getInt("y");
				ConnectorType type  = ConnectorType.valueOf(connectorsJSONArray.getJSONObject(connectorIndex).getString("type"));
				int height          = connectorsJSONArray.getJSONObject(connectorIndex).getInt("height");
				// Create the connector and add it to the list of connectors.
				connectors.add(new Connector(type, xPos, yPos, height));
			}
		}
		return connectors;
	}
	
	/**
	 * Read level segment markers from disk.
	 * @param levelName
	 * @return level segment markers.
	 */
	public static ArrayList<Marker> readLevelMarkersFromDisk(String levelName, String path) {
		// Create a map of markers.
		ArrayList<Marker> markers = new ArrayList<Marker>();
		// Ensure that we have the resources necessary to create our tiles.
		File markersFile = new File(path + levelName + "/markers");
		// Read markers from disk if there are any.
		if(markersFile.exists()) {
			JSONArray markersJSONArray = Helpers.readJSONArrayFromFile(markersFile);
			// Create each marker from file.
			for(int markerIndex = 0; markerIndex < markersJSONArray.length(); markerIndex++) {
				int xPos               = markersJSONArray.getJSONObject(markerIndex).getInt("x");
				int yPos               = markersJSONArray.getJSONObject(markerIndex).getInt("y");
				Marker.MarkerType type = Marker.MarkerType.valueOf(markersJSONArray.getJSONObject(markerIndex).getString("type"));
				// Create the marker and add it to the list of markers.
				markers.add(new Marker(type, xPos, yPos));
			}
		}
		return markers;
	}
}
