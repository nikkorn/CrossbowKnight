package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * A antique bolt.
 * Created by nik on 10/10/16.
 */
public class AntiqueBolt extends Bolt {

	/**
     * Create a new instance of the AntiqueBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public AntiqueBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
	}

	@Override
	public float getBaseDamage() { return 50f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_ANTIQUE; }
}
