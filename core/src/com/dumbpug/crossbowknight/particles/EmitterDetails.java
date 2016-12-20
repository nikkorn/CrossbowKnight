package com.dumbpug.crossbowknight.particles;

/**
 * Concrete instance of IEmitterDetails.
 * @author nikolas.howard
 */
public class EmitterDetails implements IEmitterDetails {
	public float positionX = 0;
	public float positionY = 0;
	
	@Override
	public float positionX() { return positionX; }
	
	@Override
	public float positionY() { return positionY; }
}
