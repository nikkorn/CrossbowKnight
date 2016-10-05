package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.tiles.Tile;

public class LevelEditorMenu {
	/** The level editor textures */
	private LevelEditorTextures levelEditorTextures;
	/** The width/height of the menu area */
	private int menuSize = Gdx.graphics.getHeight();
	
	public LevelEditorMenu(LevelEditorTextures textures) {
		this.levelEditorTextures = textures;
	}
	
	/**
	 * Called on tile selection.
	 * @param batch
	 */
    public void onTileSelect(Tile selectedTile) {
		System.out.println("tile selected");
	}
    
    /**
	 * Called on mouse click.
	 * @param posX relative to menu 
	 * @param posY relative to menu
	 */
    public void onMouseClick(int posX , int posY) {
    	System.out.println("menu mouse click");
	}
	
	/**
	 * Draw the level editor menu.
	 * @param batch
	 */
    public void draw(SpriteBatch batch) {
    	// Draw the menu background.
    	batch.draw(levelEditorTextures.getMenuBackground(), menuSize, 0, menuSize, menuSize);
		
    	// ****
	}
}
