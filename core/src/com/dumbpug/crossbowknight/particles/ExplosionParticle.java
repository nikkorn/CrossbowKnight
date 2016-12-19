package com.dumbpug.crossbowknight.particles;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.nbp.NBPBoxType;

/**
 * Represents an explosion particle.
 * @author nikolas.howard
 */
public class ExplosionParticle extends Particle {
	/** The sprite for this particle. */
	private static Sprite explosionParticleSprite = new Sprite(new Texture("graphics/particles/explosion_particle.png"));
	/** The physics box for this particle. */
	private ParticlePhysicsBox particlePhysicsBox;
	/** The size of the particle in the world. */
	private static float explosionParticleSize = 0.2f;
	/** The size of this particle sprite. */
	private static float explosionParticleSpriteSize = 4 * C.LAYOUT_MULTIPLIER;
	/** The level camera, needed for drawing particles in correct positions. */
	private LevelCamera camera;
	/** The scale of this particles sprite. */
	private float scale = 1f;
	/** The RNG used to randomise particle characteristics. */
	private static Random random = new Random();
	
	/**
	 * Create a new instance of the ExplosionParticle class.
	 * @param posX
	 * @param posY
	 * @param velX
	 * @param velY
	 * @param level
	 */
	ExplosionParticle(float posX, float posY, float velX, float velY, Level level) {
		// Set up the particle physics box.
 		particlePhysicsBox = new ParticlePhysicsBox(posX, posY, explosionParticleSize, explosionParticleSize, NBPBoxType.KINETIC);
		particlePhysicsBox.setVelx(velX);
		particlePhysicsBox.setVely(velY);
		particlePhysicsBox.setRestitution(0.5f);
		particlePhysicsBox.setFriction(0.5f);
		level.getLevelWorld().getPhysicsWorld().addBox(particlePhysicsBox);
		// Set the life of this particle.
		this.setLife((random.nextInt(3) + 1) * 200);
		// Set the level camera.
		this.camera = level.getLevelCamera();
	}

	@Override
	void update() {}

	@Override
	float getPositionX() { return particlePhysicsBox.getX(); }

	@Override
	float getPositionY() { return particlePhysicsBox.getY(); }

	@Override
	void draw(SpriteBatch batch) {
		// Set the sprite size.
		explosionParticleSprite.setSize(explosionParticleSpriteSize, explosionParticleSpriteSize);
		// Set the sprite position.
		explosionParticleSprite.setX((getPositionX()*C.LAYOUT_MULTIPLIER) + camera.getX());
		explosionParticleSprite.setY((getPositionY()*C.LAYOUT_MULTIPLIER) + camera.getY());
		// Set scale based on life.
		explosionParticleSprite.setScale(scale += 0.1f);
		// TODO Set opacity based on life.
		explosionParticleSprite.setAlpha(this.getRemainingLife() / (float) this.getLife());
		// Draw the particle.
		explosionParticleSprite.draw(batch);
	}

	@Override
	void onCreation() {
		// TODO Make explosion sound!
	}

	@Override
	void onEnd() {
		// On end we need to mark the particle physics box for deletion.
		particlePhysicsBox.markForDeletion();
	}
}
