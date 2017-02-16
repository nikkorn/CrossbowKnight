package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.entities.characters.Stats;
import com.dumbpug.crossbowknight.entities.characters.Stats.Attribute;

/**
 * A selectable attribute on the character menu tab.
 * @author nikolas.howard
 */
public class SelectableAttributeBox extends AttributeBox {
	/** The attribute that this box represents. */
	private Attribute attribute;
	/** The player stats. */
	protected Stats stats;

	/**
	 * Create a new instance of the SelectableAttributeBox class.
	 * @param attribute
	 * @param stats
	 * @param posX
	 * @param posY
	 */
	public SelectableAttributeBox(Attribute attribute, Stats stats, float posX, float posY) {
		super(attribute.toString(), posX, posY);
		this.attribute = attribute;
		this.stats     = stats;
	}
	
	/**
	 * Called when the user attempts to increase the attribute points by one.
	 */
	public void select() { stats.setAttributeLevel(attribute, stats.getAttributeLevel(attribute) + 1); }
	
	/**
	 * Gets the value to display as the attribute value.
	 * @return display value.
	 */
	public String getDisplayValue() { return stats.getAttributeLevel(attribute) + ""; }
	
	/**
	 * Draw this selectable attribute box.
	 * @param batch
	 * @param isSelected
	 */
	public void draw(SpriteBatch batch, boolean isSelected) {
		// Draw the attribute label and value. 
		super.draw(batch);
		// TODO Draw the point increase button.
		// TODO Draw the point increase selection overlay if we are selected.
	}
}
