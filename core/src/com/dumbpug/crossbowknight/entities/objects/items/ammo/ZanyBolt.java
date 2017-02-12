package com.dumbpug.crossbowknight.entities.objects.items.ammo;

import java.util.Random;

import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;
import com.dumbpug.crossbowknight.level.Level;

/**
 * Represents a Basic Bolt ammo item.
 * Created by nik on 19/10/16.
 */
public class ZanyBolt extends Ammo {

    /**
     * Create a new instance of the BasicBolt class.
     */
    public ZanyBolt() { }

    /**
     * Get the ammo projectile type.
     * @return projectile type.
     */
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_ZANY; }
    
    /**
     * Get a projectile instance.
     * @return A new projectile instance.
     */
    public Bolt getProjectile(float x, float y, float angle, float velocity, Level level)
    {
    	ProjectileType type = ProjectileType.values()[new Random().nextInt(ProjectileType.values().length)];
    	
    	switch(type)
    	{
			case BOLT_ANTIQUE:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.AntiqueBolt(x,y,angle, velocity);
			case BOLT_BARBED:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.BarbedBolt(x,y,angle, velocity);
			case BOLT_BASIC:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.BasicBolt(x,y,angle, velocity);
			case BOLT_EXPLOSIVE:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.ExplosiveBolt(x,y,angle, velocity, level);
			case BOLT_HEAVY:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.HeavyBolt(x,y,angle, velocity);
			case BOLT_IGNITED:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.IgnitedBolt(x,y,angle, velocity, level);
			case BOLT_RUBBER:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.RubberBolt(x,y,angle, velocity);
			case BOLT_SLEEK:
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.SleekBolt(x,y,angle, velocity);
			case BOLT_ZANY:
			default:
				// Dunno what to do here :S:S:S
				return new com.dumbpug.crossbowknight.entities.objects.projectiles.BasicBolt(x,y,angle, velocity);
    	}
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
    public ItemType getType() { return ItemType.BOLT_ZANY; }

    /**
	 * Get the name of the item. 
	 * @return name
	 */
	public String getName() { return "Zany Bolt"; }

	/**
	 * Get the description of the item. 
	 * @return description
	 */
	public String getDescription() { return "Wow what a wonderful wacky time we're having. Holds up spork"; }
}
