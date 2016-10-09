package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the game HUD.
 */
public class HUD {
    /** The player status bar. */
    private HUDStatusBar statusBar;

    /**
     * Create a new instance of HUD.
     */
    public HUD() {
        // Create our HUD status bar.
        this.statusBar = new HUDStatusBar();
    }

    /**
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        this.statusBar.draw(batch);
    }
}
