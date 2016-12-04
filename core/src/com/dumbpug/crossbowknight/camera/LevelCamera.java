package com.dumbpug.crossbowknight.camera;

/**
 * Represents our level camera.
 * @author nikolas.howard
 */
public class LevelCamera {
	/** The position provider for this camera */
	private ICameraPositionProvider positonProvider;
	
	/**
	 * Creates a new instance of the LevelCamera class.
	 * @param levelCameraPositionProvider
	 */
	public LevelCamera(ICameraPositionProvider levelCameraPositionProvider) {
		this.positonProvider = levelCameraPositionProvider;
	}
	
	/**
	 * Get x position of this camera.
	 * @return x
	 */
	public float getX() {
		return positonProvider.getXPositon();
	}
	
	/**
	 * Get y position of this camera.
	 * @return y
	 */
	public float getY() {
		return positonProvider.getYPositon();
	}
}
