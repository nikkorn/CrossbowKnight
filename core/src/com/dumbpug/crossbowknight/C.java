package com.dumbpug.crossbowknight;

import com.badlogic.gdx.Gdx;

/**
 * Game Constants.
 * @author nikolas.howard
 */
public class C {
	/** Layout */
	public static final float LAYOUT_TILE_SIZE              = Gdx.graphics.getHeight() * 0.2f;
	
	/** Resources */
	public static final String GRAPHICS_WORLD_TILES_DIR     = "graphics/tile_images";
	
	/** Saved State */
	public static final String SAVED_LEVELS_DIR             = "levels/";
	
	/** Physics */
	public static final float PHYSICS_TO_GAME_RATIO         = 0.2f; //TODO  figure out
	public static final float PHYSICS_GRAVITY               = 0.9f; 
}
