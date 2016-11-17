package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Represents a Sleek Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class SleekBolt extends Ammo {

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_SLEEK; }

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return true; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.BOLT_SLEEK; }

    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Sleek Bolt"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return "An impressively sharp bolt. It can often pass through multiple enemies."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.UNCOMMON; }
}
