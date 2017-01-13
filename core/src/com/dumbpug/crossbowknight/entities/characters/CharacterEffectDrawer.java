package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.burning.BurningParticleGenerator;

/**
 * Responsible for drawing any visible effects which are afflicting the character at the time. 
 * @author nikolas.howard
 */
public class CharacterEffectDrawer {
	/** The character. */
	private Character character;
	/** The burning effect */
	private Emitter burningParticleEmitter;
	/** The poisoned effect */
	/** The bleeding effect */
	
	/**
	 * Create a new instance of the CharacterEffectDrawer class.
	 * @param character
	 */
	CharacterEffectDrawer(Character character, LevelCamera camera) {
		// Create an burning emitter/particle generator.
    	BurningParticleGenerator burningParticleGenerator = new BurningParticleGenerator(camera, character.getPhysicsBox().getWidth(), character.getPhysicsBox().getHeight());
    	burningParticleEmitter = new Emitter(burningParticleGenerator, CrossbowKnight.getSpriteBatch());
    	// TODO Create an poisoned emitter/particle generator.
    	// TODO Create an bleeding emitter/particle generator.
	}
	
	/**
	 * Draw the visible effects which are afflicting the character at the time.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public void drawEffects(SpriteBatch batch, float offsetX, float offsetY) {
		// If the character is burning then draw a burning effect.
		if(character.getDamageEffects().isBurning()) {
			
		}
	}
}
