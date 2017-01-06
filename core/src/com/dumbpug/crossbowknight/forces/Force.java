package com.dumbpug.crossbowknight.forces;

import java.util.ArrayList;
import com.dumbpug.crossbowknight.effects.DamageEffect;
import com.dumbpug.nbp.NBPBloom;

/**
 * Represents a force.
 * @author nikolas.howard
 */
public abstract class Force extends NBPBloom {
	
	public Force(float x, float y, float radius, float force) { 
		super(x, y, radius, force);
	}
	
	/**
	 * Get the type of this force.
	 * @return type.
	 */
	public abstract ForceType getForceType();
	
	/**
	 * Get any damage effects associated with this force.
	 * @return damage effects.
	 */
	public abstract ArrayList<DamageEffect> getDamageEffects();
}
