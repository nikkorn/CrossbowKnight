package com.dumbpug.crossbowknight.particles;

import java.util.Random;
import com.dumbpug.crossbowknight.level.Level;

/**
 * Generator for ExplosionParticle.
 * @author nikolas.howard
 */
public class ExplosionParticleGenerator implements IParticleGenerator, IEmitterActivity {
	/** The level. */
	private Level level;
	/** The RNG to use when generating particle velocities. */
	private Random random;
	
	/**
	 * Create a new instance of the ExplosionParticleGenerator class.
	 * @param level
	 */
	public ExplosionParticleGenerator(Level level) {
		this.level  = level;
		this.random = new Random();
	}
	
	/**
	 * Generate an ExplosionParticle.
	 * @param emitterDetails
	 * @return ExplosionParticle
	 */
	public Particle generate(EmitterDetails emitterDetails) {
		// Create a particle at the emitter position. Passing random x/y velocity.
		float randomVelx = random.nextInt(6) - 3;
		float randomVely = random.nextInt(6) - 3;
		return new ExplosionParticle(emitterDetails.positionX, emitterDetails.positionY, randomVelx, randomVely, level);
	}

	/**
	 * The emitter activity which actually sets up an explosion emitter.
	 */
	@Override
	public void act(Emitter emitter) {
		// Generate some explosion particles.
		emitter.spawnParticle();
		emitter.spawnParticle();
		emitter.spawnParticle();
		emitter.spawnParticle();
		emitter.spawnParticle();
		// We want this emitter to die after all of our explosion particles die.
		emitter.setAliveOnlyIfHasParticles(true);
		// We have no need to carry out this activity again as we have created all 
		// the particles we will need for this emitters lifetime.
		emitter.disposeOfActivity();
	}
}
