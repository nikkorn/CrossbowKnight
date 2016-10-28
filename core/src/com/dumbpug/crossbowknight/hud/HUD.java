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
     * Update the HUD with the player information.
     * @param player
     */
    public void update(Player player) {
    	// TODO Update the players health.
    	// TODO If the player has a shield equipped, update the shield bar.
    	// TODO Update the bow tension bar.
    	// TODO Update the ammo bar with the current ammo.
    	// TODO Update the item bar with the current item.
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
