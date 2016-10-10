package com.dumbpug.crossbowknight.entities;

import com.dumbpug.nbp.NBPBox;
import com.dumbpug.nbp.NBPBoxType;

/**
 * Defines a physics entity.
 * Created by nik on 10/10/16.
 */
public abstract class PhysicsEntity extends NBPBox {

    /**
     * Types of Physics Entity.
     */
    public enum PhysicsEntityType {
        PROJECTILE
    }

    /**
     * Create a new instance of the NBPBox class.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param type
     */
    public PhysicsEntity(float x, float y, float width, float height, NBPBoxType type) {
        super(x, y, width, height, type);
    }

    /**
     * Get the type of this physics entity.
     * @return physics entity type.
     */
    public abstract PhysicsEntityType getPhysicsEntityType();
}
