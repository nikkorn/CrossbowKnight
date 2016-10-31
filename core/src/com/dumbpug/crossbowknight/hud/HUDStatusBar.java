package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * The players status bar displayed in the top left of the screen.
 * Created by nik on 09/10/16.
 */
public class HUDStatusBar {
    /** The resources for the HUD status bar. */
    private static Texture statusIcons;
    private static Texture statusBarEnd;
    private static Texture statusBarEmpty;
    private static Texture statusBarHealth;
    private static Texture statusBarShield;
    private static Texture statusBarBowTension;
    
    /** The positions of the individual status bars.  */
    private float statusBarPosX     = (C.HUD_STATUS_BAR_HEIGHT * 0.6f) + (C.HUD_STATUS_BAR_MARGIN * 2);
    private float healthBarPosY     = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 0.5f);
    private float shieldBarPosY     = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 0.6f);
    private float bowTensionBarPosY = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 0.9f);
    
    /**
     * Create a new instance of HUDStatusBar.
     */
    public HUDStatusBar() {
        // Load our resources.
        statusIcons         = new Texture("graphics/hud/statusbar/statusbar_icons.png");
        statusBarEnd        = new Texture("graphics/hud/statusbar/status_bar_end.png");
        statusBarEmpty      = new Texture("graphics/hud/statusbar/status_bar_empty_section.png");
        statusBarHealth     = new Texture("graphics/hud/statusbar/status_bar_health_section.png");
        statusBarShield     = new Texture("graphics/hud/statusbar/status_bar_shield_section.png");
        statusBarBowTension = new Texture("graphics/hud/statusbar/status_bar_bow_tension_section.png");
    }

    /**
     * Draw the HUD.
     * @param batch
     * @param player
     */
    public void draw(SpriteBatch batch, Player player) {
    	// Draw the Icons
        batch.draw(statusIcons, C.HUD_STATUS_BAR_MARGIN, Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT + C.HUD_STATUS_BAR_MARGIN),
        		C.HUD_STATUS_BAR_HEIGHT*0.6f, C.HUD_STATUS_BAR_HEIGHT);
        // Draw the individual status bars.
        drawHealthBar(batch, player.getHealthStatus().getMaxHealth(), player.getHealthStatus().getHealth());
        // TODO drawShieldBar(batch, player);
        // TODO drawBowTensionBar(batch, player);
    }
    
    /**
     * Draw the Health bar.
     * @param maxHealth
     * @param currentHealth
     */
    private void drawHealthBar(SpriteBatch batch, int maxHealth, int currentHealth) {
    	// Draw the bar end.
    	batch.draw(statusBarEnd, statusBarPosX, healthBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the empty section of the bar.
    	batch.draw(statusBarEmpty, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, healthBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * maxHealth, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the full section of the bar.
    	batch.draw(statusBarHealth, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, healthBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * currentHealth, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the end section of the bar.
    	batch.draw(statusBarEnd, statusBarPosX + (C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * (maxHealth+1)), healthBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
    }
}
