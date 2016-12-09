package com.dumbpug.crossbowknight.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.nbp.NBPBoxType;

/**
 * Represents an explosion particle.
 * @author nikolas.howard
 */
public class ExplosionParticle extends Particle {
	/** The sprite for this particle. */
	private static Sprite explosionParticleSprite = new Sprite(new Texture("graphics/hud/ammobar/ammobar_background.png"));
	/** The physics box for this particle. */
	private ParticlePhysicsBox particlePhysicsBox;
	/** The size of the particle in the world. */
	private float particleSize = 5f;
	/** The level camera, needed for drawing particles in correct positions. */
	private LevelCamera camera;
	
	/**
	 * Create a new instance of the ExplosionParticle class.
	 * @param posX
	 * @param posY
	 * @param velX
	 * @param velY
	 * @param level
	 */
	ExplosionParticle(float posX, float posY, float velX, float velY, Level level) {
		particlePhysicsBox = new ParticlePhysicsBox(posX, posY, particleSize, particleSize, NBPBoxType.KINETIC);
		particlePhysicsBox.setVelx(velX);
		particlePhysicsBox.setVely(velY);
		level.getLevelWorld().getPhysicsWorld().addBox(particlePhysicsBox);
		this.camera = level.getLevelCamera();
	}

	@Override
	void update() {}

	@Override
	void draw(SpriteBatch batch) {
		explosionParticleSprite.setX(particlePhysicsBox.getX() + camera.getX()); // TOOD Add camera pos
		explosionParticleSprite.setY(particlePhysicsBox.getY() + camera.getY()); // TOOD Add camera pos
		// TODO Set scale based on life.
		// TODO Set opacity based on life.
		explosionParticleSprite.draw(batch);
	}

	@Override
	void onCreation() {
		// TODO Auto-generated method stub
	}

	@Override
	void onEnd() {
		// TODO Auto-generated method stub
	}
}
