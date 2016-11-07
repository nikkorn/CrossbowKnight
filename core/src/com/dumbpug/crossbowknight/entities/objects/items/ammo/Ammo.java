package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Represents projectile ammo as an item.
 * Created by nik on 19/10/16.
 */
public abstract class Ammo extends Item {

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public abstract ProjectileType getProjectileType();
}
