package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;
import com.dumbpug.nbp.NBPBox;

/**
 * Developer HUD.
 * @author nikolas.howard
 */
public class DevHUD {
	/** The font with which to draw our HUD. */
    private BitmapFont font;
    /** The current level. */
    private Level currentLevel = null;
    /** Is this HUD open? */
    private boolean isOpen = false;
    /** The sprite to use when highlighting world physics boxes. */
    private Sprite nbpBoxHighlighter = new Sprite(new Texture("graphics/misc/nbp_box.png"));
    
    /**
     * Create a new instance of the DevHUD class.
     * @param level
     */
    public DevHUD() {
    	// Set the HUD font.
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = C.FONT_SIZE_XSMALL;
    	font           = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	font.setColor(Color.WHITE);
    }
    
    /**
     * Set the current level to display info about in the HUD.
     * @param level
     */
    public void setLevel(Level level) { this.currentLevel = level; }
    
    /**
     * Toggle the HUD open/closed.
     */
    public void toggle() { this.isOpen = !this.isOpen; }
    
    /**
     * Get whether this HUD is open.
     * @return isOpen
     */
    public boolean isOpen() { return this.isOpen; }
    
    /**
     * Draw the dev HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
    	// Highlight world boxes if a level is set.
    	if(currentLevel != null) {
    		for(NBPBox worldBox : currentLevel.getActiveLevelWorld().getPhysicsWorld().getWorldBoxes()) {
    			nbpBoxHighlighter.setX((worldBox.getX() * C.LAYOUT_MULTIPLIER) + currentLevel.getLevelCamera().getX());
    			nbpBoxHighlighter.setY((worldBox.getY() * C.LAYOUT_MULTIPLIER) + currentLevel.getLevelCamera().getY());
    			nbpBoxHighlighter.setSize(worldBox.getWidth() * C.LAYOUT_MULTIPLIER, worldBox.getHeight() * C.LAYOUT_MULTIPLIER);
    			nbpBoxHighlighter.draw(batch);
    		}
    	}
    	String details = "";
    	// Add the game FPS.
    	details += "FPS                         : " + Gdx.graphics.getFramesPerSecond() + "\n";
    	// Add the game screen dimensions.
    	details += "Display (width/height)      : " + Gdx.graphics.getWidth() + "/" + Gdx.graphics.getHeight() + "\n";
    	// Add level details if a level is set.
    	if(currentLevel != null) {
    		details += "Level World Physics Objects : " + currentLevel.getActiveLevelWorld().getPhysicsWorld().getWorldBoxes().size() + "\n";
    	}
    	// Draw the actual details to the screen.
    	font.draw(batch, details, 5, Gdx.graphics.getHeight() - 5, Gdx.graphics.getWidth(), Align.left, true);
    }
}
