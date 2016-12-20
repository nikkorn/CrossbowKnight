package com.dumbpug.crossbowknight.particles;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

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
	private IEmitterDetails emitterDetails = new EmitterDetails();
	/** The Emitter Activity. If this is set it will be called at the start of each emitter update. */
	private IEmitterActivity emitterActivity = null;
	/** The list of particles. */
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	/** whether this emitter is alive. */
	private boolean isAlive = true;
	/** whether this emitter is alive only as long as we have alive particles. */
	private boolean isAliveOnlyIfHasParticles = false;
	
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
	 * Sets whether this emitter is alive.
	 * @param isAlive
	 */
	public void setAlive(boolean isAlive) { this.isAlive = isAlive; }
	
	/**
	 * Gets whether this emitter is alive.
	 * @returns isAlive
	 */
	public boolean isAlive() { return this.isAlive; }
	
	/**
	 * Sets whether this emitter is alive only as long as we have alive particles.
	 * @param isAliveOnlyIfHasParticles
	 */
	public void setAliveOnlyIfHasParticles(boolean isAliveOnlyIfHasParticles) { this.isAliveOnlyIfHasParticles = isAliveOnlyIfHasParticles; }
	
	/**
	 * Get the emitter activity to be executed at the start of ever emitter update.
	 * @return IEmitterActivity
	 */
	public IEmitterActivity getEmitterActivity() { return emitterActivity; }

	/**
	 * Set the emitter activity to be executed at the start of ever emitter update.
	 * @param emitterActivity
	 */
	public void setEmitterActivity(IEmitterActivity emitterActivity) { this.emitterActivity = emitterActivity; }
	
	/**
	 * Get the emitter details.
	 * @return emitter details.
	 */
	public IEmitterDetails getEmitterDetails() { return this.emitterDetails; }
	
	/**
	 * Set the emitter details.
	 * @param emitter details.
	 */
	public void setEmitterDetails(IEmitterDetails emitterDetails) { this.emitterDetails = emitterDetails; }
	
	/**
	 * Removes the emitter activity.
	 */
	public void disposeOfActivity() { this.emitterActivity = null; }
	
	/**
	 * Spawn a new particle.
	 */
	public void spawnParticle() {
		// We can only spawn a particle if we have no hit out limit.
		if(particles.size() < C.PARTICLES_LIMIT) {
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
		// Don't do anything if this emitter is not alive.
		if(!isAlive) {
			return;
		}
		// Do our activity if we have one.
		if(emitterActivity != null) {
			emitterActivity.act(this);
		}
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
		// If this emitter dies when we have no particles we need to handle it.
		if(isAliveOnlyIfHasParticles) {
			isAlive = this.hasActiveParticles();
		}
	}
	
	/**
	 * Returns whether there are still any active particles.
	 * @return has active particles.
	 */
	public boolean hasActiveParticles() {
		for(Particle particle : particles) {
			if(particle.isAlive()) {
				// At least one particle is alive.
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Draw this emitters particles.
	 */
	public void draw() {
		// Don't do anything if this emitter is not alive.
		if(!isAlive) {
			return;
		}
		// Draw all of the particles.
		for(Particle particle : particles) {
			particle.draw(batch);
		}
	}
}
