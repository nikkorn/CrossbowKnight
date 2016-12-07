package com.dumbpug.crossbowknight.particles;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A particle emitter.
 * @author nikolas.howard
 */
public class Emitter {
	/** The particle generator. */
	private IParticleGenerator particleGenerator;
	/** The SpriteBatch used to draw our particles. */
	private SpriteBatch batch;
	/** The EmitterDEtials object to be passed to our particle generator. */
	private EmitterDetails emitterDetails = new EmitterDetails();
	/** The list of particles. */
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	/**
	 * Create a new instance of the Emitter class.
	 * @param particleGenerator
	 * @param batch
	 */
	public Emitter(IParticleGenerator particleGenerator, SpriteBatch batch) {
		this.batch             = batch;
		this.particleGenerator = particleGenerator;
	}
	
	/**
	 * Spawn a new particle.
	 */
	public void spawnParticle() {
		// We can only spawn a particle if we have no hit out limit.
		if(particles.size() > C.PARTICLES_LIMIT) {
			// Generate a new particle.
			Particle newParticle = particleGenerator.generate(emitterDetails);
			// Add it to our collection of particles.
			particles.add(newParticle);
			newParticle.onCreation();
		}
	}
	
	/**
	 * Update this emitter.
	 */
	public void update() {
		// Remove dead particles.
		Iterator<Particle> particleIterator = particles.iterator();
		while (particleIterator.hasNext()) {
			Particle currentParticle = particleIterator.next();
		    if (!currentParticle.isAlive()) {
		    	currentParticle.onEnd();
		    	particleIterator.remove();
		    }
		}
		// Update all of our particles.
		for(Particle particle : particles) {
			particle.update();
		}
	}
	
	/**
	 * Draw this emitters particles.
	 */
	public void draw() {
		for(Particle particle : particles) {
			particle.draw(batch);
		}
	}
}
