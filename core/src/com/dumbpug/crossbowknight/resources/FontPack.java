package com.dumbpug.crossbowknight.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * The games font pack.
 * @author Nikolas Howard
 */
public class FontPack {
	/** Singleton instance of FontPack. */
	private static FontPack instance;
	/** The games main font generator. */
	private static FreeTypeFontGenerator mainFontGenerator;
	
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
		mainFontGenerator =  new FreeTypeFontGenerator(Gdx.files.internal("fonts/3Dventure.ttf"));
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
	 * @param fontParameter
	 * @return font
	 */
	public BitmapFont getFont(FontType type, FreeTypeFontParameter fontParameter) {
		switch(type) {
			case MAIN_FONT:
				return mainFontGenerator.generateFont(fontParameter);
		}
		return null;
	}
}