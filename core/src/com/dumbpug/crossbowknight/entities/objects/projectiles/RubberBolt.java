package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.nbp.NBPBox;

/**
 * A bolt which bounces off of static objects.
 * Created by nik on 10/10/16.
 */
public class RubberBolt extends Bolt {
	/** The number of times that this bolt will bounce off of static blocks. */
	private int bounces = C.PROJECTILE_RUBBERBOLT_BOUNCES;

	/**
     * Create a new instance of the RubberBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
	public RubberBolt(float x, float y, float angle, float velocity) {
		super(x, y, angle, velocity);
		// We want a really bouncy bolt!
		boltPhysicsBox.setRestitution(1f);
		boltPhysicsBox.setFriction(1f);
	}

	/**
	 * Called when BoltPhysicsBox hits a static object.
	 * @param collidingBox
	 */
	@Override
	public void onStaticObjectHit(NBPBox collidingBox) {
		// We have hit a static object.
		if(bounces > 0) {
			// We bounced off a static object, play a noise.
			Audio.getSoundEffect(Audio.SoundEffect.JUMP).play();
			// Decrement the number of bounces that this rubber bolt has left.
			bounces--;
		} else {
			// We have stopped bouncing so now we should stick to something.
			this.isStuck = true;
			// We hit a static object, play a noise.
			Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
			// Check whether we want to get rid our our bolts physics box.
			boltPhysicsBox.markForDeletion();
		}
	}

	@Override
	public float getBaseDamage() { return 16f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_RUBBER; }
}
