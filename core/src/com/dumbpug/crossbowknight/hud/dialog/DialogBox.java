package com.dumbpug.crossbowknight.hud.dialog;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Created by nik on 25/09/16.
 */
public class DialogBox {
    /** Is this dialog box finished with? */
    private boolean isFinished = false;
    /** The dialog sections which are to be displayed. */
    private ArrayList<DialogSection> dialogSections = new ArrayList<DialogSection>();

    /**
     * Handle accept press.
     */
    public void onAccept() {

    }

    /**
     * Handle up press.
     */
    public void onUp() {

    }

    /**
     * Handle down press.
     */
    public void onDown() {

    }

    /**
     * Update the state of the dialog box.
     */
    public void update() {

    }

    /**
     * Is this dialog box finished with?
     * @return is finished
     */
    public boolean isFinished() { return isFinished; }

    /**
     * Add a dialog section.
     * @param dialogSection
     */
    public void addDialogSection(DialogSection dialogSection) { this.dialogSections.add(dialogSection); }

    /**
     * Draw this dialog box.
     * @param batch
     */
    public void draw(SpriteBatch batch) {

    }
}
