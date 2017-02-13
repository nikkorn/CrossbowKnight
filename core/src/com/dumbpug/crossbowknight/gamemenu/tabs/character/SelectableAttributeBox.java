package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.entities.characters.Stats;
import com.dumbpug.crossbowknight.entities.characters.Stats.Attribute;

/**
 * A selectable attribute on the character menu tab.
 * @author nikolas.howard
 */
public class SelectableAttributeBox extends AttributeBox {

	/**
	 * Create a new instance of the SelectableAttributeBox class.
	 * @param attribute
	 * @param stats
	 * @param posX
	 * @param posY
	 */
	public SelectableAttributeBox(Attribute attribute, Stats stats, float posX, float posY) {
		super(attribute, stats, posX, posY);
	}
	
	/**
	 * Called when the user attempts to increase the attribute points by one.
	 */
	public void select() { stats.setAttributeLevel(attribute, stats.getAttributeLevel(attribute) + 1); }
	
	/**
	 * Draw this selectable attribute box.
	 * @param batch
	 * @param isSelected
	 */
	public void draw(SpriteBatch batch, boolean isSelected) {
		// Draw the attribute label and value. 
		super.draw(batch, isSelected);
		// TODO Draw the point increase button.
		// TODO Draw the point increase selection overlay if we are selected.
	}
}
