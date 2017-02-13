package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.Stats;
import com.dumbpug.crossbowknight.entities.characters.Stats.Attribute;

/**
 * Holds the selectable attribute boxes for the character menu tab and handles selection.
 * @author nikolas.howard
 */
public class SelectableAttributeBoxContainer {
	/** The player stats. */
	private Stats stats;
	/** The list of attributes. */
	private ArrayList<SelectableAttributeBox> attributeBoxes = new ArrayList<SelectableAttributeBox>();
	/** The index of the selected attribute box. */
	private int selectedAttributeBoxIndex = 0;
	
	/**
	 * Create a new instance of the SelectableAttributeBoxContainer class.
	 * @param stats
     */
	public SelectableAttributeBoxContainer(Stats stats) {
		this.stats = stats;
		// Figure out the position on the screen from where to start positioning the attribute boxes.
		float containerPositionX = C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.04f);
		float containerPositionY = C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.37f);
		// Create the attribute boxes.
		attributeBoxes.add(new SelectableAttributeBox(Attribute.AGILITY, stats, containerPositionX, containerPositionY));
		attributeBoxes.add(new SelectableAttributeBox(Attribute.MARKSMANSHIP, stats, containerPositionX, containerPositionY + C.MENU_CHARACTER_ATTRIBUTE_HEIGHT));
		attributeBoxes.add(new SelectableAttributeBox(Attribute.LUCK, stats, containerPositionX, containerPositionY + (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT * 2)));
		attributeBoxes.add(new SelectableAttributeBox(Attribute.DEFENSE, stats, containerPositionX, containerPositionY + (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT * 3)));
		attributeBoxes.add(new SelectableAttributeBox(Attribute.STRENGTH, stats, containerPositionX, containerPositionY + (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT * 4)));
		attributeBoxes.add(new SelectableAttributeBox(Attribute.VITALITY, stats, containerPositionX, containerPositionY + (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT * 5)));
	}
	
	/**
	 * Called when the user wants to move selection up.
	 */
	public void selectionUp() {
		if(selectedAttributeBoxIndex == 0) {
			selectedAttributeBoxIndex = attributeBoxes.size() - 1;
		} else {
			selectedAttributeBoxIndex--;
		}
	}
	
	/**
	 * Called when the user wants to move selection down.
	 */
	public void selectionDown() {
		if(selectedAttributeBoxIndex == attributeBoxes.size() - 1) {
			selectedAttributeBoxIndex = 0;
		} else {
			selectedAttributeBoxIndex++;
		}
	}
	
	/**
	 * Called when the user attempts to select the currently highlighted attribute.
	 */
	public void onSelection() {
		// Do not do anything if we have no available points to spend.
		if (stats.getUnspentAttributeLevels() > 0) {
			attributeBoxes.get(selectedAttributeBoxIndex).select();
		}
	}
	
	/**
	 * Draw the attribute boxes.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		for(SelectableAttributeBox attribute : attributeBoxes) {
			// Get whether this attribute is the currently selected one.
			boolean isSelected = attribute == attributeBoxes.get(selectedAttributeBoxIndex);
			// Draw the attribute.
			attribute.draw(batch, isSelected);
		}
	}
}
