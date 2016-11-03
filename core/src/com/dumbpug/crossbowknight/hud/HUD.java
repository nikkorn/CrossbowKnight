package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * Represents the game HUD.
 */
public class HUD {
    /** The player status bar. */
    private HUDStatusBar statusBar;
    /** The ammo details bar. */
    private HUDAmmoBar ammoBar;
    
    // TODO Create a HUD Item bar for item usages.

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
     * @param player
     */
    public void draw(SpriteBatch batch, Player player) {
        this.statusBar.draw(batch, player);
        this.ammoBar.draw(batch, player);
    }
}
