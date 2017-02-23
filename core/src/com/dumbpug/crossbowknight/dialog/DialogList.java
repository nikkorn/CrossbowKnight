package com.dumbpug.crossbowknight.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * Represents a list of Dialog items.
 * @author nikolas.howard
 */
public class DialogList {
	/** The active dialog items. */
	private ArrayList<Dialog> dialogItems = new ArrayList<Dialog>();
	/** The count of times which we need to offset Y for all active Dialog items. */
	private int pendingYOffsetCount = 0;
	
	/**
	 * Add a Dialog item to this list.
	 * @param dialog
	 */
	public void add(Dialog dialog) {
		// We can't exceed the max dialog limit.
		if (dialogItems.size() == C.DIALOG_LIST_MAX_LENGTH) {
			// Remove the oldest dialog from the list.
			dialogItems.remove(C.DIALOG_LIST_MAX_LENGTH - 1);
		}
		// Add the new dialog to this list.
		this.dialogItems.add(0,  dialog);
		// We need to push up dialogs to make room for the most recent one.
		pendingYOffsetCount += C.DIALOG_OFFSET_INTERVAL;
	}
	
	/**
	 * Draw the active Dialog items in this list.
	 * @param batch
	 * @param posX
	 * @param posY
	 */
	public void draw(SpriteBatch batch, float posX, float posY) {
		// There is no need to draw this DialogList if we have no dialogs.
		if(dialogItems.isEmpty()) {
			return;
		}
		// We may need to offset any dialog items which are not the newest one. 
		if(pendingYOffsetCount > 0) {
			for(Dialog dialog : this.dialogItems) {
				// Don't offset the most recently added dialog. 
				if(this.dialogItems.get(0) != dialog) {
					dialog.applyYOffset(C.DIALOG_OFFSET_AMOUNT);
				}
			}
			pendingYOffsetCount--;
		}
		// Draw our dialogs, and remove them from this list if they are too old.
		for (Iterator<Dialog> iterator = this.dialogItems.iterator(); iterator.hasNext(); ) {
		    Dialog dialog = iterator.next();
		    if (!dialog.draw(batch, posX, posY)) {
		        iterator.remove();
		    }
		}
	}
}
