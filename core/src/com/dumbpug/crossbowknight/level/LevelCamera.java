package com.dumbpug.crossbowknight.level;

/**
 * Represents our level camera.
 * @author nikolas.howard
 */
public class LevelCamera {
	/** The position provider for this camera */
	private LevelCameraPositionProvider positonProvider;
	
	/**
	 * Creates a new instance of the LevelCamera class.
	 * @param levelCameraPositionProvider
	 */
	public LevelCamera(LevelCameraPositionProvider levelCameraPositionProvider) {
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
