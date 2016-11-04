package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * The barbed bolt.
 * Created by nik on 10/10/16.
 */
public class BarbedBolt extends Bolt {
	
    /**
     * Create a new instance of the BarbedBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
    public BarbedBolt(float x, float y, float angle, float velocity) {
        super(x, y, angle, velocity);
    }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_BARBED; }

    @Override
    public float getBaseDamage() { return 15f; }
}
