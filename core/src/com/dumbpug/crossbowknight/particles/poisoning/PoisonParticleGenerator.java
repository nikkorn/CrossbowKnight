package com.dumbpug.crossbowknight.particles.poisoning;

import java.util.Random;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.IEmitterActivity;
import com.dumbpug.crossbowknight.particles.IEmitterDetails;
import com.dumbpug.crossbowknight.particles.IParticleGenerator;
import com.dumbpug.crossbowknight.particles.Particle;

/**
 * Generator for PoisonParticle.
 * @author nikolas.howard
 */
public class PoisonParticleGenerator implements IParticleGenerator, IEmitterActivity {
	/** The level camera. */
	private LevelCamera camera;
	/** The RNG to use when generating particle velocities. */
	private Random random;
	/** The time in milliseconds to wait between particle generations. */
	private long particleGenerationDelay = 80;
	/** The last time that a particle was generated. */
	private long lastParticleGenerationTime = System.currentTimeMillis();
	/** The particle spawn area around the spawn point. */
	private float areaWidth  = 2f;
	private float areaHeight = 2f;
	
	/**
	 * Create a new instance of the PoisonParticleGenerator class.
	 * @param camera
	 */
	public PoisonParticleGenerator(LevelCamera camera) {
		this.camera = camera;
		this.random = new Random();
	}
	
	/**
	 * Create a new instance of the PoisonParticleGenerator class.
	 * Define the area size in which particles are generated.
	 * @param camera
	 * @param areaWidth
	 * @param areaHeight
	 */
	public PoisonParticleGenerator(LevelCamera camera, float areaWidth, float areaHeight) {
		this(camera);
		this.areaWidth  = areaWidth;
		this.areaHeight = areaHeight;
	}
	
	/**
	 * Generate an PoisonParticle.
	 * @param emitterDetails
	 * @return PoisonParticle
	 */
	public Particle generate(IEmitterDetails emitterDetails) {
		// Create a particle at the emitter position. 
		// Generate some random slight positional offsets
		// for the particles, to make it more ...burny.
		// Use the height and width of the burning area to spawn particles in!
		float randomPosOffsetX = (random.nextFloat()*areaWidth) - (areaWidth/2);
		float randomPosOffsetY = (random.nextFloat()*areaHeight) - (areaHeight/2);
		// Create the particle.
		return new PoisonParticle(emitterDetails.positionX() + randomPosOffsetX, emitterDetails.positionY() + randomPosOffsetY, camera);
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
