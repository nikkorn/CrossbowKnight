package com.dumbpug.crossbowknight.leveleditor.menu;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.leveleditor.LevelEditorTextures;
import com.dumbpug.crossbowknight.leveleditor.menu.button.MenuButton;
import com.dumbpug.crossbowknight.leveleditor.menu.button.MenuButton.ButtonType;
import com.dumbpug.crossbowknight.tiles.Tile;

/**
 * The level editor menu.
 * @author nikolas.howard
 */
public class MainMenu {
	/** The level editor textures */
	private LevelEditorTextures levelEditorTextures;
	/** The width/height of the menu area */
	private int menuSize = Gdx.graphics.getHeight();
	/** The list of menu buttons */
	private ArrayList<MenuButton> buttons;
	/** The active sub menu button */
	private ButtonType activeSubMenuButtonType = ButtonType.BACKGROUND;
	/** The active tile. */
	private Tile activeTile = null;
	/** The sub menus. */
	private BackgroundMenu backgroundMenu;
	private DecorationMenu decorationMenu;
	private BlockMenu blockMenu;
	
	/**
	 * Create a new instance of LevelEditorMenu.
	 * @param textures
	 */
	public MainMenu(LevelEditorTextures textures) {
		this.levelEditorTextures = textures;
		buttons                  = new ArrayList<MenuButton>();
		
		// Make our menu buttons.
		Texture bgButtonTexture = levelEditorTextures.getBackgroundButtonTexture();
		buttons.add(new MenuButton(bgButtonTexture, ButtonType.BACKGROUND, 500, 450, 100, 50));
		
		Texture dcButtonTexture = levelEditorTextures.getDecorationButtonTexture();
		buttons.add(new MenuButton(dcButtonTexture, ButtonType.DECORATION, 500, 400, 100, 50));
		
		Texture blkButtonTexture = levelEditorTextures.getBlockButtonTexture();
		buttons.add(new MenuButton(blkButtonTexture, ButtonType.BLOCK, 500, 350, 100, 50));
		
		Texture clButtonTexture = levelEditorTextures.getClearButtonTexture();
		buttons.add(new MenuButton(clButtonTexture, ButtonType.CLEAR, 500, 0, 100, 50));
		
		getButton(activeSubMenuButtonType).setActive(true);
		
		// Create our sub menus.
		backgroundMenu = new BackgroundMenu(this);
		decorationMenu = new DecorationMenu(this);
		blockMenu      = new BlockMenu(this, this.levelEditorTextures);
	}
	
	/**
	 * Called on tile selection.
	 * @param selectedTile
	 */
    public void onTileSelect(Tile selectedTile) { this.activeTile = selectedTile; }

	/**
	 * Called on tile selection.
	 * @return activeTile
	 */
	public Tile getActiveTile() { return this.activeTile; }
    
    /**
	 * Called on mouse click.
	 * @param posX relative to menu 
	 * @param posY relative to menu
	 */
    public void onMouseClick(int posX , int posY) {
    	// Check buttons for a click.
    	for(MenuButton button :  buttons) {
    		if(button.isMouseClickOnButton(posX, posY)) {
    			// This button was clicked on. Switch on its type.
    			switch(button.getType()) {
					case BACKGROUND:
						getButton(ButtonType.BLOCK).setActive(false);
						getButton(ButtonType.DECORATION).setActive(false);
						getButton(ButtonType.BACKGROUND).setActive(true);
						activeSubMenuButtonType = ButtonType.BACKGROUND;
						break;
					case BLOCK:
						getButton(ButtonType.BLOCK).setActive(true);
						getButton(ButtonType.DECORATION).setActive(false);
						getButton(ButtonType.BACKGROUND).setActive(false);
						activeSubMenuButtonType = ButtonType.BLOCK;
						break;
					case DECORATION:
						getButton(ButtonType.BLOCK).setActive(false);
						getButton(ButtonType.DECORATION).setActive(true);
						getButton(ButtonType.BACKGROUND).setActive(false);
						activeSubMenuButtonType = ButtonType.DECORATION;
						break;
					case CLEAR:
						// Clear the active tile.
						if(this.activeTile != null) {
							this.activeTile.setBackgroundTexture(null);
							this.activeTile.setDecorationTexture(null);
							this.activeTile.setPhysicsBlock(null);
						}
						break;
					default:
						break;
    			}
    		}
    	}
		// This may be a click on a button within a sub menu.
		if(posX >= 600) {
			// The click happened in the active sub menu, let it handle the click.
			switch(this.activeSubMenuButtonType) {
				case BACKGROUND:
					backgroundMenu.onMouseClick(posX, posY);
					break;
				case DECORATION:
					decorationMenu.onMouseClick(posX, posY);
					break;
				case BLOCK:
					blockMenu.onMouseClick(posX, posY);
					break;
				default:
					break;
			}
		}
	}
    
    /**
     * Get the button with the provided type from the list of buttons.
     * @param type
     * @return button
     */
    public MenuButton getButton(ButtonType type) {
    	for(MenuButton button : buttons) {
    		if(button.getType() == type) {
    			return button;
    		}
    	}
		return null;
    }
	
	/**
	 * Draw the level editor menu.
	 * @param batch
	 */
    public void draw(SpriteBatch batch) {
    	// Draw the menu background.
    	batch.draw(levelEditorTextures.getMenuBackground(), menuSize, 0, menuSize, menuSize);
    	// Draw the menu buttons.
    	for(MenuButton button :  buttons) {
    		button.draw(batch);
    	}
    	// Draw the active sub menu
    	switch(activeSubMenuButtonType) {
			case BACKGROUND:
				backgroundMenu.draw(batch);
				break;
			case DECORATION:
				decorationMenu.draw(batch);
				break;
			case BLOCK:
				blockMenu.draw(batch);
				break;
			default:
				// What the hell?
				break;
    	}
	}
}
