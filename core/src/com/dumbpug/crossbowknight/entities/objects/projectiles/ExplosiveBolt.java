package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.audio.Audio.SoundEffect;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.ExplosionParticleGenerator;
import com.dumbpug.nbp.NBPBloom;
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
        // Create an explosion emitter/particle generator.
    	ExplosionParticleGenerator explosionParticleGenerator = new ExplosionParticleGenerator(level);
    	Emitter explosionEmitter = new Emitter(explosionParticleGenerator, CrossbowKnight.getSpriteBatch());
    	explosionEmitter.getEmitterDetails().positionX = this.boltPhysicsBox.getX();
    	explosionEmitter.getEmitterDetails().positionY = this.boltPhysicsBox.getY();
    	explosionEmitter.setEmitterActivity(explosionParticleGenerator);
    	// Add the explosion emitter to the level world.
    	level.getLevelWorld().getEmitterPool().add(explosionEmitter);
    	// Add an explosion bloom to the physics world.
    	level.getLevelWorld().getPhysicsWorld().addBloom(new NBPBloom(this.boltPhysicsBox.getX(), 
    			this.boltPhysicsBox.getY(), C.PROJECTILE_EXPLOSIVEBOLT_BLAST_RADIUS * C.LAYOUT_MULTIPLIER, C.PROJECTILE_EXPLOSIVEBOLT_BLAST_FORCE * C.LAYOUT_MULTIPLIER));
    	// Play the explosion sound!
    	Audio.getSoundEffect(SoundEffect.EXPLOSION).play();
    }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_EXPLOSIVE; }

    @Override
    public float getBaseDamage() { return 40f; }
}
