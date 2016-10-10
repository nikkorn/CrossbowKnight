package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * The physics box which represents a bolt in our physics world.
 * @author nikolas.howard
 */
public class BoltPhysicsBox extends NBPBox {
	/** The bolt. */
	private Bolt bolt;
	
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

	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {
		// Could have hit an enemy/player/other projectile.
	}

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {
		// Has hit a block. Must stick there.
		
		// We we may no longer want this physics box as b 
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onAfterUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDeletion() {
		// TODO Auto-generated method stub
		
	}

}
