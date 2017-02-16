package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Shield;

/**
 * The players status bar displayed in the top left of the screen.
 * Created by nik on 09/10/16.
 */
public class HUDStatusBar {
    /** The resources for the HUD status bar. */
    private static Texture statusIcons;
    private static Texture statusShieldIcon;
    private static Texture statusBarEnd;
    private static Texture statusBarEmpty;
    private static Texture statusBarHealth;
    private static Texture statusBarStamina;
    private static Texture statusBarShield;
    
    /** The positions of the individual status bars.  */
    private float statusBarPosX  = (C.HUD_STATUS_BAR_HEIGHT * 0.4f) + C.HUD_STATUS_BAR_MARGIN;
    private float healthBarPosY  = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 0.56f);
    private float staminaBarPosY = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 1.024f);
    private float shieldBarPosY  = Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT * 1.45f);
    
    /**
     * Create a new instance of HUDStatusBar.
     */
    public HUDStatusBar() {
        // Load our resources.
        statusIcons      = new Texture("graphics/hud/statusbar/statusbar_icons.png");
        statusShieldIcon = new Texture("graphics/hud/statusbar/statusbar_defense_icon.png");
        statusBarEnd     = new Texture("graphics/hud/statusbar/status_bar_end.png");
        statusBarEmpty   = new Texture("graphics/hud/statusbar/status_bar_empty_section.png");
        statusBarHealth  = new Texture("graphics/hud/statusbar/status_bar_health_section.png");
        statusBarShield  = new Texture("graphics/hud/statusbar/status_bar_shield_section.png");
        statusBarStamina = new Texture("graphics/hud/statusbar/status_bar_stamina_section.png");
    }

    /**
     * Draw the HUD.
     * @param batch
     * @param player
     */
    public void draw(SpriteBatch batch, Player player) {
        // Draw the individual status bars.
        drawHealthBar(batch, player.getStats().getMaxHealth(), player.getHealthStatus().getHealth());
        drawStaminaBar(batch, player.getStats().getMaxStamina(), player.getStaminaStatus().getStamina());
        // Draw the shield durability bar if we have a shield equipped.
        if(player.getEquipment().getShieldSlot() != null) {
        	Shield equippedShield = player.getEquipment().getShieldSlot();
        	// Draw the shield bar.
        	drawShieldBar(batch, equippedShield.getTotalDurability(), equippedShield.getCurrentDurability());
        	// Draw the shield icon.
        	batch.draw(statusShieldIcon, C.HUD_STATUS_BAR_MARGIN, Gdx.graphics.getHeight() - ((C.HUD_STATUS_BAR_HEIGHT*1.5f) + C.HUD_STATUS_BAR_MARGIN),
            		C.HUD_STATUS_BAR_HEIGHT*0.5f, C.HUD_STATUS_BAR_HEIGHT*0.5f);
        }
        // Draw the Icons
        batch.draw(statusIcons, C.HUD_STATUS_BAR_MARGIN, Gdx.graphics.getHeight() - (C.HUD_STATUS_BAR_HEIGHT + C.HUD_STATUS_BAR_MARGIN),
        		C.HUD_STATUS_BAR_HEIGHT*0.5f, C.HUD_STATUS_BAR_HEIGHT);
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
    
    /**
     * Draw the stamina bar.
     * @param maxStamina
     * @param currentStamina
     */
    private void drawStaminaBar(SpriteBatch batch, int maxStamina, int currentStamina) {
    	// Draw the bar end.
    	batch.draw(statusBarEnd, statusBarPosX, staminaBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the empty section of the bar.
    	batch.draw(statusBarEmpty, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, staminaBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * maxStamina, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the full section of the bar.
    	batch.draw(statusBarStamina, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, staminaBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * currentStamina, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the end section of the bar.
    	batch.draw(statusBarEnd, statusBarPosX + (C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * (maxStamina+1)), staminaBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
    }
    
    /**
     * Draw the equipped shield durability bar.
     * @param batch
     * @param totalDurability
     * @param currentDurability
     */
    private void drawShieldBar(SpriteBatch batch, float totalDurability, float currentDurability) {
    	// Draw the bar end.
    	batch.draw(statusBarEnd, statusBarPosX, shieldBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the empty section of the bar.
    	batch.draw(statusBarEmpty, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, shieldBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * totalDurability, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the full section of the bar.
    	batch.draw(statusBarShield, statusBarPosX + C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, shieldBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * currentDurability, C.HUD_SUB_STATUS_BAR_HEIGHT);
    	// Draw the end section of the bar.
    	batch.draw(statusBarEnd, statusBarPosX + (C.HUD_SUB_STATUS_BAR_SECTION_WIDTH * (totalDurability+1)), shieldBarPosY, C.HUD_SUB_STATUS_BAR_SECTION_WIDTH, C.HUD_SUB_STATUS_BAR_HEIGHT);
	}
}
