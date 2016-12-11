package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.entities.PhysicsEntity;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * The physics box which represents a bolt in our physics world.
 * @author nikolas.howard
 */
public class BoltPhysicsBox extends PhysicsEntity {
	/** The bolt. */
	private Bolt bolt;
	/** The last angle of velocity of this physics entity 
	 *  before the most recent update of the physics world. */
	private float lastAngleOfVelocity = 0;
	
	/**
	 * Create a new instance of the BoltPhysicsBox class.
	 * @param bolt
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BoltPhysicsBox(Bolt bolt, float x, float y, float width, float height) {
		super(x, y, width, height, NBPBoxType.KINETIC);
		this.bolt = bolt;
	}
	
	/**
	 * Get the last angle of velocity for this object, taken
	 * before the physics world update.
	 * @return lastAngleOfVelocity
	 */
	public float getLastAngleOfVelocity() {
		return lastAngleOfVelocity;
	}

	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {
		// Could have hit an enemy/player/other projectile.
		bolt.onKinematicObjectHit(collidingBox);
	}

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {
		// Has hit a block!
		bolt.onStaticObjectHit(collidingBox);
	}

	@Override
	protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
	}

	@Override
	protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {
	}

	@Override
	protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
		bolt.onPush(bloom, angleOfForce, force);
		return true;
	}

	@Override
	protected void onBeforeUpdate() {
		// Grab the angle of velocity of this bolts physics box.
		// We may need this to know its orientation before a collision.
		this.lastAngleOfVelocity = (float) -(Math.atan2(getVelx(), getVely())/(Math.PI/180));
	}

	@Override
	protected void onAfterUpdate() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onDeletion() {
		// TODO Auto-generated method stub
	}

	@Override
	public PhysicsEntityType getPhysicsEntityType() {
		return PhysicsEntityType.PROJECTILE;
	}
}
