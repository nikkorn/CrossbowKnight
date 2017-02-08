package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.Stats;
import com.dumbpug.crossbowknight.entities.characters.Stats.Attribute;
import com.dumbpug.crossbowknight.resources.FontPack;

/**
 * An attribute on the character menu tab.
 * @author nikolas.howard
 */
public class AttributeBox {
	/** The attribute that this box represents. */
	private Attribute attribute;
	/** The player stats. */
	private Stats stats;
	/** The position of this box. */
	private float posX, posY;
	/** The width of this box. */
	private static float width  = C.INGAME_MENU_WIDTH * 0.45f;
	/** The width of the area in which the point count is displayed. */
	private static float pointDisplayWidth  = width / 5f;
	/** The font to use in drawing this attribute box. */
	private BitmapFont font;
	
	/**
	 * Create a new instance of the AttributeBox class.
	 * @param attribute
	 * @param stats
	 * @param posX
	 * @param posY
	 */
	public AttributeBox(Attribute attribute, Stats stats, float posX, float posY){
		this.attribute = attribute;
		this.stats     = stats;
		this.posX      = posX;
		this.posY      = posY;
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size   = C.FONT_SIZE_SMALL;
		this.font        = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
	}
	
	/**
	 * Called when the user attempts to increase the attribute points by one.
	 */
	public void select() { stats.setAttributeLevel(attribute, stats.getAttributeLevel(attribute) + 1); }
	
	/**
	 * Draw this attribute box.
	 * @param batch
	 * @param isSelected
	 */
	public void draw(SpriteBatch batch, boolean isSelected) {
		// Write the attribute name.
		font.draw(batch, attribute.toString(), posX, posY, width, Align.left, true);
		// Write the attribute level count.
		font.draw(batch, stats.getAttributeLevel(attribute) + "", (posX + width) - (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT + pointDisplayWidth), posY, pointDisplayWidth, Align.center, true);
		// TODO Draw the point increase button.
		// TODO Draw the point increase selection overlay if we are selected.
	}
}
