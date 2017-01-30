package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.entities.characters.Character;
import com.dumbpug.nbp.NBPBox;

/**
 * Base class for projectiles.
 * Created by nik on 10/10/16.
 */
public abstract class Projectile {
    /** The owner of this projectile */
    private Character owningCharacter = null;
    /** The modified damage of this projectile. Calculated based on
     * the stats of the character which fired it and also based
     * on the base damage of the projectile. */
    private float modifiedDamage = 0;
    /** Is this projectile active? */
    private boolean isActive = true;
    /** The time at which this projectile was fired. */
    private long projectileLaunchTime = 0;

    /**
     * Get the type of this projectile.
     * @return projectile type.
     */
    public abstract ProjectileType getProjectileType();

    /**
     * Is this projectile affected by gravity?
     * @return is affected by gravity.
     */
    protected boolean isAffectedByGravity() { return true; }

    /**
     * Get the modified damage.
     * @return modified damage.
     */
    public float getModifiedDamage() { return modifiedDamage; }

    /**
     * Set the modified damage.
     * @param modifiedDamage
     */
    public void setModifiedDamage(float modifiedDamage) { this.modifiedDamage = modifiedDamage; }

    /**
     * Get the character who owns this projectile.
     * @return owner
     */
    public Character getOwner() { return owningCharacter; }

    /**
     * Set the owner of this projectile.
     * @param owningCharacter
     */
    public void setOwner(Character owningCharacter) { this.owningCharacter = owningCharacter; }

    /**
     * Is this projectile active?
     * @return is active.
     */
    public boolean isActive() { return isActive; }
    
    /**
     * Mark this projectile as launched.
     * This will mark the start of this projectiles launch life.
     */
    public void markAsLaunched() { this.projectileLaunchTime = System.currentTimeMillis(); }
    
    /**
     * Get the launch life of this projectile. As in the millis it has been flying for.
     * If the projectile has never been launched, 0 will be returned.
     * @return launch life
     */
    public long getLaunchLife() { return (this.projectileLaunchTime == 0) ? 0 : System.currentTimeMillis() - this.projectileLaunchTime; }

    /**
     * Set whether this projectile is active.
     * @param active
     */
    public void setActive(boolean active) { isActive = active; }

    /**
     * Get this projectiles physics box.
     */
    public abstract NBPBox getPhysicsBox();

    /**
     * Draw this projectile.
     * @param batch
     * @param levelCameraXOffset
     * @param levelCameraYOffset
     */
    public abstract void draw(SpriteBatch batch, float levelCameraXOffset, float levelCameraYOffset);
}
