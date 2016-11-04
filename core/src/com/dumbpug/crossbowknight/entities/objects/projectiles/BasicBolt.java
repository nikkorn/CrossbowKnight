package com.dumbpug.crossbowknight.entities.objects.projectiles;

/**
 * The most basic type of bolt in the game.
 * Created by nik on 10/10/16.
 */
public class BasicBolt extends Bolt {
	
    /**
     * Create a new instance of the BasicBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
    public BasicBolt(float x, float y, float angle, float velocity) {
        super(x, y, angle, velocity);
    }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_BASIC; }

    @Override
    public float getBaseDamage() { return 12f; }
}
