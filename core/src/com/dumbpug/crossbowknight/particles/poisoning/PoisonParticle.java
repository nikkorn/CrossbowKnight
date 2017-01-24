package com.dumbpug.crossbowknight.particles.poisoning;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.particles.Particle;

/**
 * Represents a poison particle.
 * @author nikolas.howard
 */
public class PoisonParticle extends Particle {
	/** The sprite for this particle. */
	private static Sprite poisonParticleSprite = new Sprite(new Texture("graphics/particles/poison_particle.png"));
	/** The size of this particle sprite. */
	private static float poisonParticleSpriteSize = 2 * C.LAYOUT_MULTIPLIER;
	/** The position of the particle. */
	private float posX, posY;
	/** The level camera, needed for drawing particles in correct positions. */
	private LevelCamera camera;
	/** The scale of this particles sprite. */
	private float scale = 1f;
	/** The RNG used to randomise particle characteristics. */
	private static Random random = new Random();
	
	/**
	 * Create a new instance of the PoisonParticle class.
	 * @param posX
	 * @param posY
	 * @param camera
	 */
	PoisonParticle(float posX, float posY, LevelCamera camera) {
		// Set the position of the particle.
		this.posX = posX - ((poisonParticleSpriteSize/C.LAYOUT_MULTIPLIER)/2);
		this.posY = posY - ((poisonParticleSpriteSize/C.LAYOUT_MULTIPLIER)/2);
		// Set the life of this particle.
		this.setLife((random.nextInt(3) + 1) * 200);
		// Set the level camera.
		this.camera = camera;
	}
	
	@Override
	public void update() {
		// Poisons particles will rise slowly.
		posY += 0.1f;
	}
	
	public float getPositionX() { return posX; }
	
	public float getPositionY() { return posY; }

	protected void draw(SpriteBatch batch) {
		// Set the sprite size.
		poisonParticleSprite.setSize(poisonParticleSpriteSize, poisonParticleSpriteSize);
		// Set the sprite position.
		poisonParticleSprite.setX((getPositionX()*C.LAYOUT_MULTIPLIER) + camera.getX());
		poisonParticleSprite.setY((getPositionY()*C.LAYOUT_MULTIPLIER) + camera.getY());
		// Set scale based on life.
		poisonParticleSprite.setScale(scale += 0.01f);
		// Draw the particle.
		poisonParticleSprite.draw(batch);
	}

	protected void onCreation() {}

	protected void onEnd() {}
}
