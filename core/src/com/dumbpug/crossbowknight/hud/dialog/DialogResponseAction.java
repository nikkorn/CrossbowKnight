package com.dumbpug.crossbowknight.hud.dialog;

/**
 * Response handler for dialog.
 * Created by nik on 25/09/16.
 */
public interface DialogResponseAction {

    /**
     * The reponse to display to the player.
     * @return reponse
     */
    String response();

    /**
     * Handles a particular response in dialog. The return string, when not null, is
     * the identifier of the next dialog section to move to.
     * @param response
     * @return Next dialog section identifier.
     */
    String handleResponse(String response);
}
