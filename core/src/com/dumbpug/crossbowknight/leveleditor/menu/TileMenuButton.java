package com.dumbpug.crossbowknight.leveleditor.menu;

import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;

/**
 * A menu button which hold an indexed tile texture.
 * @author nikolas.howard
 */
public class TileMenuButton extends MenuButton {
	/** The tile texture. */
	private IndexedTileTexture texture;
	
	/**
	 * Create a new instance of the TileMenuButton class.
	 * @param texture
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public TileMenuButton(IndexedTileTexture texture, ButtonType type, int x, int y, int width, int height) {
		super(texture.getTexture(), type, x, y, width, height);
		this.texture = texture;
	}
	
	/**
	 * Get the indexed tile texture.
	 * @return indexed tile texture.
	 */
	public IndexedTileTexture getIndexedTileTexture() {
		return this.texture;
	}
}
