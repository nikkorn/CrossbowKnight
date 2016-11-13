package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.resources.EntityResources;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBox;

/**
 * Base class for all bolts.
 * Created by nik on 09/10/16.
 */
public abstract class Bolt extends Projectile {
	/** The physics entity for this bolt. */
	protected BoltPhysicsBox boltPhysicsBox;
	/** Is the bolt stuck in something? */
	protected boolean isStuck = false;

	/**
	 * Create a new instance of a child of the Bolt class.
	 */
	public Bolt(float x, float y, float angle, float velocity) {
		// Set the bolt physics box.
		boltPhysicsBox = new BoltPhysicsBox(this, x-(C.PROJECTILE_BOLT_HEAD_SIZE/2),
				y-(C.PROJECTILE_BOLT_HEAD_SIZE/2),
				C.PROJECTILE_BOLT_HEAD_SIZE * (1f - C.PROJECTILE_BOLT_HITBOX_PADDING),
				C.PROJECTILE_BOLT_HEAD_SIZE * (1f - C.PROJECTILE_BOLT_HITBOX_PADDING));
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
	 public Sprite getBoltSprite() { return EntityResources.getEntityResources().getProjectileSprite(this.getProjectileType()); }

	/**
	 * Get the base damage of this bolt.
	 * @return bolt base damage.
	 */
	public abstract float getBaseDamage();
	
	/**
	 * Draw the bolt.
	 */
	@Override
    public void draw(SpriteBatch batch, float levelCameraXOffset, float levelCameraYOffset) {
        // Set the sprite up.
    	float hitboxPadding = (C.PROJECTILE_BOLT_HEAD_SIZE-getPhysicsBox().getWidth())/2;
    	float boltSpritePositionX = (getPhysicsBox().getX() - hitboxPadding) * C.LAYOUT_MULTIPLIER;
    	float boltSpritePositionY = (getPhysicsBox().getY() - (C.PROJECTILE_BOLT_HEAD_SIZE + hitboxPadding)) * C.LAYOUT_MULTIPLIER;
        getBoltSprite().setSize(C.PROJECTILE_BOLT_HEAD_SIZE * C.LAYOUT_MULTIPLIER, (C.PROJECTILE_BOLT_HEAD_SIZE*2) * C.LAYOUT_MULTIPLIER);
        getBoltSprite().setOrigin(getBoltSprite().getWidth()/2, getBoltSprite().getWidth()*1.5f); // TODO May be 0.5f
        getBoltSprite().setPosition(boltSpritePositionX + levelCameraXOffset, boltSpritePositionY + levelCameraYOffset);
        getBoltSprite().setRotation((float) ((BoltPhysicsBox) this.getPhysicsBox()).getLastAngleOfVelocity());
        // Draw the sprite.
        getBoltSprite().draw(batch);
    }
}
