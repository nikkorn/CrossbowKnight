package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.audio.Audio.SoundEffect;
import com.dumbpug.crossbowknight.entities.characters.Character;
import com.dumbpug.crossbowknight.forces.Explosion;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.EmitterDetails;
import com.dumbpug.crossbowknight.particles.explosion.ExplosionParticleGenerator;
import com.dumbpug.nbp.NBPBox;

/**
 * The explosive bolt which explodes on contact.
 * Created by nik on 10/10/16.
 */
public class ExplosiveBolt extends Bolt {
	/** The level */
   private Level level;
   
    /**
     * Create a new instance of the ExplosiveBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
    public ExplosiveBolt(float x, float y, float angle, float velocity, Level level) {
        super(x, y, angle, velocity);
        this.level = level;
    }

    /**
     * Called when BoltPhysicsBox hits a static object.
     * @param collidingBox
     */
    @Override
    public void onStaticObjectHit(NBPBox collidingBox) {
    	// Make this projectile inactive and mark it for deletion from our physics world.
    	this.setActive(false);
    	this.boltPhysicsBox.markForDeletion();
        // Create an explosion!
    	explode();
    }
	
	/**
	 * Called when a character has been hit by this bolt.
	 * In the case that the hit character is the same as the 
	 * owner of this bolt, this will only be called if this
	 * projectile has passed its launch threshold.
	 * @param character
	 */
	protected void onCharacterHit(Character character) {
		// We have hit a character, we should explode!
		explode();
	}
	
	/**
	 * Called when another bolt has been hit with this one.
	 * @param boltPhysicsBox
	 */
	protected void onOtherProjectileHit(BoltPhysicsBox boltPhysicsBox) {
		// We have hit another projectile, we should explode!
		explode();
		this.setActive(false);
		this.boltPhysicsBox.markForDeletion();
	}
	
	/**
	 * Explode this bolt.
	 */
	public void explode() {
		// Create an explosion emitter/particle generator.
    	ExplosionParticleGenerator explosionParticleGenerator = new ExplosionParticleGenerator(level);
    	Emitter explosionEmitter = new Emitter(explosionParticleGenerator, CrossbowKnight.getSpriteBatch());
    	// Set the emitter details. (position)
    	EmitterDetails emitterDetails = new EmitterDetails();
    	emitterDetails.positionX = this.boltPhysicsBox.getX();
    	emitterDetails.positionY = this.boltPhysicsBox.getY();
    	explosionEmitter.setEmitterDetails(emitterDetails);
    	// Set the emitter activity.
    	explosionEmitter.setEmitterActivity(explosionParticleGenerator);
    	// Add the explosion emitter to the level world.
    	level.getLevelWorld().getEmitterPool().add(explosionEmitter);
    	// Add an explosion bloom to the physics world.
    	level.getLevelWorld().applyForce(new Explosion(this.boltPhysicsBox.getX(), 
    			this.boltPhysicsBox.getY(), C.PROJECTILE_EXPLOSIVEBOLT_BLAST_RADIUS, C.PROJECTILE_EXPLOSIVEBOLT_BLAST_FORCE, getBaseDamage()));
    	// Play the explosion sound!
    	Audio.getSoundEffect(SoundEffect.EXPLOSION).play();
	}

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_EXPLOSIVE; }

    @Override
    public float getBaseDamage() { return 40f; }
}
