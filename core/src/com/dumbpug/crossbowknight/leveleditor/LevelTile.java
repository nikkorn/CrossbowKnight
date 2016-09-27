package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.graphics.Texture;

public class LevelTile {
	/** The position */
	public int X = 0;
	public int Y = 0;
	/** The background texture for this tile */
	public Texture backgroundTexture = null;
	/** The decoration texture for this tile */
	public Texture decorationTexture = null;
	/** The block texture for this tile */
	public LevelBlock levelBlock     = null;

	/**
	 * Returns true if this tile has nothing on it.
	 * @return is blank
     */
	public boolean isBlank() {
		return (backgroundTexture == null && decorationTexture == null && levelBlock == null);
	}
}
