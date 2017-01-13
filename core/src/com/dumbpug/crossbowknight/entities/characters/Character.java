package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.effects.DamageEffect;
import com.dumbpug.crossbowknight.effects.DamageEffects;
import com.dumbpug.crossbowknight.forces.Force;
import com.dumbpug.nbp.NBPPoint;

/**
 * Represents a living being.
 * @author nikolas.howard
 */
public abstract class Character {
	/** The physics box for this character. */
    protected CharacterPhysicsBox<? extends Character> characterPhysicsBox;
	/** The health status of this character. */
	private HealthStatus healthStatus = new HealthStatus();
	/** The stats of this character. */
	private Stats stats = new Stats();
	/** The characters active damage effects. */
	private DamageEffects damageEffects = new DamageEffects();
	/** The character effect drawer. */
	private CharacterEffectDrawer characterEffectDrawer = null;
	
	/**
	 * Update the character.
	 */
	public void update() {
		// Apply any damage effects..
		damageEffects.apply(this);
		
		// ... 
	}
	
	/**
	 * Apply a damage effect to this character.
	 * @param damageEffect
	 */
	public void applyDamageEffect(DamageEffect damageEffect) { damageEffects.add(damageEffect); }

	/**
	 * Get health status.
	 * @return health status.
	 */
	public HealthStatus getHealthStatus() { return this.healthStatus; }
	
	/**
	 * Set health status.
	 * @param healthStatus
	 */
	public void setHealthStatus(HealthStatus healthStatus) { this.healthStatus = healthStatus; }
	
	/**
	 * Get stats.
	 * @return stats.
	 */
	public Stats getStats() { return this.stats; }
	
	/**
	 * Set stats.
	 * @param stats
	 */
	public void setStats(Stats stats) { this.stats = stats; }
	
	/**
	 * Set the character effects drawer.
	 * @param characterEffectDrawer
	 */
	public void setCharacterEffectsDrawer(CharacterEffectDrawer characterEffectDrawer) { this.characterEffectDrawer = characterEffectDrawer; }
	
	/**
	 * Get the characters active damage effects.
	 * @return damage effects
	 */
	public DamageEffects getDamageEffects() { return this.damageEffects; }
	
	/**
	 * Called by physics entity when the player box lands on a static floor.
	 */
	public void onLanding() {
		// The character landed so play a thump.
		Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
	}
	
	/**
	 * Get this characters current point of origin.
	 * @return players point of origin.
	 */
	public NBPPoint getCurrentOriginPoint() {
		// This characters point of origin will match the origin of its physics box.
		return characterPhysicsBox.getCurrentOriginPoint();
	}
	
	/**
	 * Get whether the character is currently idle (not moving at all)
	 * @return is idle.
	 */
	public boolean isIdle() {
		return (characterPhysicsBox.getVelx() < 0.2f && characterPhysicsBox.getVelx() > -0.2f) &&
				(characterPhysicsBox.getVely() < 0.2f && characterPhysicsBox.getVely() > -0.2f);
	}
	
	/**
	 * Get whether the character is touching the floor (if false then the character is airborne).
	 * @return is touching floor.
	 */
	public boolean isTouchingFloor() { return characterPhysicsBox.isTouchingFloor(); }

	/**
	 * Get this characters physics box.
	 * @return characterPhysicsBox
	 */
	public CharacterPhysicsBox<? extends Character> getPhysicsBox() { return characterPhysicsBox; }
	
	/**
	 * Handles the character being pushed by a force in the world.
	 * @param force
	 * @param angleOfForce
	 * @param forceAmount
	 * @param distance
	 */
	public void onPushedByForce(Force force, float angleOfForce, float forceAmount, float distance) {
		// Based on what type of force this is we may need to take action.
		switch(force.getForceType()) {
			case EXPLOSION:
				// Explosions have damage effects!
				this.damageEffects.add(force.getDamageEffects());
				break;
			default:
				// No idea what type of force this is.
				break;
		}
	}
	
	/**
	 * Draw the character.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public void draw(SpriteBatch batch, LevelCamera camera) {
		// Draw the character.
		drawCharacter(batch, camera);
		// Draw any overlapping effects.
		if(characterEffectDrawer != null) {
			characterEffectDrawer.drawEffects(batch, camera.getX(), camera.getY());
		}
	}
	
	/**
	 * Draw the character.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public abstract void drawCharacter(SpriteBatch batch, LevelCamera camera);
}
