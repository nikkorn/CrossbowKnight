package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

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
