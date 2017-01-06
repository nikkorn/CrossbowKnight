package com.dumbpug.crossbowknight.forces;

import java.util.ArrayList;
import com.dumbpug.crossbowknight.effects.DamageEffect;
import com.dumbpug.crossbowknight.effects.DamageType;

/**
 * Represents a force which is an explosion.
 * @author nikolas.howard
 */
public class Explosion extends Force {
	/** The list containing the explosive damage effect to apply. */
	ArrayList<DamageEffect> explosiveDamageEffectList = new ArrayList<DamageEffect>();

	/**
	 * Create a new instance of the Explosion class.
	 * @param x
	 * @param y
	 * @param radius
	 * @param force
	 * @param explosiveDamage
	 */
	public Explosion(float x, float y, float radius, float force, float explosiveDamage) {
		super(x, y, radius, force);
		// Set the explosive damage effect which will be applied to characters on interaction with this bloom.
		DamageEffect explosiveDamageEffect = new DamageEffect();
		explosiveDamageEffect.setType(DamageType.EXPLOSIVE);
		explosiveDamageEffect.setIntiallyAppliedDamage((int) explosiveDamage);
		explosiveDamageEffectList.add(explosiveDamageEffect);
	}

	@Override
	public ForceType getForceType() { return ForceType.EXPLOSION; }

	@Override
	public ArrayList<DamageEffect> getDamageEffects() { return explosiveDamageEffectList; }
}
