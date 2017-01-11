package com.dumbpug.crossbowknight.effects;

import com.dumbpug.crossbowknight.entities.characters.player.EquippedItems;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Helmet;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Shield;

/**
 * Represents an effect which can cause damage.
 * @author nikolas.howard
 */
public class DamageEffect {
	/** The amount of damage this effect causes when first applied. */
	private int intiallyAppliedDamage = 0;
	/** The type damage this effect applies. */
	private DamageType type = DamageType.NORMAL;
	
	/**
	 * Apply this damage effect to a player character.
	 * In the case of applying damage to a player, we have to take into account
	 * the players helmet and shield if they are blocking.
	 * @param player
	 * @return whether the effect is finished.
	 */
	public boolean applyToPlayer(Player player) {
		// Get the players equipped items.
		EquippedItems playerEquipment = player.getEquipment();
		// Get the initially applied damage.
		float damage = getIntiallyAppliedDamage();
		// If the player is wearing a helmet, reduce the damage 
		// according to the helmets defense modifier.
		Helmet equippedHelmet = playerEquipment.getHelmetSlot();
		if(equippedHelmet != null) {
			damage -= (getIntiallyAppliedDamage() * equippedHelmet.getDefenseBuff());
		}
		// If the player is holding a shield AND is guarding, reduce the damage 
		// according to the shields defense modifier.
		// The durability of the shield will also drop 10% of the damage take.
		if(player.isGuarding()) {
			// If the player is guarding then they must have a shield.
			Shield shield = playerEquipment.getShieldSlot();
			// Get the amount of damage that blocking prevents.
			float shieldDamageReduction = getIntiallyAppliedDamage() * shield.getDefenseBuff();
			// Check that the shield has the durability available for blocking.
			if(shieldDamageReduction < shield.getCurrentDurability()) {
				// The shield will not break this time round.
				shield.setCurrentDurability(shield.getCurrentDurability() - shieldDamageReduction); // TODO fix this
				// Reduce the damage.
				damage -= (getIntiallyAppliedDamage() - shieldDamageReduction);
			} else {
				// Blocking this damage effect will cause our shield to break!
				shield.setCurrentDurability(0);
				// If our shield broke while blocking, then we forget about our defenese buff.
				damage -= getIntiallyAppliedDamage();
			}
		}
		// Damage character with resolved damage.
		player.getHealthStatus().applyDamage((int) damage);
		// Damage effects which are not prolonged are only applied once.
		return true;
	}
	
	/**
	 * Apply this damage effect to a character
	 * @param character
	 * @return whether the effect is finished.
	 */
	public boolean apply(com.dumbpug.crossbowknight.entities.characters.Character character) {
		// Damage character with initial damage.
		character.getHealthStatus().applyDamage(getIntiallyAppliedDamage());
		// Damage effects which are not prolonged are only applied once.
		return true;
	}
	
	/**
	 * Get amount of damage this effect causes when first applied. 
	 * @return intiallyAppliedDamage.
	 */
	public int getIntiallyAppliedDamage() { return intiallyAppliedDamage; }

	/**
	 * Set amount of damage this effect causes when first applied. 
	 * @param intiallyAppliedDamage
	 */
	public void setIntiallyAppliedDamage(int intiallyAppliedDamage) { this.intiallyAppliedDamage = intiallyAppliedDamage; }
	
	/**
	 * Is this a prolonged damage effect or is it applied instantly.
	 * @return is prolonged.
	 */
	public boolean isProlonged() { return false; }

	/**
	 * Get the type of this effect.
	 * @return type.
	 */
	public DamageType getType() { return type; }

	/**
	 * Set the type of this effect.
	 * @param type
	 */
	public void setType(DamageType type) { this.type = type; }
}
