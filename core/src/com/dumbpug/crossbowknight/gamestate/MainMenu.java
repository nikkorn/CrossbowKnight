package com.dumbpug.crossbowknight.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.resources.Animation;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;

/**
 * Represents the main menu state.
 */
public class MainMenu extends State {
	/** The menu background. */
    private Texture background;
    /** The menu pointer animation. */
    private Animation menuItemPointerAnimation;
	/** The font with which to draw our menu items. */
    private BitmapFont menuFont;
    /** The selected menu item. */
    private MenuItem selectedItem;
    /** Is a save available? */
    private boolean isSaveAvailable = true;
    
    /** 
     * Represents a menu item in the main menu.
     * @author nikolas.howard
     */
    private enum MenuItem {
    	CONTINUE,
    	NEW_GAME,
    	OPTIONS,
    	EXIT
    }
    
    /**
     * Create a new instance of the MainMenu class.
     */
    public MainMenu() {
    	// TODO Find out if we have a save available and set isSaveAvailable.
    	// Set the menu background texture.
    	background = new Texture("graphics/backdrops/mainmenu_background.png");
    	// Set the menu item pointer animation.
    	menuItemPointerAnimation = new Animation(new Texture("graphics/misc/bolt_pointer_animation.png"), 8, 1, 0.08f);
    	// Get the menu font.
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = C.FONT_SIZE_MEDIUM;
    	menuFont       = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	menuFont.setColor(Color.WHITE);
    	// Set the first selected menu item.
    	this.selectedItem = isSaveAvailable ? MenuItem.CONTINUE : MenuItem.NEW_GAME;
    }

    @Override
    public void renderState() {
    	// Check for input from the player.
    	if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) {
    		// Play a sound.
    		Audio.getSoundEffect(Audio.SoundEffect.BLIP_HIGH).play();
    		moveSelectionUp();
    	} else if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) {
    		// Play a sound.
    		Audio.getSoundEffect(Audio.SoundEffect.BLIP_HIGH).play();
    		moveSelectionDown();
    	}
    	// Did The user make a selection?
    	if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) {
    		// Play a different sound.
    		Audio.getSoundEffect(Audio.SoundEffect.BLIP_SELECT).play();
    		// TODO Handle the selection!
    	}
    }
    
    /**
     * Move the current selection up.
     */
    private void moveSelectionUp() {
    	switch(selectedItem) {
		case CONTINUE:
			this.selectedItem = MenuItem.EXIT;
			break;
		case NEW_GAME:
			this.selectedItem = isSaveAvailable ? MenuItem.CONTINUE : MenuItem.EXIT;
			break;
		case OPTIONS:
			this.selectedItem = MenuItem.NEW_GAME;
			break;
		case EXIT:
			this.selectedItem = MenuItem.OPTIONS;
			break;
    	}
    }
    
    /**
     * Move the current selection down.
     */
    private void moveSelectionDown() {
    	switch(selectedItem) {
		case CONTINUE:
			this.selectedItem = MenuItem.NEW_GAME;
			break;
		case NEW_GAME:
			this.selectedItem = MenuItem.OPTIONS;
			break;
		case OPTIONS:
			this.selectedItem = MenuItem.EXIT;
			break;
		case EXIT:
			this.selectedItem = isSaveAvailable ? MenuItem.CONTINUE : MenuItem.NEW_GAME;
			break;
    	}
    }

    @Override
  	public void drawState() {
    	// Get the games SpriteBatch
    	SpriteBatch batch = CrossbowKnight.getSpriteBatch();
    	batch.begin();
    	// Draw the background
    	batch.draw(background, 0, 0, Gdx.graphics.getHeight() * 2, Gdx.graphics.getHeight());
    	// TODO Draw the logo.
    	// Draw the menu items.
    	if(isSaveAvailable) {
    		menuFont.draw(batch, "CONTINUE", C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING*4) + (C.FONT_SIZE_MEDIUM));
    	}
    	menuFont.draw(batch, "NEW GAME", C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING*3) + (C.FONT_SIZE_MEDIUM));
    	menuFont.draw(batch, "OPTIONS", C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING*2) + C.FONT_SIZE_MEDIUM);
    	menuFont.draw(batch, "EXIT", C.MAIN_MENU_ITEM_PADDING, C.MAIN_MENU_ITEM_PADDING + C.FONT_SIZE_MEDIUM);
    	// Draw the selection marker next to the menu item.
    	TextureRegion pointerFrame = menuItemPointerAnimation.getCurrentFrame(true);
    	int pointerOffset = (int) (C.FONT_SIZE_MEDIUM * 0.2f);
    	switch(selectedItem) {
		case CONTINUE:
			batch.draw(pointerFrame, C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING * 4) + pointerOffset, C.FONT_SIZE_MEDIUM * 1.42f, C.FONT_SIZE_MEDIUM);
			break;
		case NEW_GAME:
			batch.draw(pointerFrame, C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING * 3) + pointerOffset, C.FONT_SIZE_MEDIUM * 1.42f, C.FONT_SIZE_MEDIUM);
			break;
		case OPTIONS:
			batch.draw(pointerFrame, C.MAIN_MENU_ITEM_PADDING, (C.MAIN_MENU_ITEM_PADDING * 2) + pointerOffset, C.FONT_SIZE_MEDIUM * 1.42f, C.FONT_SIZE_MEDIUM);
			break;
		case EXIT:
			batch.draw(pointerFrame, C.MAIN_MENU_ITEM_PADDING, C.MAIN_MENU_ITEM_PADDING + pointerOffset, C.FONT_SIZE_MEDIUM * 1.42f, C.FONT_SIZE_MEDIUM);
			break;
    	}
    	batch.end();
  	}
    
    @Override
    public void onTransitionFromState(State state) {}

    @Override
    public String getStateName() { return "main-menu"; }
}
