package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * A bolt which bounces off of static objects.
 * Created by nik on 10/10/16.
 */
public class RubberBolt extends Bolt {

	/**
     * Create a new instance of the RubberBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public RubberBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
	}

	@Override
	public float getBaseDamage() { return 16f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_RUBBER; }
}
