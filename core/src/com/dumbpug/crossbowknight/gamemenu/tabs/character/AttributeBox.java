package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.FontPack;

/**
 * An attribute on the character menu tab.
 * @author nikolas.howard
 */
public class AttributeBox {
	/** The name of the attribute that this box represents. */
	protected String name;
	/** The position of this box. */
	private float posX, posY;
	/** The width of this box. */
	private static float width = C.INGAME_MENU_WIDTH * 0.45f;
	/** The width of the area in which the point count is displayed. */
	private static float pointDisplayWidth  = width / 5f;
	/** The font to use in drawing this attribute box. */
	private BitmapFont font;
	
	/**
	 * Create a new instance of the AttributeBox class.
	 * @param name
	 * @param stats
	 * @param posX
	 * @param posY
	 */
	public AttributeBox(String name, float posX, float posY){
		this.name  = name;
		this.posX  = posX;
		this.posY  = posY;
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_SMALL;
		this.font      = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
	}
	
	/**
	 * Gets the value to display as the attribute value.
	 * @return display value.
	 */
	public String getDisplayValue() { return ""; }
	
	/**
	 * Draw this attribute box.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Write the attribute name.
		font.draw(batch, name, posX, posY, width, Align.left, false);
		// Write the attribute level count.
		font.draw(batch, getDisplayValue(), (posX + width) - (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT + pointDisplayWidth), posY, pointDisplayWidth, Align.center, false);
	}
}
