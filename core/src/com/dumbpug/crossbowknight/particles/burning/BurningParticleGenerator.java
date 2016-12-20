package com.dumbpug.crossbowknight.particles.burning;

import java.util.Random;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.IEmitterActivity;
import com.dumbpug.crossbowknight.particles.IEmitterDetails;
import com.dumbpug.crossbowknight.particles.IParticleGenerator;
import com.dumbpug.crossbowknight.particles.Particle;

/**
 * Generator for ExplosionParticle.
 * @author nikolas.howard
 */
public class BurningParticleGenerator implements IParticleGenerator, IEmitterActivity {
	/** The level. */
	private Level level;
	/** The RNG to use when generating particle velocities. */
	private Random random;
	/** The time in milliseconds to wait between particle generations. */
	private long particleGenerationDelay = 80;
	/** The last time that a particle was generated. */
	private long lastParticleGenerationTime = System.currentTimeMillis();
	
	/**
	 * Create a new instance of the ExplosionParticleGenerator class.
	 * @param level
	 */
	public BurningParticleGenerator(Level level) {
		this.level  = level;
		this.random = new Random();
	}
	
	/**
	 * Generate an BurningParticle.
	 * @param emitterDetails
	 * @return BurningParticle
	 */
	public Particle generate(IEmitterDetails emitterDetails) {
		// Create a particle at the emitter position. 
		// Generate some random slight positional offsets
		// for the particles, to make it more ...burny.
		// TODO Eventually take a height and width of the burning area to spawn particles in!
		float randomPosOffsetX = (random.nextFloat()*2f) - 1f;
		float randomPosOffsetY = (random.nextFloat()*2f) - 1f;
		// Create the particle.
		return new BurningParticle(emitterDetails.positionX() + randomPosOffsetX, emitterDetails.positionY() + randomPosOffsetY, level);
	}

	/**
	 * The emitter activity which actually sets up an explosion emitter.
	 */
	@Override
	public void act(Emitter emitter) {
		// We should only generate a particle when enough time has passed since the last time.
		if((System.currentTimeMillis() - lastParticleGenerationTime) >= particleGenerationDelay) {
			// Generate a particle.
			emitter.spawnParticle();
			// Reset the generation delay.
			lastParticleGenerationTime = System.currentTimeMillis();
		}
	}
}
