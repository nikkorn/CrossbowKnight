package com.dumbpug.crossbowknight.leveleditor.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Button for level editor.
 * @author nikolas.howard
 */
public class MenuButton {
	private int posX;
	private int posY;
	private int width;
	private int height;
	private boolean isVisible = true;
	private boolean isActive  = false;
	private Texture texture;
	private ButtonType type;
	private Texture activeBox = new Texture("graphics/misc/LEVELEDITOR_ACTIVETILEMARKER.png");
	
	/**
	 * Types of menu button.
	 */
	public enum ButtonType {
		BACKGROUND,
		DECORATION,
		BLOCK,
		CLEAR,
		TILE
	}
	
	/**
	 * Create a new instance of LevelEditorMenuButton
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public MenuButton(Texture texture, ButtonType type, int x, int y, int width, int height) {
		this.texture = texture;
		this.type    = type;
		this.posX    = x;
		this.posY    = y;
		this.width   = width;
		this.height  = height;
	}
	
	/**
	 * Set active.
	 * @param isActive
	 */
    public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    
    /**
     * Get is active.
     * @return
     */
    public boolean isActive() {
		return this.isActive;
	}
	
    /**
     * Set visibility.
     * @param isVisible
     */
    public void setVisible(boolean isVisible) {
  		this.isVisible = isVisible;
  	}
      
    /**
     * Get is visible.
     * @return
     */
    public boolean isVisible() {
		return this.isVisible;
	}
    
    /**
     * Get the button type.
     * @return
     */
    public ButtonType getType() {
		return type;
	}
    
    /**
     * Is a mouse click within the bounds of this button?
     * @param clickPosX
     * @param clickPosY
     * @return click in button?
     */
    public boolean isMouseClickOnButton(float clickPosX, float clickPosY) {
    	clickPosY = Gdx.graphics.getHeight() - clickPosY;
    	// Can't click invisible buttons! 
    	if(isVisible) {
    		boolean inXBounds =  clickPosX > posX && clickPosX < (posX + width);
        	boolean inYBounds =  clickPosY > posY && clickPosY < (posY + height);
        	return inXBounds && inYBounds;
    	}
    	return false;
    }
    
    /**
     * Draw this button.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
    	// Draw only if visible.
    	if(isVisible) {
    		batch.draw(texture, posX, posY, width, height);
    		// If this button is active, then draw around it.
    		if(isActive) {
    			batch.draw(activeBox, posX, posY, width, height);
    		}
    	}
    }
}
