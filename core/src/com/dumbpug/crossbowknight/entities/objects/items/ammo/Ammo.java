package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;
import com.dumbpug.crossbowknight.level.Level;

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
    
    /**
     * Get a projectile instance.
     * @return A new projectile instance.
     */
    public abstract Bolt getProjectile(float x, float y, float angle, float velocity, Level level);
    
    /**
	 * Get the category of the item.
	 * @return category.
	 */
	public ItemCategory getCategory() { return ItemCategory.AMMO; }
}
