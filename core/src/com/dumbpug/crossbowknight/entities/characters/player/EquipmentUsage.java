package com.dumbpug.crossbowknight.entities.characters.player;

import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.dialog.Dialog;
import com.dumbpug.crossbowknight.dialog.DialogType;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.Ammo;
import com.dumbpug.crossbowknight.entities.objects.items.potions.IHealingItem;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
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
	public EquipmentUsage(Level level) { this.level = level; }
	
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
			case HEALTH_POTION_LARGE:
			case HEALTH_POTION_SUPER:
				// Using this item will replenish an amount of HP.
				int amountOfHP = ((IHealingItem)item).getHP();
				character.getHealthStatus().applyHealth(amountOfHP);
				// Add a character dialog for this action.
				character.getDialogList().add(new Dialog(DialogType.BUFF, "+" + amountOfHP + "HP"));
				break;
			case KEY:
				break;
			default:
				break;
		}
		// Play an item usage sound based on the item category.
		switch(item.getCategory()) {
			case AMMO:
				break;
			case CONSUMABLE:
				Audio.getSoundEffect(Audio.SoundEffect.LEVEL_UP).play(); // TODO Replace! 
				break;
			case DEFAULT:
				break;
			case HELMET:
				break;
			case LIMBS:
				break;
			case SHIELD:
				break;
			case SIGHT:
				break;
			case STOCK:
				break;
			case STRING:
				break;
			default:
				break;
		}
		// Item use was a success.
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
		
		float shotX = characterPhysicsBox.getX();
		float shotY = characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2);
		
		// TODO Handle other projectile types.
		
		// Create the bolt based on the ammo type.
		Bolt bolt = ammo.getProjectile(shotX, shotY, angleOfFire, shotVelocity, level);

		// Set the owner of this bolt to be the character which fired it.
		bolt.setOwner(character);
		
		// Check whether we have the stamina required to fire this projectile.
		if(!character.getStaminaStatus().expendStamina(bolt.getFireStaminaCost())) {
			// We failed to fire this bolt as we do not have the stamina required.
			return false;
		}
		
		// TODO modify the bolts damage to reflect character stats.
		
		// Attempt to fire, will return true if success, false if the physics world rejects the projectiles physics box.
		boolean ammoUsageSuccess = level.getLevelWorld().getProjectilePool().add(bolt);
		// If the ammo was fired play a sound.
		if(ammoUsageSuccess) { 
			Audio.getSoundEffect(Audio.SoundEffect.JUMP).play(); // TODO Replace! 
		}
		// Return whether the ammo usage was a success.
		return ammoUsageSuccess;
	}
}
