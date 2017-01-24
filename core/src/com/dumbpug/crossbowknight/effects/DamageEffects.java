package com.dumbpug.crossbowknight.effects;

import java.util.ArrayList;
import java.util.Iterator;
import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * Represents a list of active damage effects.
 * @author nikolas.howard
 */
public class DamageEffects {
	/** The list of active damage effects. */
	private ArrayList<DamageEffect> activeDamageEffects = new ArrayList<DamageEffect>();
	
	/**
	 * Add an active damage effect.
	 * @param damageEffect
	 */
	public void add(DamageEffect damageEffect) { activeDamageEffects.add(damageEffect); }
	
	/**
	 * Add a number of active damage effects.
	 * @param damageEffect
	 */
	public void add(ArrayList<DamageEffect> damageEffects) { 
		for(DamageEffect effect : damageEffects) {
			activeDamageEffects.add(effect);
		}
	}
	
	/**
	 * Apply any active damage effects to the character.
	 * Any inactive effects will also be removed.
	 * @param character
	 */
	public void apply(com.dumbpug.crossbowknight.entities.characters.Character character) {
		for (Iterator<DamageEffect> iterator = activeDamageEffects.iterator(); iterator.hasNext();) {
			DamageEffect effect = iterator.next();
			// Apply the damage affect and determine whether the effect was made inactive during its application.
			boolean wasMadeInactive = character instanceof Player ? effect.applyToPlayer((Player) character) : effect.apply(character);
			// If this effect is inactive, remove it.
		    if(wasMadeInactive) {
		        iterator.remove();
		    }
		}
	}
	
	/**
	 * Returns true if there are any active damage effects which mach the specified type.
	 * @param type
	 * @return is active
	 */
	public boolean isDamageEffectOfTypeActive(DamageType type) {
		for(DamageEffect effect : activeDamageEffects) {
			if(effect.getType() == type) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get whether a bleeding damage effect is active.
	 * @return
	 */
	public boolean isBleeding() { return isDamageEffectOfTypeActive(DamageType.BLEEDING); }
	
	/**
	 * Get whether a burning damage effect is active.
	 * @return
	 */
	public boolean isBurning() { return isDamageEffectOfTypeActive(DamageType.BURNING); }
	
	/**
	 * Get whether a poison damage effect is active.
	 * @return
	 */
	public boolean isPoisoned() { return isDamageEffectOfTypeActive(DamageType.POISON); }
	
	/**
	 * Clear all active damage effects.
	 */
	public void clear() { activeDamageEffects.clear(); }
}
