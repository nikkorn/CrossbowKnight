package com.dumbpug.crossbowknight.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nik on 12/11/16.
 */
public interface IParticle {

    /**
     * Update the particle.
     */
    void update();

    /**
     * Is this particle an entity in a physics world.
     * @return isPhysicsParticle
     */
    boolean isPhysicsParticle();

    /**
     * Draw the particle.
     * @param batch
     */
    void draw(SpriteBatch batch);
}
