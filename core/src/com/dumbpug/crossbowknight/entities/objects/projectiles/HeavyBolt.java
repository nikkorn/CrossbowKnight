package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * A very heavy bolt.
 * Created by nik on 10/10/16.
 */
public class HeavyBolt extends Bolt {

	/**
     * Create a new instance of the HeavyBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public HeavyBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
	}

	@Override
	public float getBaseDamage() { return 20f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_HEAVY; }
}
