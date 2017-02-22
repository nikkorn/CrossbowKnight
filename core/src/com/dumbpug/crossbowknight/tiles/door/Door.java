package com.dumbpug.crossbowknight.tiles.door;

import java.util.UUID;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.TileResources;
import com.dumbpug.crossbowknight.resources.TileResources.EntityTile;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.TileType;

/**
 * Represents a door tile.
 * @author nikolas.howard
 */
public class Door extends Tile {
	/** The unique id of the door */
	private String id; 
	/** The type of this door. */
	private DoorType type;
	/** The target door of this door. */
	private DoorTarget target;
	/** Whether this door is unlocked. */
	private boolean isUnlocked = false;
	/** The tile textures for this door. */
	private IndexedTileTexture lockedTexture;
	private IndexedTileTexture unlockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_OPEN);
	
	/**
	 * Create a new instance of the Door class.
	 * @param type
	 */
	public Door(DoorType type) {
		this.setDoorType(type);
		// Every door needs an ID.
		this.id = UUID.randomUUID().toString();
	}
	
	/**
	 * Create a new instance of the Door class.
	 * @param type
	 * @param id
	 */
	public Door(DoorType type, String id) {
		this(type);
		this.id = id;
		// Set the door tile texture.
		switch(type) {
			case SINGLE:
				lockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_1);
				break;
			case DOUBLE:
				lockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_2);
				break;
			case TRIPLE:
				lockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_3);
				break;
			case HOME:
				lockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_HOME);
				break;
			case SHOP:
				lockedTexture = TileResources.getTileTextures().getEntityTileTexture(EntityTile.DOOR_SHOP);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Create a new instance of the Door class.
	 * Where this door is the target of a different door.
	 * @param connectingDoor
	 * @param connectingLevel
	 */
	public Door(Door connectingDoor, String connectingLevel) {
		this.setDoorType(type);
		// Target the connecting door.
		DoorTarget target = new DoorTarget();
		target.level      = connectingLevel;
		target.doorId     = connectingDoor.id;
		this.setTarget(target);
	}
	
	/**
	 * Get the number of keys required to open this door.
	 * @return number of keys required.
	 */
	public int getKeysRequiredToOpen() {
		switch(type) {
			case SINGLE:
				return 1;
			case DOUBLE:
				return 2;
			case TRIPLE:
				return 3;
			default:
				// Any other door should not be locked.
				return 0;
		}
	}
	
	/**
	 * Get whether this door is locked.
	 * @return is locked
	 */
	public boolean isLocked() { return getKeysRequiredToOpen() == 0 || isUnlocked; }
	
	/**
	 * Unlock the door.
	 */
	public void unlock() { isUnlocked = true; }
	
	/**
	 * Get the id of this door.
	 * @return id
	 */
	public String getId() { return id; }
	
	/**
	 * Set the is of this door.
	 * @param id
	 */
	public void setId(String id) { this.id = id; }

	/**
	 * Get this doors target door.
	 * @return target door
	 */
	public DoorTarget getTarget() { return target; }

	/**
	 * Set this doors target door.
	 * @param target door
	 */
	public void setTarget(DoorTarget target) { this.target = target; }

	/**
	 * Get the type of this door.
	 * @return door type
	 */
	public DoorType getDoorType() { return type; }
	
	/**
	 * Set the type of this door.
	 * @param door type
	 */
	public void setDoorType(DoorType type) { this.type = type; }
	
	/**
	 * Set the type of this tile.
	 * @param type
	 */
	public TileType getType() { return TileType.DOOR; }
	
	/**
	 * Draw the top layer of this tile.
	 * This is only supposed to be implemented by specific tile types (e.g doors).
	 * @param batch
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawTopLayer(SpriteBatch batch, float xOffset, float yOffset) {
		// Draw the door.
		batch.draw((this.isUnlocked ? unlockedTexture : lockedTexture).getTexture(),
				(x * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + xOffset,
				(y * (C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER)) + yOffset,
				(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER),
				(C.LAYOUT_TILE_SIZE * C.LAYOUT_MULTIPLIER));
	}
}
