package com.dumbpug.crossbowknight.particles;

/**
 * Provides details of an Emitter passed when generating a new particle
 * @author nikolas.howard
 */
public interface IEmitterDetails {
	
	/**
	 * Get the X position of an emitter.
	 * @return x position
	 */
	float positionX();
	
	/**
	 * Get the Y position of an emitter.
	 * @return y position
	 */
	float positionY();
}
