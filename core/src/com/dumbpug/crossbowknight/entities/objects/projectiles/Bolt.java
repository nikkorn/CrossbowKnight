package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.CharacterPhysicsBox;
import com.dumbpug.crossbowknight.entities.characters.Character;
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
	 * Called when BoltPhysicsBox hits a kinematic object (player/enemy).
	 * @param collidingBox
	 */
	public void onKinematicObjectHit(NBPBox collidingBox) {
		// We only care about collisions with bolts that are active.
		if (this.isActive()) {
			// The bolt has hit a game entity which could very well be a character. 
			if(collidingBox instanceof CharacterPhysicsBox) {
				// This bolt has hit a character.
				Character hitCharacter = ((CharacterPhysicsBox<?>) collidingBox).getCharacter();
				// In a scenario where a character has hit themselves, it will only
				// count as a hit if the projectile launch life has passed a lauch threshold.
				// This is required as we don't want to accidentaly hit ourselves when initially 
				// launching a projectile.
				if(hitCharacter == this.getOwner() && this.getLaunchLife() < C.PROJECTILE_LAUNCH_IMMUNITY_THRESHOLD) {
					return;
				}
				// Hit the character with this bolt.
				hitCharacter.onHitByBolt(this);
				// The character has been hit, this bolt is no longer active.
				// It can be attached to a character physics box at a later time though (if barbed e.g.).
				this.setActive(false);
				// We no longer need the physics box for this bolt.
				boltPhysicsBox.markForDeletion();
			}
			
			// TODO Handle hitting of something which is not a character.
		}
	}
	
	/**
	 * Called when BoltPhysicsBox hits a static object.
	 * @param collidingBox
	 */
	public void onStaticObjectHit(NBPBox collidingBox) {
		// By default, all bolts will stick when they hit a static object.
		this.isStuck = true;
		// We hit a static object, play a noise.
		Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
		// Check whether we want to get rid our our bolts physics box.
		boltPhysicsBox.markForDeletion();
	}

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
