package com.dumbpug.crossbowknight.entities.characters;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.forces.Force;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * Physics box for a character.
 * @author nikolas.howard
 */
public class CharacterPhysicsBox<TCharacter extends Character> extends NBPBox {
	/** Reference to our Character object. */
	protected TCharacter character;
	/** Can the character jump? */
    private boolean canJump = false;

    /**
     * Creates a new instance of the CharacterPhysicsBox class.
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
     * @param friction
     * @param restitution
     * @param maxVelocity
     */
	public CharacterPhysicsBox(TCharacter character, float x, float y, float width, float height) {
		super(x, y, width, height, NBPBoxType.KINETIC);
		setFriction(C.CHARACTER_PHYSICS_FRICTION);
        setRestitution(C.CHARACTER_PHYSICS_RESTITUTION);
        // Set max velocity for this character.
        setMaxVelocityX(C.CHARACTER_PHYSICS_MAX_VELOCITY);
        setMaxVelocityY(C.CHARACTER_PHYSICS_MAX_VELOCITY * 1.5f);
        // Create a sensor and place it at the base of our character. This sensor will
        // be used to detect when we are standing on something static, thus allowing
        // the character to jump.
        float sensorHeight = 1;
        float sensorWidth  = width;
        float sensorPosX   = x;
        float sensorPosY   = y - sensorHeight;
        // Create the sensor.
        NBPSensor baseSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        baseSensor.setName("base_sensor");
        // Attach the sensor to the character box.
        attachSensor(baseSensor);
        // Grab reference to our character.
        this.character = character;
	}
	
	/**
     * Move the character to the left.
     */
    public void moveLeft() {
    	// Calculate how to apply an impulse to this character so that its moving speed is defined 
    	// by a value lower that its max velocity. In this case, a walking speed.
    	if(this.getVelx() > -C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY) {
    		if((-C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx()) > C.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE) {
    			applyImpulse(-C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx(), 0f);
    		} else {
    			applyImpulse(-C.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE, 0f);
    		}
    	} 
    }

    /**
     * Move the character to the right.
     */
    public void moveRight() {
    	// Calculate how to apply an impulse to this character so that its moving speed is defined 
    	// by a value lower that its max velocity. In this case, a walking speed.
    	if(this.getVelx() < C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY) {
    		if((C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx()) < C.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE) {
    			applyImpulse(C.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx(), 0f);
    		} else {
    			applyImpulse(C.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE, 0f);
    		}
    	} 
    }

    /**
     * Make the character jump if he can.
     * @return true if character was able to jump
     */
    public boolean jump() {
        // Can we jump? (Are we on a static block?)
        // We should also not allow a jump to happen if our Y velocity
        // is above 0, this is due to the fact that you cannot jump
        // if you are already ascending. This fixes the issue where
        // sometimes the characters base sensor will intersect with a
        // static box while ascending upwards against it.
        if(canJump && !(this.getVely() > 0)) {
            // Apply a vertical impulse.
            applyImpulse(0f, C.CHARACTER_JUMPING_IMPULSE);
            // Character was able to jump.
            return true;
        }
        // Player was not able to jump.
        return false;
    }

    /**
     * Get whether the character is touching the floor (if false then the character is airborne).
     * @return is touching floor.
     */
    public boolean isTouchingFloor() {
        // If the player can jump then he is touching the floor.
        return canJump;
    }
    
    /**
     * Return the character which this physics box represents.
     * @return character
     */
    public TCharacter getCharacter() { return this.character; }
    
    @Override
    public void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
        // Check that this sensor is the one we have placed at the bottom of the box.
        if(sensor.getName().equals("base_sensor")) {
            // If we are on any static block then we can jump off of it.
            if(enteredBox.getType() == NBPBoxType.STATIC) {
                // If the player was previously airborne, then this is a landing.
                if(!isTouchingFloor()) {
                    // Let our player instance know that we have landed.
                    character.onLanding();
                }
                // Set a flag to show that the player can now jump.
                this.canJump = true;
            }
        }
    }

    @Override
    public void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {
        // We have lifted off of a box, if this is a static box then check whether the sensor is
        // now not intersecting with any static boxes. if not then the player can no longer jump.
        // Firstly, make sure that this is the sensor that we placed at the base of the player.
        if(sensor.getName().equals("base_sensor")) {
            // Check that the sensor left a static box.
            if(exitedBox.getType() == NBPBoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isRestingOnStaticBox = false;
                for(NBPBox box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == NBPBoxType.STATIC) {
                        // We are still standing on a static box so we can still jump.
                        isRestingOnStaticBox = true;
                        break;
                    }
                }
                // Set the flag to show whether we can still jump.
                this.canJump = isRestingOnStaticBox;
            }
        }
    }

	@Override
	protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
		// All blooms in our physics world SHOULD be of type Force.
		character.onPushedByForce((Force) bloom, angleOfForce, force, distance);
		// Return true as we want the force to affect this character box.
		return true;
	}

	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {}
	
	@Override
	protected void onBeforeUpdate() {}

	@Override
	protected void onAfterUpdate() {}

	@Override
	protected void onDeletion() {}
}
