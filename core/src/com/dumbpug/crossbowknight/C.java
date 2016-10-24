package com.dumbpug.crossbowknight;

import com.badlogic.gdx.Gdx;

/**
 * Game Constants.
 * @author nikolas.howard
 */
public class C {
	/** Main Menu */
	public static final int MAIN_MENU_ITEM_PADDING              = Gdx.graphics.getHeight() / 16;
	
	/** Layout */
	public static final float LAYOUT_MULTIPLIER                 = Gdx.graphics.getHeight() / C.LAYOUT_SCREEN_HEIGHT;
	public static final float LAYOUT_SCREEN_HEIGHT              = 100f;
	public static final float LAYOUT_TILE_SIZE                  = 20f;

	/** Dialog/Font */
	public static final float DIALOG_HEIGHT                     = Gdx.graphics.getHeight() / 5f;
	public static final float DIALOG_BORDER_SIZE                = DIALOG_HEIGHT / 5f;

	/** Resources */
	public static final String GRAPHICS_WORLD_TILES_DIR         = "graphics/tile_images";
	public static final String AUDIO_SOUND_EFFECTS_DIR          = "audio/sounds";
	public static final String AUDIO_MUSIC_DIR                  = "audio/music";

	/** Saved State */
	public static final String SAVED_LEVELS_DIR                 = "levels/";

	/** Physics */
	public static final float PHYSICS_TO_GAME_RATIO             = 0.2f; //TODO  figure out
	public static final float PHYSICS_GRAVITY                   = 0.09f;
	
	/** Player */
	public static final float PLAYER_SIZE_WIDTH                 = 10f;
	public static final float PLAYER_SIZE_HEIGHT                = 15f;
	public static final float PLAYER_MAX_VELOCITY               = 1.6f;
	public static final float PLAYER_FRICTION                   = 0.75f;
	public static final float PLAYER_RESTITUTION                = 0f;
	public static final float PLAYER_MAX_WALKING_VELOCITY       = 1f;
	public static final float PLAYER_WALKING_IMPULSE_VALUE      = 0.2f;
	public static final float PLAYER_JUMPING_IMPULSE            = 1.7f;

	/** Character */
	public static final int CHARACTER_MAX_LEVEL                 = 100;
	
	/** Items */
	public static final float ITEM_BOX_SIZE                     = 10f;
	public static final float ITEM_BOX_FRICTION                 = 0.6f;
	public static final float ITEM_BOX_RESTITUTION              = 0.3f;
	public static final float ITEM_BOX_BOUNCE_SOUND_VELOCITY    = 1f;
	
	/** Inventory */
	public static final int INVENTORY_MAX_SLOT_QUANTITY         = 99;
	public static final int INVENTORY_MAX_SIZE                  = 24;
	public static final float INVENTORY_SLOT_SIZE               = Gdx.graphics.getHeight() / 10.5f;

	/** Projectiles */
	public static final float PROJECTILE_BASE_BOLT_HEAD_SIZE    = 4f;
	public static final float PROJECTILE_BOLT_HITBOX_PADDING    = 0.5f;

	/** HUD */
	public static final int HUD_STATUS_BAR_HEIGHT               = Gdx.graphics.getHeight() / 8;
	public static final int HUD_STATUS_BAR_MARGIN               = HUD_STATUS_BAR_HEIGHT / 6;
	public static final int HUD_AMMO_BAR_HEIGHT                 = Gdx.graphics.getHeight() / 9;
	public static final int HUD_AMMO_BAR_WIDTH                  = (int) (C.HUD_AMMO_BAR_HEIGHT * 1.68);
	public static final int HUD_AMMO_BAR_MARGIN                 = HUD_AMMO_BAR_HEIGHT / 6;
	
	/** In-Game Menu */
	public static final int INGAME_MENU_HEIGHT                  = Gdx.graphics.getHeight() / 2;
	public static final int INGAME_MENU_WIDTH                   = INGAME_MENU_HEIGHT * 2;
	public static final int INGAME_MENU_POS_X                   = (Gdx.graphics.getWidth() / 2) - (INGAME_MENU_WIDTH / 2);
	public static final int INGAME_MENU_POS_Y                   = (Gdx.graphics.getHeight() / 2) - (INGAME_MENU_HEIGHT / 2);
	public static final int MENU_INVENTORY_ITEM_SLOT_WIDTH      = 6;
	public static final int MENU_INVENTORY_ITEM_SLOT_HEIGHT     = 4;
	public static final float MENU_INVENTORY_ITEM_SLOT_SIZE     = INGAME_MENU_HEIGHT * 0.15f;

	/** Font */
	public static final int FONT_SIZE_XSMALL                    = (int) (2 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_SMALL                     = (int) (4 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_MEDIUM                    = (int) (8 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_LARGE                     = (int) (12 * LAYOUT_MULTIPLIER);
	
	/** Stats */
	public static final int STATS_ATTRIBUTE_LEVEL_MODIFIER      = 5;
	public static final int STATS_LEVEL_XP_MODIFIER             = 10;
}
