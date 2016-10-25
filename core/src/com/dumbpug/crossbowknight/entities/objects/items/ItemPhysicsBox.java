package com.dumbpug.crossbowknight.entities.objects.items;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.player.PlayerPhysicsBox;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * The physics box which represents an item in the game world.
 * @author nikolas.howard
 */
public class ItemPhysicsBox extends NBPBox {
	/** The sensor that represents the pickup area for this item. */
	private NBPSensor pickupAreaSensor;
	/** The item that this box represents. */
	private Item item;
	/** Defines whether this item is still active. */
	private boolean isActive = true;
	
	/**
	 * Create a new instance of the ItemPhysicsBox class.
	 * @param x
	 * @param y
	 */
	public ItemPhysicsBox(Item item, float x, float y) {
		super(x, y, C.ITEM_BOX_SIZE / 4, C.ITEM_BOX_SIZE / 4, NBPBoxType.KINETIC);
		this.setRestitution(C.ITEM_BOX_RESTITUTION);
		this.setFriction(C.ITEM_BOX_FRICTION);
		this.item = item;
		// Create the sensor which is the actual pickup area for the item.
		// We cannot have the entire physics box being the pickup area as 
		// the chance that this item box will be placed over a static block
		// is way too high.
		createPickupSensor();
	}

	/**
	 * Create a pickup sensor for this item.
	 */
	private void createPickupSensor() {
        float sensorPosX   = this.getX() - ((C.ITEM_BOX_SIZE/2) - (this.getWidth()/2));
        // Create the sensor.
        pickupAreaSensor = new NBPSensor(sensorPosX, this.getY(), C.ITEM_BOX_SIZE, C.ITEM_BOX_SIZE);
        pickupAreaSensor.setName("item_pickup_sensor");
        // Attach the sensor to this physics box.
        this.attachSensor(pickupAreaSensor);
	}
	
	/**
	 * Get whether this items physics box is active.
	 * It is made inactive when it is collected.
	 * @return isActive
	 */
	public boolean isActive() { return this.isActive; }
	
	/**
	 * Get the item associated with this physics box.
	 * @return item
	 */
	public Item getItem() { return this.item; }
	
	/**
	 * Get the sensor that represents the pickup area for this item.
	 * @return pickupAreaSensor
	 */
	public NBPSensor getPickupSensor() { return this.pickupAreaSensor; }

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {
		// Make some kind of bouncing sound. But only do this is
		// if this items velocity is above some minimum value.
		// This is to stop this sound being played for every frame
		// that it is resting on a static box.
		if(this.getVelx() > C.ITEM_BOX_BOUNCE_SOUND_VELOCITY && this.getVely() > C.ITEM_BOX_BOUNCE_SOUND_VELOCITY) {
			// Play the bounce sound
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_HIGH).play();
		}
	}

	@Override
	protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
		// Check that the sensor is our pickup area sensor.
		if(sensor == this.pickupAreaSensor) {
			// Is the box that entered the pickup sensor area the players physics box?
			// If so, then the player has picked up this item.
			if(enteredBox instanceof PlayerPhysicsBox) {
				// Let the player physics box know that it has picked up this item.
				((PlayerPhysicsBox) enteredBox).onItemPhysicsBoxInteraction(this);
				// We no longer need this item in the world.
				// Set this item box as inactive so that the associated item knows whether it is needed.
				this.isActive = false;
				// This box should also be removed from the physics world.
				this.markForDeletion();
			}
		}
	}

	@Override
	protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
		// Items should not be affected by blooms.
		return false;
	}
	
	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}
	
	@Override
	protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {}

	@Override
	protected void onBeforeUpdate() {}

	@Override
	protected void onAfterUpdate() {}

	@Override
	protected void onDeletion() {}
}
