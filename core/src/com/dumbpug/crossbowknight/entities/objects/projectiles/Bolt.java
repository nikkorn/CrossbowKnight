package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;

/**
 * Base class for all bolts.
 * Created by nik on 09/10/16.
 */
public abstract class Bolt extends Projectile {
	/** The physics entity for this bolt. */
	private BoltPhysicsBox boltPhysicsBox;
	/** Is the bolt stuck in something? */
	private boolean isStuck = false;

	/**
	 * Create a new instance of a child of the Bolt class.
	 */
	public Bolt(float x, float y, float angle, float velocity) {
		// Set the bolt physics box.
		boltPhysicsBox = new BoltPhysicsBox(this, x-(getBoltHeadSize()/2),
				y-(getBoltHeadSize()/2),
				getBoltHeadSize() * (1f - C.PROJECTILE_BOLT_HITBOX_PADDING),
				getBoltHeadSize() * (1f - C.PROJECTILE_BOLT_HITBOX_PADDING));
		boltPhysicsBox.setAffectedByGravity(isAffectedByGravity());
		boltPhysicsBox.applyVelocityInDirection(angle, velocity);
	}

	/**
	 * Called when BoltPhysicsBox is pushed by a bloom. 
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 */
	public void onPush(NBPBloom bloom, float angleOfForce, float force) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Called when BoltPhysicsBox hits a static object.
	 * @param collidingBox
	 */
	public void onStaticObjectHit(NBPBox collidingBox) {
		// TODO Check to see if this arrow can rebound.
		this.isStuck = true;
		// We hit a static object, play a noise.
		Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
		// Check whether we want to get rid our our bolts physics box.
		boltPhysicsBox.markForDeletion();
	}

	/**
	 * The number of rebounds off of static blocks this bolt gets.
	 * @return number of rebounds.
     */
	protected int numberOfRebounds() { return 0; }

	/**
	 * Is this bolt stuck in something?
	 * @return is stuck
     */
	public boolean isStuck() { return isStuck; }

	/**
	 * Get the physics box for this bolt.
	 * @return bolt physics box.
     */
	public NBPBox getPhysicsBox() { return this.boltPhysicsBox; }
	
	/**
	 * Get the texture for this bolt.
	 * @return texture.
	 */
	public abstract Sprite getBoltSprite();

	/**
	 * Get the base damage of this bolt.
	 * @return bolt base damage.
	 */
	public abstract float getBaseDamage();

	/**
	 * Get the size of the head of this bolt.
	 * This defines the size of the bolt as well
	 * as the size of the bolts physics entity
	 * @return bolt head size.
	 */
	public abstract float getBoltHeadSize();
}
