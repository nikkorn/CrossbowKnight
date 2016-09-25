package com.dumbpug.crossbowknight.hud.dialog;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Represents a section of dialog.
 * Created by nik on 25/09/16.
 */
public class DialogSection {
    /** The dialog to display. */
    private String dialog;
    /** The id of the section to navigate to when we are done with this one. */
    private String followingSectionIdentifier;
    /** The list of dialog response actions, if we have ANY response actions
     * then we need to disregard the 'followingSectionIdentifier' as the response
     * chosen by the player will define which section to move to. */
    private ArrayList<DialogResponseAction> dialogResponseActions;
    /** Has the whole section of dialog been printed? */
    private boolean hasFinishedPrinting = false;
    /** Is this dialog section finished with? If true then the dialog box will swap out
     *  the active dialog section with whatever 'followingSectionIdentifier' targets. */
    private boolean isFinishedWith = false;

    /**
     * Creates a new instance of the DialogSection class.
     * @param dialog
     */
    public DialogSection(String dialog) {
        this.dialog                     = dialog;
        this.followingSectionIdentifier = null;
        this.dialogResponseActions      = new ArrayList<DialogResponseAction>();
    }

    /**
     * On up key press.
     */
    public void onUpPress() {
    }

    /**
     * On down key press.
     */
    public void onDownPress() {
    }

    /**
     * On accept key press.
     */
    public void onAcceptPress() {
    }

    /**
     * Get the following section identifier.
     * @return following section identifier.
     */
    public String getFollowingSectionIdentifier() { return followingSectionIdentifier; }

    /**
     * Set the following section identifier.
     * @param followingSectionIdentifier
     */
    public void setFollowingSectionIdentifier(String followingSectionIdentifier) { this.followingSectionIdentifier = followingSectionIdentifier; }

    /**
     * Refresh this dialog section for resuse.
     */
    public void refresh() {
        this.isFinishedWith      = false;
        this.hasFinishedPrinting = false;
    }

    /**
     * Is this dialog section finished with?
     * @return finished with
     */
    public boolean isFinishedWith() {
        return isFinishedWith;
    }

    /**
     * Add a dialog response action to this dialog section.
     * This means that when we have finished printing the dialog then we will display
     * @param action
     */
    public void addDialogResponseAction(DialogResponseAction action) {
        dialogResponseActions.add(action);
    }

    /**
     * Draw this dialog section.
     * @param batch
     */
    public void draw(SpriteBatch batch) {

    }
}
