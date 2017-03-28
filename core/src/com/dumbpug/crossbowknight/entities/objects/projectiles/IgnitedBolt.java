package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.IEmitterDetails;
import com.dumbpug.crossbowknight.particles.burning.BurningParticleGenerator;

/**
 * An Ignited bolt.
 * Created by nik on 10/10/16.
 */
public class IgnitedBolt extends Bolt {
	/** The burning particle emitter for this bolt. */
	private Emitter burningParticleEmitter;
	
	/**
     * Create a new instance of the IgnitedBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     * @param level
     */
	public IgnitedBolt(float x, float y, float angle, float velocity, Level level) {
		super(x, y, angle, velocity);
		// Create an burning emitter/particle generator.
    	BurningParticleGenerator burningParticleGenerator = new BurningParticleGenerator(level.getLevelCamera());
    	burningParticleEmitter = new Emitter(burningParticleGenerator, CrossbowKnight.getSpriteBatch());
    	// Set the emitter details. (position)
    	final BoltPhysicsBox ignitedBoltPhysicsBox = this.boltPhysicsBox;
    	burningParticleEmitter.setEmitterDetails(new IEmitterDetails() {
			@Override
			public float positionX() { return ignitedBoltPhysicsBox.getX(); }
			@Override
			public float positionY() { return ignitedBoltPhysicsBox.getY();}
    	});
    	// Set the emitter activity.
    	burningParticleEmitter.setEmitterActivity(burningParticleGenerator);
    	// Add the burning emitter to the level world.
    	level.getActiveLevelWorld().getEmitterPool().add(burningParticleEmitter);
	}

	@Override
	public float getBaseDamage() { return 30f; }

	@Override
	public ProjectileType getProjectileType() { return ProjectileType.BOLT_IGNITED; }
}
