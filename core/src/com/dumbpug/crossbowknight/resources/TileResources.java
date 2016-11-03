package com.dumbpug.crossbowknight.resources;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;

/**
 * Holds tile textures.
 * @author nikolas.howard
 */
public class TileResources {
	/** Singleton instance of tile textures. */
	private static TileResources instance;
	
	/** Maps which hold all tile textures. */
	private HashMap<BackgroundTile, IndexedTileTexture> backgroundTileTextures = new HashMap<BackgroundTile, IndexedTileTexture>();
	private HashMap<DecorationTile, IndexedTileTexture> decorationTileTextures = new HashMap<DecorationTile, IndexedTileTexture>();
	private HashMap<EntityTile, IndexedTileTexture> entityTileTextures         = new HashMap<EntityTile, IndexedTileTexture>();
	private HashMap<BlockTile, IndexedTileTexture> blockTileTextures           = new HashMap<BlockTile, IndexedTileTexture>();
	private HashMap<MiscTile, IndexedTileTexture> miscTileTextures             = new HashMap<MiscTile, IndexedTileTexture>();
	
	/**
	 * Tiles that represent part of the background.
	 */
	public enum BackgroundTile {
		BG_1,
		BG_2,
		BG_3,
		BG_4,
		BG_5,
		BG_6,
		BG_7
	}
	
	/**
	 * Tiles that represents non-interactive entities.
	 */
	public enum DecorationTile {
		CANDLE_ARCH,
		DISPLAY_SWORDS_1,
		DISPLAY_SWORDS_2,
		WALL_SHIELD,
		WALL_TORCH,
		WINDOW_DARK_SHORT,
		WINDOW_DARK_TOP,
		WINDOW_DARK_BOTTOM,
		WINDOW_LIGHT_SHORT,
		WINDOW_LIGHT_TOP,
		WINDOW_LIGHT_BOTTOM,
		BIGWINDOW_BOTTOM_LEFT_1,
		BIGWINDOW_BOTTOM_LEFT_2,
		BIGWINDOW_BOTTOM_RIGHT_1,
		BIGWINDOW_BOTTOM_RIGHT_2,
		BIGWINDOW_TOP_LEFT,
		BIGWINDOW_TOP_RIGHT,
		TAPESTRY_RED_TOP,
		TAPESTRY_RED_BOTTOM,
		TAPESTRY_BLUE_TOP,
		TAPESTRY_BLUE_BOTTOM,
		PILLAR_TOP,
		PILLAR_MID,
		PILLAR_BOTTOM,
		WIDE_PILLAR_TOP,
		WIDE_PILLAR_BOTTOM,
		WIDE_PILLAR_MID,
		WIDE_PILLAR_MID_BROKEN_1,
		WIDE_PILLAR_MID_BROKEN_2,
		CHAIN_TOP,
		CHAIN_MIDDLE,
		CHAIN_BOTTOM,
		CANDELABRA_TOP,
		CANDELABRA_BOTTOM,
		BOOKCASE_EMPTY_LEFT,
		BOOKCASE_EMPTY_RIGHT,
		BOOKCASE_MIDDLE_LEFT,
		BOOKCASE_MIDDLE_RIGHT,
		BOOKCASE_FULL_LEFT,
		BOOKCASE_FULL_RIGHT,
		BOOKCASE_SHORT_EMPTY,
		BOOKCASE_SHORT_FULL,
		STAIR_LEFT,
		STAIR_RIGHT,
		SKY_1_EMPTY,
		SKY_1_RAMPARTS,
		SKY_2_EMPTY,
		SKY_3_EMPTY,
		SKY_4_EMPTY,
		SKY_5_EMPTY,
		SKY_6_EMPTY,
		SKY_6_STARS_1,
		SKY_6_STARS_2,
		SKY_7_EMPTY,
		SKY_7_HALFMOON_LEFT,
		SKY_7_HALFMOON_RIGHT,
		SKY_7_MOON_LEFT,
		SKY_7_MOON_RIGHT,
		SKY_7_STARS_1,
		SKY_7_STARS_2,
		SKY_8_EMPTY,
		SKY_8_HALFMOON_LEFT,
		SKY_8_HALFMOON_RIGHT,
		SKY_8_MOON_LEFT,
		SKY_8_MOON_RIGHT,
		SKY_8_STARS_1,
		SKY_8_STARS_2
	}
	
