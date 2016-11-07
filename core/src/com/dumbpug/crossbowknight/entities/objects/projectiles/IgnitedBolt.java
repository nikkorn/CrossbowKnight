package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * An Ignited bolt.
 * Created by nik on 10/10/16.
 */
public class IgnitedBolt extends Bolt {

	/**
     * Create a new instance of the IgnitedBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public IgnitedBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
	}

	@Override
	public float getBaseDamage() { return 30f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_IGNITED; }
}
