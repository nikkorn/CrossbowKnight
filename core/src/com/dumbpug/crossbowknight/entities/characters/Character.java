package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.effects.DamageEffect;
import com.dumbpug.crossbowknight.effects.DamageEffects;
import com.dumbpug.crossbowknight.effects.DamageType;
import com.dumbpug.crossbowknight.entities.objects.projectiles.Bolt;
import com.dumbpug.crossbowknight.forces.Force;
import com.dumbpug.nbp.NBPPoint;

/**
 * Represents a living being.
 * @author nikolas.howard
 */
public abstract class Character {
	/** The physics box for this character. */
    protected CharacterPhysicsBox<? extends Character> characterPhysicsBox;
	/** The stats of this character. */
	private Stats stats = new Stats();
	/** The health status of this character. */
	private HealthStatus healthStatus = new HealthStatus(stats);
	/** The stamina status of this character. */
	private StaminaStatus staminaStatus = new StaminaStatus(stats);
	/** The characters active damage effects. */
	private DamageEffects damageEffects = new DamageEffects();
	/** The character effect drawer. */
	private CharacterEffectDrawer characterEffectDrawer = null;
	/** The angle of focus for this character (where we are looking). */
    private float angleOfFocus = 0f;
	
	/**
	 * Update the character.
	 */
	public void update() {
		// Tick our stamina status as it replenishes over time.
		staminaStatus.tick();
		// Apply any damage effects..
		damageEffects.apply(this);
		// Update the character effect emitter pool.
		if(characterEffectDrawer != null) { characterEffectDrawer.updateEffectEmitters(); }
		
		
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
	 * Get stamina status.
	 * @return stamina status.
	 */
	public StaminaStatus getStaminaStatus() { return this.staminaStatus; }
	
	/**
	 * Set stamina status.
	 * @param staminaStatus
	 */
	public void setStaminaStatus(StaminaStatus staminaStatus) { this.staminaStatus = staminaStatus; }
	
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
	 * Get this characters angle of focus.
	 * @return angleOfFocus
	 */
	public float getAngleOfFocus() { return angleOfFocus; }

	/**
	 * Set this characters angle of focus.
	 * @param angleOfFocus
	 */
	public void setAngleOfFocus(float angleOfFocus) { this.angleOfFocus = angleOfFocus; }
	
	/**
	 * Get whether the character is touching the floor (if false then the character is airborne).
	 * @return is touching floor.
	 */
	public boolean isTouchingFloor() { return characterPhysicsBox.isTouchingFloor(); }
	
	/**
	 * Get whether this character is a player.
	 * @return is player.
	 */
	public boolean isPlayer() { return false; }

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
	 * Called when the character has been hit by a bolt.
	 * @param bolt
	 */
	public void onHitByBolt(Bolt bolt) {
		// TODO Stick into character physics box (or delete if not appropriate) 
		// TODO push character physics box a bit using bolt velocity (also take stats into account)
		// TODO Hurt our character using a damage effect(taking stats into account)! 
		DamageEffect testerDamageEffect = new DamageEffect();
		testerDamageEffect.setType(DamageType.NORMAL);
		testerDamageEffect.setIntiallyAppliedDamage(2);
		this.applyDamageEffect(testerDamageEffect);
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