	/**
	 * Tiles that represents interactive entities.
	 */
	public enum EntityTile {
		DOOR_1,
		DOOR_2,
		DOOR_3,
		DOOR_HOME,
		DOOR_OPEN,
		DOOR_SHOP,
		LADDER_TOP,
		LADDER_MID,
		LADDER_BOTTOM,
		CHEST_RED_OPEN,
		CHEST_RED_CLOSED
	}
	
	/**
	 * Tiles that represents part of a physical block.
	 */
	public enum BlockTile {
		FG_STONE_1,
		FG_STONE_2,
		FG_STONE_3,
		FG_STONE_4,
		FG_STONE_5,
		FG_STONE_6,
		FG_STONE_7,
		FG_STONE_LEFT,
		FG_STONE_RIGHT,
		FG_STONE_HALF_HORIZONTAL,
		FG_STONE_HALF_VERTICAL,
		FG_WOOD_SHELF_LEFT,
		FG_WOOD_SHELF_MIDDLE,
		FG_WOOD_SHELF_RIGHT,
		FG_WOOD_SHELF_LEFT_HALF,
		FG_WOOD_SHELF_MIDDLE_HALF,
		FG_WOOD_SHELF_RIGHT_HALF
	}
	
	/**
	 * Misc textures used when drawing tiles.
	 */
	public enum MiscTile {
		FG_BASESHADOW,
		FLOOR
	}
	
	/**
	 * Creates an instance of the TileTextures class.
	 */
	private TileResources() {
		// Populate our background tile indexed texture map.
		for(BackgroundTile tile : BackgroundTile.values()) {
			String tileTexturePath = C.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			backgroundTileTextures.put(tile, new IndexedTileTexture(new Texture(tileTexturePath), tile.ordinal()));
		}
		// Populate our decoration tile indexed texture map.
		for(DecorationTile tile : DecorationTile.values()) {
			String tileTexturePath = C.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			decorationTileTextures.put(tile, new IndexedTileTexture(new Texture(tileTexturePath), tile.ordinal()));
		}
		// Populate our entity tile indexed texture map.
		for(EntityTile tile : EntityTile.values()) {
			String tileTexturePath = C.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			entityTileTextures.put(tile, new IndexedTileTexture(new Texture(tileTexturePath), tile.ordinal()));
		}
		// Populate our block tile indexed texture map.
		for(BlockTile tile : BlockTile.values()) {
			String tileTexturePath = C.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			blockTileTextures.put(tile, new IndexedTileTexture(new Texture(tileTexturePath), tile.ordinal()));
		}
		// Populate our misc tile indexed texture map.
		for(MiscTile tile : MiscTile.values()) {
			String tileTexturePath = C.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			miscTileTextures.put(tile, new IndexedTileTexture(new Texture(tileTexturePath), tile.ordinal()));
		}
	}
	
	/**
	 * Get our TileTextures singleton instance.
	 * @return TileTextures
	 */
	public static TileResources getTileTextures() {
		if(instance == null) {
			instance = new TileResources();
		}
		return instance;
	}
	
	/**
	 * Get Background tile texture.
	 * @param background
	 * @return tile texture
	 */
	public IndexedTileTexture getBackgroundTileTexture(BackgroundTile background) {
		return this.backgroundTileTextures.get(background);
	}
	
	/**
	 * Get Decoration tile texture.
	 * @param decoration
	 * @return tile texture
	 */
	public IndexedTileTexture getDecorationTileTexture(DecorationTile decoration) {
		return this.decorationTileTextures.get(decoration);
	}
	
	/**
	 * Get Entity tile texture.
	 * @param entitiyTile
	 * @return tile texture
	 */
	public IndexedTileTexture getEntityTileTexture(EntityTile entitiyTile) {
		return this.entityTileTextures.get(entitiyTile);
	}
	
	/**
	 * Get Block tile texture.
	 * @param block
	 * @return tile texture
	 */
	public IndexedTileTexture getBlockTileTexture(BlockTile block) {
		return this.blockTileTextures.get(block);
	}
	
	/**
	 * Get misc tile texture.
	 * @param miscTile
	 * @return tile texture
	 */
	public IndexedTileTexture getMiscTileTexture(MiscTile miscTile) {
		return this.miscTileTextures.get(miscTile);
	}
}
