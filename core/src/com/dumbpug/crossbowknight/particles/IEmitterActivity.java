package com.dumbpug.crossbowknight.particles;

/**
 * An interface which represents an activity to be carried out by the emitter.
 * @author nikolas.howard
 */
public interface IEmitterActivity {

	/**
	 * Do the activity
	 * @param emitter
	 */
	void act(Emitter emitter);
}
