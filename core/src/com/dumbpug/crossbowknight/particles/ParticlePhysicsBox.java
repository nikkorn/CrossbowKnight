package com.dumbpug.crossbowknight.particles;

import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;
import com.dumbpug.nbp.NBPIntersectionPoint;
import com.dumbpug.nbp.NBPSensor;

/**
 * A physics box which represents a particle.
 */
public class ParticlePhysicsBox extends NBPBox {

	/**
	 * Create a new instance of the ParticlePhysicsBox class.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
     * @param type
     */
	public ParticlePhysicsBox(float x, float y, float width, float height, NBPBoxType type) {
		super(x, y, width, height, type);
	}

	@Override
	protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

	@Override
	protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {}

	@Override
	protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {}

	@Override
	protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {}

	@Override
	protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) { return false; }

	@Override
	protected void onBeforeUpdate() {}

	@Override
	protected void onAfterUpdate() {}

	@Override
	protected void onDeletion() {}
}
