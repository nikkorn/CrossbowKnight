package com.dumbpug.crossbowknight.hud.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nik on 25/09/16.
 */
public class DialogBox {
    /** Is this dialog box finished with? */
    private boolean isFinished = false;
    /** Is this dialog box cancellable? */
    private boolean isCancellable = false;
    /** The dialog sections which are to be displayed. */
    private HashMap<String, DialogSection> dialogSections = new HashMap<String, DialogSection>();
    /** The identifier of the current dialog section. */
    private String currentDialogSectionIdentifier;

    /** Dialog box textures. */
    private static Texture dialogTopTexture;
    private static Texture dialogBodyTexture;
    private static Texture dialogBottomTexture;

    /**
     * Create a new instance of the DialogBox class.
     */
    public DialogBox() {
        dialogTopTexture    = new Texture("graphics/hud/dialog/dialog_top.png");
        dialogBodyTexture   = new Texture("graphics/hud/dialog/dialog_body.png");
        dialogBottomTexture = new Texture("graphics/hud/dialog/dialog_bottom.png");
    }

    /**
     * Process input for this dialog box.
     */
    public void processInput() {
        if(!isFinished) {
            if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) {
                dialogSections.get(currentDialogSectionIdentifier).onUpPress();
            }
            if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) {
                dialogSections.get(currentDialogSectionIdentifier).onDownPress();
            }
            if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) {
                dialogSections.get(currentDialogSectionIdentifier).onAcceptPress();
            }
            if(CrossbowKnight.getPlayerInput().isCancelButtonDown()) {
                if(isCancellable) {
                    this.isFinished = true;
                }
            }
        }
    }

    /**
     * Update the state of the dialog box.
     */
    public void update() {
        // Get the current dialog section.
        DialogSection currentDialogSection = dialogSections.get(currentDialogSectionIdentifier);
        // Is this dialog section finished with?
        if(currentDialogSection != null && currentDialogSection.isFinishedWith()) {
            // Get the next dialog section.
            String nextDialogSectionId = currentDialogSection.getFollowingSectionIdentifier();
            // If we have no id for the next dialog section then it is safe to say we are done.
            if(nextDialogSectionId == null) {
                this.isFinished = true;
            } else {
                // There is another dialog section to show. Make it current.
                this.currentDialogSectionIdentifier = nextDialogSectionId;
                // We could be reusing an old dialog section. Refresh it just in case.
                dialogSections.get(currentDialogSectionIdentifier).refresh();
            }
        }
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
    public void addDialogSection(String dialogSectionId, DialogSection dialogSection) {
        this.dialogSections.put(dialogSectionId, dialogSection);
    }

    /**
     * Draw this dialog box.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Draw the dialog background.
        batch.draw(dialogBodyTexture, C.DIALOG_BORDER_SIZE, C.DIALOG_BORDER_SIZE, Gdx.graphics.getWidth() - (C.DIALOG_BORDER_SIZE*2), C.DIALOG_HEIGHT - C.DIALOG_BORDER_SIZE);
        batch.draw(dialogTopTexture, C.DIALOG_BORDER_SIZE, C.DIALOG_HEIGHT, Gdx.graphics.getWidth() - (C.DIALOG_BORDER_SIZE*2), C.DIALOG_BORDER_SIZE);
        batch.draw(dialogBottomTexture, C.DIALOG_BORDER_SIZE, 0, Gdx.graphics.getWidth() - (C.DIALOG_BORDER_SIZE*2), C.DIALOG_BORDER_SIZE);
        // Draw the current dialog section.
        DialogSection currentDialogSection = dialogSections.get(currentDialogSectionIdentifier);
        if(currentDialogSection != null) {
            currentDialogSection.draw(batch);
        }
    }
}
