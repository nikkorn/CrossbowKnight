package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * The explosive bolt.
 * Created by nik on 10/10/16.
 */
public class ExplosiveBolt extends Bolt {
	
    /**
     * Create a new instance of the ExplosiveBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
    public ExplosiveBolt(float x, float y, float angle, float velocity) {
        super(x, y, angle, velocity);
    }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_EXPLOSIVE; }

    @Override
    public float getBaseDamage() { return 40f; }
}
