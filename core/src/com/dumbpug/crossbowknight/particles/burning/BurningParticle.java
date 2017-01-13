package com.dumbpug.crossbowknight.particles.burning;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.particles.Particle;

/**
 * Represents a burning particle.
 * @author nikolas.howard
 */
public class BurningParticle extends Particle {
	/** The sprite for this particle. */
	private static Sprite burningParticleSprite = new Sprite(new Texture("graphics/particles/explosion_particle.png"));
	/** The size of this particle sprite. */
	private static float burningParticleSpriteSize = 3 * C.LAYOUT_MULTIPLIER;
	/** The position of the particle. */
	private float posX, posY;
	/** The level camera, needed for drawing particles in correct positions. */
	private LevelCamera camera;
	/** The scale of this particles sprite. */
	private float scale = 1f;
	/** The RNG used to randomise particle characteristics. */
	private static Random random = new Random();
	
	/**
	 * Create a new instance of the BurningParticle class.
	 * @param posX
	 * @param posY
	 * @param camera
	 */
	BurningParticle(float posX, float posY, LevelCamera camera) {
		// Set the position of the particle.
		this.posX = posX;
		this.posY = posY;
		// Set the life of this particle.
		this.setLife((random.nextInt(3) + 1) * 200);
		// Set the level camera.
		this.camera = camera;
	}
	
	@Override
	public void update() {
		// Burning particles will rise slowly.
		posY += 0.2f;
	}
	
	public float getPositionX() { return posX; }
	
	public float getPositionY() { return posY; }

	protected void draw(SpriteBatch batch) {
		// Set the sprite size.
		burningParticleSprite.setSize(burningParticleSpriteSize, burningParticleSpriteSize);
		// Set the sprite position.
		burningParticleSprite.setX((getPositionX()*C.LAYOUT_MULTIPLIER) + camera.getX());
		burningParticleSprite.setY((getPositionY()*C.LAYOUT_MULTIPLIER) + camera.getY());
		// Set scale based on life.
		burningParticleSprite.setScale(scale += 0.05f);
		// Set opacity based on life.
		burningParticleSprite.setAlpha(this.getRemainingLife() / (float) this.getLife());
		// Draw the particle.
		burningParticleSprite.draw(batch);
	}

	protected void onCreation() {}

	protected void onEnd() {}
}
