package com.dumbpug.crossbowknight.leveleditor;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.leveleditor.LevelEditorMenuButton.ButtonType;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * The level editor menu.
 * @author nikolas.howard
 */
public class LevelEditorMenu {
	/** The level editor textures */
	private LevelEditorTextures levelEditorTextures;
	/** The width/height of the menu area */
	private int menuSize = Gdx.graphics.getHeight();
	/** The list of menu buttons */
	private ArrayList<LevelEditorMenuButton> buttons;
	
	/**
	 * Create a new instance of LevelEditorMenu.
	 * @param textures
	 */
	public LevelEditorMenu(LevelEditorTextures textures) {
		this.levelEditorTextures = textures;
		buttons                  = new ArrayList<LevelEditorMenuButton>();
		
		// Make our menu buttons.
		Texture bgButtonTexture = levelEditorTextures.getBackgroundButtonTexture();
		buttons.add(new LevelEditorMenuButton(bgButtonTexture, ButtonType.BACKGROUND, 500, 450, 100, 50));
		
		Texture dcButtonTexture = levelEditorTextures.getDecorationButtonTexture();
		buttons.add(new LevelEditorMenuButton(dcButtonTexture, ButtonType.DECORATION, 500, 400, 100, 50));
		
		Texture blkButtonTexture = levelEditorTextures.getBlockButtonTexture();
		buttons.add(new LevelEditorMenuButton(blkButtonTexture, ButtonType.BLOCK, 500, 350, 100, 50));
		
		Texture clButtonTexture = levelEditorTextures.getClearButtonTexture();
		buttons.add(new LevelEditorMenuButton(clButtonTexture, ButtonType.CLEAR, 500, 0, 100, 50));
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
    	// Check buttons for a click.
    	for(LevelEditorMenuButton button :  buttons) {
    		if(button.isMouseClickOnButton(posX, posY)) {
    			// This button was clicked on. Switch on its type.
    			switch(button.getType()) {
					case BACKGROUND:
						break;
					case BLOCK:
						break;
					case CLEAR:
						break;
					case DECORATION:
						break;
					default:
						break;
    			}
    			
    			System.out.println("Clicked on: " + button.getType());
    		}
    	}
	}
	
	/**
	 * Draw the level editor menu.
	 * @param batch
	 */
    public void draw(SpriteBatch batch) {
    	// Draw the menu background.
    	batch.draw(levelEditorTextures.getMenuBackground(), menuSize, 0, menuSize, menuSize);
    	// Draw the menu buttons.
    	for(LevelEditorMenuButton button :  buttons) {
    		button.draw(batch);
    	}
    	
    	// ****
	}
}
