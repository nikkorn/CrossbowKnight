package com.dumbpug.crossbowknight;

import com.badlogic.gdx.Gdx;

/**
 * Game Constants.
 * @author nikolas.howard
 */
public class C {
	/** Layout */
	public static final float LAYOUT_MULTIPLIER              = Gdx.graphics.getHeight() / C.LAYOUT_SCREEN_HEIGHT;
	public static final float LAYOUT_SCREEN_HEIGHT           = 100f;
	public static final float LAYOUT_TILE_SIZE               = 20f;
	
	/** Resources */
	public static final String GRAPHICS_WORLD_TILES_DIR      = "graphics/tile_images";
	
	/** Saved State */
	public static final String SAVED_LEVELS_DIR              = "levels/";
	
	/** Physics */
	public static final float PHYSICS_TO_GAME_RATIO          = 0.2f; //TODO  figure out
	public static final float PHYSICS_GRAVITY                = 0.9f; 
	
	/** Player */
	public static final float PLAYER_SIZE_WIDTH              = 20f;
	public static final float PLAYER_SIZE_HEIGHT             = 30f;
	public static final float PLAYER_MAX_VELOCITY            = 5f;
	public static final float PLAYER_FRICTION                = 0.92f;
	public static final float PLAYER_RESTITUTION             = 0f;
	public static final float PLAYER_MAX_WALKING_VELOCITY    = 2f;
	public static final float PLAYER_WALKING_IMPULSE_VALUE   = 0.2f;
	public static final float PLAYER_JUMPING_IMPULSE         = 3.5f;
}
