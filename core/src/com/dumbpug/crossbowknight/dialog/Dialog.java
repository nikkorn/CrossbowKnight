package com.dumbpug.crossbowknight.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;

/**
 * Represents a line of dialog to be displayed.
 * @author nikolas.howard
 */
public class Dialog {
	/** The type of the dialog. */
	private DialogType type;
	/** The dialog text. */
	private String text;
	/** The time of the first time this dialog was drawn. */
	private long firstDrawTime = 0l;
	/** The font for this dialog. */
	private BitmapFont font;
	/** the Y offset of this dialog. */
	private float offsetY = 0f;
	/** Whether this dialog is active. */
	
	/**
	 * Create a new instance of the Dialog class. 
	 */
	public Dialog(DialogType type, String text) {
		this.type = type;
		this.text = text;
		// Create the dialog font.
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size                  = C.DIALOG_FONT_SIZE;
    	font                            = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	font.setColor(getDialogColor());
	}
	
	/**
	 * Apply a Y offset amount.
	 * @param offset
	 */
	public void applyYOffset(float offset) { this.offsetY += offset; }
	
	/**
	 * Draw this dialog.
	 * @param batch
	 * @param posX
	 * @param posY
	 * @returns Is dialog still active
	 */
	public boolean draw(SpriteBatch batch, float posX, float posY) {
		// Is this the first time we are drawing this dialog.
		if(firstDrawTime == 0l) {
			firstDrawTime = System.currentTimeMillis();
		}
		// Write the description.
		font.draw(batch, this.text, posX - (C.DIALOG_WIDTH / 2f), posY + offsetY, C.DIALOG_WIDTH, Align.center, true);
		// Dialogs should fade throughout their lifetime.
		font.getColor().a -= 0.008;
		if(font.getColor().a < 0){
			font.getColor().a = 0f;
		}
		// Should this Dialog be removed from the Dialog list.
		if(firstDrawTime < (System.currentTimeMillis() - C.DIALOG_LIFE_DURATION)) {
			return false;
		}
		// This Dialog is still active.
		return true;
	}
	
	/**
	 * Get the colour of this dialog font based on its type.
	 * @return colour
	 */
	private Color getDialogColor() {
		switch(type) {
			case BUFF:
				return Color.LIME;
			case NEGATIVE:
				return Color.MAROON;
			case POSITIVE:
				return Color.YELLOW;
			case SPEECH:
				return Color.WHITE;
			default:
				return Color.WHITE;
		}
	}

	/**
	 * Get the dialog text.
	 * @return text.
	 */
	public String getText() { return this.text; }
	
	/**
	 * Get the dialog type.
	 * @return text.
	 */
	public DialogType getType() { return this.type; }
}
