package com.dumbpug.crossbowknight.particles;

import com.dumbpug.crossbowknight.level.LevelWorld;

/**
 * Generator for ExplosionParticle.
 * @author nikolas.howard
 */
public class ExplosionParticleGenerator implements IParticleGenerator {
	
	/**
	 * Create a new instance of the ExplosionParticleGenerator class.
	 * @param levelWorld
	 */
	public ExplosionParticleGenerator(LevelWorld levelWorld) {
		
	}
	
	/**
	 * Generate an ExplosionParticle.
	 * @param emitterDetails
	 * @return ExplosionParticle
	 */
	public Particle generate(EmitterDetails emitterDetails) {
		// TODO Create a particle at the emitter position. Passing random x/y velocity.
		
		
		return new ExplosionParticle();
	}
}
