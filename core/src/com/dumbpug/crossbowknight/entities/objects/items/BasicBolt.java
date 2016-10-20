package com.dumbpug.crossbowknight.entities.objects.items;

import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Represents a Basic Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class BasicBolt extends Ammo {

    /**
     * Create a new instance of the BasicBolt class.
     */
    public BasicBolt() {
        // Basic bolts are an infinite ammo.
        this.setQuantity(-1);
    }

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_BASIC; }

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return false; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.BOLT_BASIC; }
}
