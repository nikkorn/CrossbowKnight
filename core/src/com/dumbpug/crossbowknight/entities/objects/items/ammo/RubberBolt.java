package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Represents a Rubber Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class RubberBolt extends Ammo {

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_RUBBER; }

    /**
     * Is this item displayed in the inventory menu?
     * @return isDisplayedInInventory
     */
    public boolean isDisplayedInInventory() { return true; }

    /**
     * Get the type of the item.
     * @return type.
     */
    public ItemType getType() { return ItemType.BOLT_RUBBER; }

    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Rubber Bolt"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return "A strange bolt with rubber edges. It can bounce off of static objects."; }
	
	@Override
	public ItemRarity getRarity() { return ItemRarity.RARE; }
}
