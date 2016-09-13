package com.dumbpug.crossbowknight.tiles;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.crossbowknight.Constants;

/**
 * Holds tile textures.
 * @author nikolas.howard
 */
public class TileTextures {
	/** Singleton instance of tile textures. */
	private static TileTextures instance;
	
	/** Maps which hold all tile textures. */
	private HashMap<BackgroundTile, Texture> backgroundTileTextures = new HashMap<BackgroundTile, Texture>();
	private HashMap<DecorationTile, Texture> decorationTileTextures = new HashMap<DecorationTile, Texture>();
	private HashMap<EntityTile, Texture> entityTileTextures         = new HashMap<EntityTile, Texture>();
	private HashMap<BlockTile, Texture> blockTileTextures           = new HashMap<BlockTile, Texture>();
	private HashMap<MiscTile, Texture> miscTileTextures             = new HashMap<MiscTile, Texture>();
	
	/**
	 * Tiles that represent part of the background.
	 */
	public enum BackgroundTile {
		BG_1,
		BG_2,
		BG_3,
		BG_4,
		BG_5,
		BG_6
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
		TAPESTRY_RED_TOP,
		TAPESTRY_RED_BOTTOM,
		TAPESTRY_BLUE_TOP,
		TAPESTRY_BLUE_BOTTOM,
		PILLAR_TOP,
		PILLAR_MID,
		PILLAR_BOTTOM
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
		FG_STONE_LEFT_CAP,
		FG_STONE_RIGHT_CAP
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
	private TileTextures() {
		// Populate our background tile texture map.
		for(BackgroundTile tile : BackgroundTile.values()) {
			String tileTexturePath = Constants.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			backgroundTileTextures.put(tile, new Texture(tileTexturePath));
		}
		// Populate our decoration tile texture map.
		for(DecorationTile tile : DecorationTile.values()) {
			String tileTexturePath = Constants.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			decorationTileTextures.put(tile, new Texture(tileTexturePath));
		}
		// Populate our entity tile texture map.
		for(EntityTile tile : EntityTile.values()) {
			String tileTexturePath = Constants.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			entityTileTextures.put(tile, new Texture(tileTexturePath));
		}
		// Populate our block tile texture map.
		for(BlockTile tile : BlockTile.values()) {
			String tileTexturePath = Constants.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			blockTileTextures.put(tile, new Texture(tileTexturePath));
		}
		// Populate our misc tile texture map.
		for(MiscTile tile : MiscTile.values()) {
			String tileTexturePath = Constants.GRAPHICS_WORLD_TILES_DIR + "/TILE_" + tile + ".png";
			miscTileTextures.put(tile, new Texture(tileTexturePath));
		}
	}
	
	/**
	 * Get our TileTextures singleton instance.
	 * @return TileTextures
	 */
	public static TileTextures getTileTextures() {
		if(instance == null) {
			instance = new TileTextures();
		}
		return instance;
	}
	
	/**
	 * Get Background tile texture.
	 * @param texture
	 * @return tile texture
	 */
	public Texture getBackgroundTileTexture(BackgroundTile texture) {
		return this.backgroundTileTextures.get(texture);
	}
	
	/**
	 * Get Decoration tile texture.
	 * @param texture
	 * @return tile texture
	 */
	public Texture getDecorationTileTexture(DecorationTile texture) {
		return this.decorationTileTextures.get(texture);
	}
	
	/**
	 * Get Entity tile texture.
	 * @param texture
	 * @return tile texture
	 */
	public Texture getEntityTileTexture(EntityTile texture) {
		return this.entityTileTextures.get(texture);
	}
	
	/**
	 * Get Block tile texture.
	 * @param texture
	 * @return tile texture
	 */
	public Texture getBlockTileTexture(BlockTile texture) {
		return this.blockTileTextures.get(texture);
	}
	
	/**
	 * Get misc tile texture.
	 * @param texture
	 * @return tile texture
	 */
	public Texture getMiscTileTexture(MiscTile texture) {
		return this.miscTileTextures.get(texture);
	}
}
