package com.dumbpug.crossbowknight.leveleditor.menu;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.leveleditor.C;
import com.dumbpug.crossbowknight.leveleditor.menu.MenuButton.ButtonType;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;
import com.dumbpug.crossbowknight.tiles.TileTextures;

public class BackgroundMenu {
	/** The list of background tile buttons */
	private ArrayList<TileMenuButton> backgroundTileButtons = new ArrayList<TileMenuButton>();
	
	/**
	 * Create a new instance of the BackgroundMenu class.
	 */
	public BackgroundMenu() {
		populateTileButtonList();
	}
	
	/**
	 * Populate our list of background tile buttons.
	 */
	private void populateTileButtonList() {
		int tilePosX  = 600;
		int tilePosY  = Gdx.graphics.getHeight() - C.MENU_TILE_SIZE;
		int tileIndex = 0;
		
		for(int x = 0; x < C.MENU_TILE_X_LIMIT; x++) {
			for(int y = 0; y < C.MENU_TILE_Y_LIMIT; y++) {
				// Have we reached the limit of the background tiles?
				if(tileIndex >= TileTextures.BackgroundTile.values().length) {
					return;
				}
				// Get the indexed texture of the current tile.
				IndexedTileTexture texture = TileTextures.getTileTextures().getBackgroundTileTexture(TileTextures.BackgroundTile.values()[tileIndex]);
				// Calculate the position of this tile.
				int posX = tilePosX + (x*C.MENU_TILE_SIZE);
				int posY = tilePosY - (y*C.MENU_TILE_SIZE);
				// Create the tile button.
				TileMenuButton button = new TileMenuButton(texture, ButtonType.TILE, posX, posY, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE);
				backgroundTileButtons.add(button);
				tileIndex++;
			}
		}
	}
	
	/**
	 * Called on mouse click.
	 * @param posX relative to menu 
	 * @param posY relative to menu
	 */
    public void onMouseClick(int posX , int posY) {
    	
    }
    
    /**
	 * Draw this menu.
	 * @param batch
	 */
    public void draw(SpriteBatch batch) {
    	// Draw the menu buttons.
    	for(TileMenuButton tileButton : backgroundTileButtons) {
    		tileButton.draw(batch);
    	}
    	
    	// ****
	}
}
