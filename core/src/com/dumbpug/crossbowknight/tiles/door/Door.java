package com.dumbpug.crossbowknight.tiles.door;

import java.util.UUID;

/**
 * Represents a door.
 * @author nikolas.howard
 */
public class Door {
	/** The unique id of the door */
	private String id; 
	/** The type of this door. */
	private DoorType type;
	/** The target door of this door. */
	private DoorTarget target;
	/** Whether this door is unlocked. */
	private boolean isUnlocked = false;
	
	/**
	 * Create a new instance of the Door class.
	 * @param type
	 */
	public Door(DoorType type) {
		this.setType(type);
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
	}
	
	/**
	 * Create a new instance of the Door class.
	 * Where this door is the target of a different door.
	 * @param connectingDoor
	 * @param connectingLevel
	 */
	public Door(Door connectingDoor, String connectingLevel) {
		this.setType(type);
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
	 * @return type
	 */
	public DoorType getType() { return type; }

	/**
	 * Set the type of this door.
	 * @param type
	 */
	public void setType(DoorType type) { this.type = type; }
}
