package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.nbp.NBPBox;

/**
 * The explosive bolt which explodes on contact.
 * Created by nik on 10/10/16.
 */
public class ExplosiveBolt extends Bolt {
    /** The explosion emitter. */
    private Emitter explosionEmitter;

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

    /**
     * Called when BoltPhysicsBox hits a static object.
     * @param collidingBox
     */
    @Override
    public void onStaticObjectHit(NBPBox collidingBox) {
        // TODO Create an explosion emitter/particle generator.
    }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_EXPLOSIVE; }

    @Override
    public float getBaseDamage() { return 40f; }
}
