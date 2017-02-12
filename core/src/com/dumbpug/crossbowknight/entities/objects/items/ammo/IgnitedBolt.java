package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;
import com.dumbpug.crossbowknight.level.Level;

/**
 * Represents an Ignited Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class IgnitedBolt extends Ammo {

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_IGNITED; }

    /**
     * Get a projectile instance.
     * @return A new projectile instance.
     */
    public Bolt getProjectile(float x, float y, float angle, float velocity, Level level)
    {
    	return new com.dumbpug.crossbowknight.entities.objects.projectiles.IgnitedBolt(x, y, angle, velocity, level);
    }

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return true; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.BOLT_IGNITED; }

    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Ignited Bolt"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return "A rather heavy bolt which ungulfs enemies in flames."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.UNCOMMON; }
}
