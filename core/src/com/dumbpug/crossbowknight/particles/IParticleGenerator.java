package com.dumbpug.crossbowknight.particles;

/**
 * Functional interface for creating a particle.
 * @author nikolas.howard
 */
public interface IParticleGenerator {
	
	/**
	 * Generate a particle.
	 * @param emitterDetails
	 * @return particle
	 */
	Particle generate(IEmitterDetails emitterDetails);
}
