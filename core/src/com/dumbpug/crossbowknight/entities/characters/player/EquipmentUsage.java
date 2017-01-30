package com.dumbpug.crossbowknight.entities.characters.player;

import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.Ammo;
import com.dumbpug.crossbowknight.entities.objects.projectiles.AntiqueBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.BarbedBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.BasicBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ExplosiveBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.HeavyBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.IgnitedBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.RubberBolt;
import com.dumbpug.crossbowknight.entities.objects.projectiles.SleekBolt;
import com.dumbpug.crossbowknight.entities.characters.Character;
import com.dumbpug.crossbowknight.entities.characters.CharacterPhysicsBox;
import com.dumbpug.crossbowknight.level.Level;

/**
 * Handles the usage of equipment.
 * @author nikolas.howard
 */
public class EquipmentUsage {
	/** The level. */
	private Level level;
	
	/**
	 * Create a new instance of the EqupimentUsage class.
	 * @param level
	 */
	public EquipmentUsage(Level level) {
		this.level = level;
	}
	
	/**
	 * Called when an item is actually able to be used.
	 * @param character
	 * @param item
	 * @returns item use success
	 */
	public boolean onItemUse(Character character, Item item) {
		// Use the item for real now.
		switch(item.getType()) {
			case HEALTH_POTION:
				break;
			case HEALTH_POTION_LARGE:
				break;
			case HEALTH_POTION_SUPER:
				break;
			case KEY:
				break;
			default:
				break;
		}
		// TODO Play an item usage sound!
		return true;
	}
	
	/**
	 * Called when ammo is actually able to be used.
	 * @param character
	 * @param item
	 * @returns ammo use success
	 */
	public boolean onAmmoUse(Character character, Ammo ammo) {
		// Get the characters physics box.
		CharacterPhysicsBox<? extends Character> characterPhysicsBox = character.getPhysicsBox();
		// Get the angle at which we will be firing the projectile.
		float angleOfFire = (float) -Math.toRadians(character.getAngleOfFocus());
		// Get the velocity of this shot.
		float shotVelocity = 3; // TODO alter this to take character stats amongst other things into account.
		
		// TODO Handle other projectile types.
		
		// Create the bolt.
		Bolt bolt = null;
		switch(ammo.getProjectileType()) {
			case BOLT_ANTIQUE:
				bolt = new AntiqueBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			case BOLT_BARBED:
				bolt = new BarbedBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			case BOLT_BASIC:
				bolt = new BasicBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			case BOLT_EXPLOSIVE:
				bolt = new ExplosiveBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity, level);
				break;
			case BOLT_HEAVY:
				bolt = new HeavyBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			case BOLT_IGNITED:
				bolt = new IgnitedBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity, level);
				break;
			case BOLT_RUBBER:
				bolt = new RubberBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			case BOLT_SLEEK:
				bolt = new SleekBolt(characterPhysicsBox.getX(), characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2), angleOfFire, shotVelocity);
				break;
			default:
				// Got a crazy unknown bolt type, this ammo usage attempt failed.
				return false;
		}
		// Set the owner of this bolt to be the character which fired it.
		bolt.setOwner(character);
		
		// TODO modify the bolts damage to reflect character stats.
		
		// Attempt to fire, will return true if success, false if the physics world rejects the projectiles physics box.
		boolean ammoUsageSuccess = level.getLevelWorld().getProjectilePool().add(bolt);
		// If the ammo was fired play a sound.
		if(ammoUsageSuccess) { 
			Audio.getSoundEffect(Audio.SoundEffect.JUMP).play(); 
		}
		// Return whether the ammo usage was a success.
		return ammoUsageSuccess;
	}
}
