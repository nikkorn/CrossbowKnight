package com.dumbpug.crossbowknight.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * The games font pack.
 * @author Nikolas Howard
 */
public class FontPack {
	/** Singleton instance of FontPack. */
	private static FontPack instance;
	/** The games main font. */
	private static BitmapFont MAIN_FONT;
	
	/**
	 * The games font types.
	 * @author nikolas.howard
	 */
	public enum FontType {
		MAIN_FONT
	}
	
	/**
	 * Create a new instance of the FontPack class.
	 */
	private FontPack() {
		MAIN_FONT = new BitmapFont(Gdx.files.internal("fonts/main.fnt"), Gdx.files.internal("fonts/main.png"), false);
	}
	
	/**
	 * Get our FontPack singleton instance.
	 * @return FontPack
	 */
	public static FontPack getFontPack() {
		if(instance == null) {
			instance = new FontPack();
		}
		return instance;
	}
	
	/**
	 * Get a game font.
	 * @param type
	 * @return font
	 */
	public BitmapFont getFont(FontType type) {
		switch(type) {
			case MAIN_FONT:
				return MAIN_FONT;
		}
		return null;
	}
}