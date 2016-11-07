package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Represents an Antique Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class AntiqueBolt extends Ammo {

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_ANTIQUE; }

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return true; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.BOLT_ANTIQUE; }

    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Antique Bolt"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return "A very old and valuable bolt. The person who made this must have been very skilled."; }
}
