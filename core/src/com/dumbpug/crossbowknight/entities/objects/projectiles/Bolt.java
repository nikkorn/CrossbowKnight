package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
		boltPhysicsBox = new BoltPhysicsBox(this, x-(getBoltHeadSize()/2) , y-(getBoltHeadSize()/2), getBoltHeadSize(), getBoltHeadSize());
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
