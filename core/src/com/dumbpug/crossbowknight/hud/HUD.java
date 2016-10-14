package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the game HUD.
 */
public class HUD {
    /** The player status bar. */
    private HUDStatusBar statusBar;
    /** The ammo details bar. */
    private HUDAmmoBar ammoBar;

    /**
     * Create a new instance of HUD.
     */
    public HUD() {
        // Create our HUD status bar.
        this.statusBar = new HUDStatusBar();
        // Create our HUD ammo bar.
        this.ammoBar = new HUDAmmoBar();
    }

    /**
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        this.statusBar.draw(batch);
        this.ammoBar.draw(batch);
    }
}
