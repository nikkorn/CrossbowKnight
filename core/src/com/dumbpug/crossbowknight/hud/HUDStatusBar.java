package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * The players status bar displayed in the top left of the screen.
 * Created by nik on 09/10/16.
 */
public class HUDStatusBar {
    /** The resources for the HUD status bar. */
    private static Texture statusIcons;

    /**
     * Create a new instance of HUDStatusBar.
     */
    public HUDStatusBar() {
        // Load our resources.
        statusIcons = new Texture("graphics/hud/statusbar/statusbar_icons.png");
    }

    /**
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        batch.draw(statusIcons, C.HUD_STATUS_BAR_MARGIN, Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT + C.HUD_STATUS_BAR_MARGIN),
        		C.HUD_STATUS_BAR_HEIGHT*0.6f, C.HUD_STATUS_BAR_HEIGHT);
    }
}
