package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * A bolt which passes through enemies.
 * Created by nik on 10/10/16.
 */
public class SleekBolt extends Bolt {

	/**
     * Create a new instance of the SleekBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public SleekBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
	}

	@Override
	public float getBaseDamage() { return 25f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_SLEEK; }
}
