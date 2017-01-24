package com.dumbpug.crossbowknight.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.particles.Emitter;
import com.dumbpug.crossbowknight.particles.EmitterPool;
import com.dumbpug.crossbowknight.particles.IEmitterActivity;
import com.dumbpug.crossbowknight.particles.IEmitterDetails;
import com.dumbpug.crossbowknight.particles.burning.BurningParticleGenerator;
import com.dumbpug.crossbowknight.particles.poisoning.PoisonParticleGenerator;

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
	private Emitter poisonParticleEmitter;
	/** The bleeding effect */
	
	/** The emitter pool for character effect emitters. */
	private EmitterPool characterEffectEmitterPool = new EmitterPool();
	
	/**
	 * Create a new instance of the CharacterEffectDrawer class.
	 * @param character
	 */
	public CharacterEffectDrawer(final Character character, LevelCamera camera) {
		this.character = character;
		// Create an burning emitter/particle generator.
    	final BurningParticleGenerator burningParticleGenerator = new BurningParticleGenerator(camera, character.getPhysicsBox().getWidth()*0.5f, character.getPhysicsBox().getHeight()*0.5f);
    	burningParticleEmitter = new Emitter(burningParticleGenerator, CrossbowKnight.getSpriteBatch());
    	setUpEmitter(burningParticleEmitter, new IEmitterActivity() {
			@Override
			public void act(Emitter emitter) {
				// Do the actual emitter activity.
				burningParticleGenerator.act(emitter);
				// If the character is no longer burning, stop the emitter activity.
				if(!character.getDamageEffects().isBurning()) {
					emitter.finishActivity();
					emitter.setAlive(false);
				}
			}
    	});
    	
    	// Create an poisoned emitter/particle generator.
    	final PoisonParticleGenerator poisonParticleGenerator = new PoisonParticleGenerator(camera, character.getPhysicsBox().getWidth()*0.5f, character.getPhysicsBox().getHeight()*0.5f);
    	poisonParticleEmitter = new Emitter(poisonParticleGenerator, CrossbowKnight.getSpriteBatch());
    	setUpEmitter(poisonParticleEmitter, new IEmitterActivity() {
			@Override
			public void act(Emitter emitter) {
				// Do the actual emitter activity.
				poisonParticleGenerator.act(emitter);
				// If the character is no longer burning, stop the emitter activity.
				if(!character.getDamageEffects().isPoisoned()) {
					emitter.finishActivity();
					emitter.setAlive(false);
				}
			}
    	});
    	
    	// TODO Create an bleeding emitter/particle generator.
	}
	
	/**
	 * Set up an emitter 
	 * @param emitter
	 * @param emitterActivity
	 */
	private void setUpEmitter(Emitter emitter, IEmitterActivity emitterActivity) {
		final CharacterPhysicsBox<?> characterPhysicsBox = this.character.getPhysicsBox();
		// Set the emitter position to always match the characters position.
		emitter.setEmitterDetails(new IEmitterDetails() {
			@Override
			public float positionX() { return characterPhysicsBox.getX() + (characterPhysicsBox.getWidth()/2); }
			@Override
			public float positionY() { return characterPhysicsBox.getY() + (characterPhysicsBox.getHeight()/2);}
    	});
    	// Set the emitter activity.
		emitter.setEmitterActivity(emitterActivity);
	}
	
	/**
	 * Update the effect emitter pool.
	 */
	public void updateEffectEmitters() { this.characterEffectEmitterPool.update(); }
	
	/**
	 * Draw the visible effects which are afflicting the character at the time.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public void drawEffects(SpriteBatch batch, float offsetX, float offsetY) {
		// Check to make sure that we have effect emitters in our pool if we need them.
		// Are we burning?
		if(character.getDamageEffects().isBurning() && !characterEffectEmitterPool.contains(burningParticleEmitter)) {
			burningParticleEmitter.reset();
			characterEffectEmitterPool.add(burningParticleEmitter);
		}
		// Are we poisoned?
		if(character.getDamageEffects().isPoisoned() && !characterEffectEmitterPool.contains(poisonParticleEmitter)) {
 			poisonParticleEmitter.reset();
			characterEffectEmitterPool.add(poisonParticleEmitter);
		}
		
		// ...
		
		// Draw our particle effects.
		characterEffectEmitterPool.draw();
	}
}
