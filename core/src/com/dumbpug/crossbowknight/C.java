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
	public static final float PHYSICS_GRAVITY                   = 0.09f;

	/** Particles */
	public static final long PARTICLES_DEFAULT_LIFE             = 1500;
	public static final int PARTICLES_LIMIT                     = 50;
	
	/** Player */
	public static final float PLAYER_SIZE_WIDTH                 = 10f;
	public static final float PLAYER_SIZE_HEIGHT                = 15f;

	/** Character */
	public static final int CHARACTER_MAX_LEVEL                       = 100;
	public static final float CHARACTER_PHYSICS_MAX_WALKING_VELOCITY  = 1f;
	public static final float CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE = 0.2f;
	public static final float CHARACTER_JUMPING_IMPULSE               = 1.7f;
	public static final float CHARACTER_PHYSICS_MAX_VELOCITY          = 1.6f;
	public static final float CHARACTER_PHYSICS_FRICTION              = 0.75f;
	public static final float CHARACTER_PHYSICS_RESTITUTION           = 0f;
	
	/** Items */
	public static final float ITEM_BOX_SIZE                     = 6f;
	public static final float ITEM_BOX_FRICTION                 = 0.6f;
	public static final float ITEM_BOX_RESTITUTION              = 0.3f;
	public static final float ITEM_BOX_BOUNCE_SOUND_VELOCITY    = 0.2f;
	
	/** Inventory */
	public static final int INVENTORY_MAX_SLOT_QUANTITY         = 99;
	public static final int INVENTORY_MAX_SIZE                  = 24;
	public static final float INVENTORY_SLOT_SIZE               = Gdx.graphics.getHeight() / 10.5f;
	
	/** Equipment */
	public static final float EQUIPMENT_SLOT_SIZE               = INVENTORY_SLOT_SIZE * 1.6f;
	public static final float EQUIPMENT_SELECTION_VIEW_WIDTH    = EQUIPMENT_SLOT_SIZE * 2;
	public static final float EQUIPMENT_SELECTION_VIEW_HEIGHT   = EQUIPMENT_SELECTION_VIEW_WIDTH / 5;

	/** Projectiles */
	public static final long PROJECTILE_LAUNCH_IMMUNITY_THRESHOLD  = 500;
	public static final float PROJECTILE_BASE_FIRE_STAMINA_REQ     = 5f;
	public static final float PROJECTILE_BOLT_HEAD_SIZE            = 4f;
	public static final float PROJECTILE_BOLT_HITBOX_PADDING       = 0.5f;
	public static final int PROJECTILE_RUBBERBOLT_BOUNCES          = 3;
	public static final int PROJECTILE_EXPLOSIVEBOLT_BLAST_RADIUS  = 30;
	public static final int PROJECTILE_EXPLOSIVEBOLT_BLAST_FORCE   = 3;
	
	/** HUD */
	public static final int HUD_STATUS_BAR_HEIGHT               = Gdx.graphics.getHeight() / 8;
	public static final int HUD_STATUS_BAR_MARGIN               = HUD_STATUS_BAR_HEIGHT / 6;
	public static final int HUD_ITEM_BAR_HEIGHT                 = Gdx.graphics.getHeight() / 8;
	public static final int HUD_ITEM_BAR_WIDTH                  = (int) (HUD_ITEM_BAR_HEIGHT * 1.68);;
	public static final int HUD_ITEM_BAR_MARGIN                 = HUD_ITEM_BAR_HEIGHT / 7;
	public static final int HUD_SUB_STATUS_BAR_HEIGHT           = HUD_STATUS_BAR_HEIGHT / 4;
	public static final int HUD_SUB_STATUS_BAR_SECTION_WIDTH    = HUD_SUB_STATUS_BAR_HEIGHT / 4;
	
	/** In-Game Menu */
	public static final int INGAME_MENU_HEIGHT                  = Gdx.graphics.getHeight() / 2;
	public static final int INGAME_MENU_WIDTH                   = INGAME_MENU_HEIGHT * 2;
	public static final int INGAME_MENU_POS_X                   = (Gdx.graphics.getWidth() / 2) - (INGAME_MENU_WIDTH / 2);
	public static final int INGAME_MENU_POS_Y                   = (Gdx.graphics.getHeight() / 2) - (INGAME_MENU_HEIGHT / 2);
	public static final int MENU_INVENTORY_ITEM_SLOT_WIDTH      = 6;
	public static final int MENU_INVENTORY_ITEM_SLOT_HEIGHT     = 4;
	public static final float MENU_INVENTORY_ITEM_SLOT_SIZE     = INGAME_MENU_HEIGHT * 0.15f;
	public static final int MENU_EQUIPMENT_ITEM_SLOT_WIDTH      = 5;
	public static final int MENU_EQUIPMENT_ITEM_SLOT_HEIGHT     = 2;
	public static final float MENU_CHARACTER_XP_BAR_HEIGHT      = INGAME_MENU_HEIGHT * 0.2f;
	public static final float MENU_CHARACTER_ATTRIBUTE_HEIGHT   = INGAME_MENU_HEIGHT * 0.08f;

	/** Font */
	public static final int FONT_SIZE_XXSMALL                   = (int) (2 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_XSMALL                    = (int) (3 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_SMALL                     = (int) (4 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_MEDIUM                    = (int) (8 * LAYOUT_MULTIPLIER);
	public static final int FONT_SIZE_LARGE                     = (int) (12 * LAYOUT_MULTIPLIER);
	
	/** Stats */
	public static final int STATS_ATTRIBUTE_LEVEL_MODIFIER      = 2;
	public static final int STATS_LEVEL_XP_MODIFIER             = 10;
	public static final int STATS_BASE_HEALTH                   = 50;
	public static final int STATS_BASE_STAMINA                  = 50;
	public static final long STATS_BASE_STAMINA_REFILL_RATE     = 1000l;
	
	/** Procedural Generation */
	public static final int PROC_GEN_TOKENS_RARITY_COMMON       = 65;
	public static final int PROC_GEN_TOKENS_RARITY_UNCOMMON     = 30;
	public static final int PROC_GEN_TOKENS_RARITY_RARE         = 15;
	public static final int PROC_GEN_TOKENS_RARITY_ULTRA_RARE   = 8;
	public static final int PROC_GEN_TOKENS_RARITY_MYTHICAL     = 3;
	public static final int PROC_GEN_TOKENS_MATERIAL_WOOD       = 30;
	public static final int PROC_GEN_TOKENS_MATERIAL_IRON       = 20;
	public static final int PROC_GEN_TOKENS_MATERIAL_STEEL      = 15;
	public static final int PROC_GEN_TOKENS_MATERIAL_GLASS      = 10;
	public static final int PROC_GEN_TOKENS_MATERIAL_CRYSTAL    = 6;
	public static final int PROC_GEN_TOKENS_MATERIAL_ONYX       = 3;
	public static final int PROC_GEN_TOKENS_MATERIAL_MYTHRIL    = 1;
	public static final int PROC_GEN_TOKENS_ITEM_NOTHING        = 60;
	public static final int PROC_GEN_TOKENS_ITEM_CONSUMABLE     = 50;
	public static final int PROC_GEN_TOKENS_ITEM_AMMO           = 55;
	public static final int PROC_GEN_TOKENS_ITEM_HELMET         = 12;
	public static final int PROC_GEN_TOKENS_ITEM_SHIELD         = 12;
	public static final int PROC_GEN_TOKENS_ITEM_STOCK          = 10;
	public static final int PROC_GEN_TOKENS_ITEM_STRING         = 10;
	public static final int PROC_GEN_TOKENS_ITEM_SIGHT          = 5;
	public static final int PROC_GEN_TOKENS_ITEM_LIMBS          = 5;
}
