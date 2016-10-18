package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.pickups.Item;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * The ammo bar displayed in the bottom left of the screen.
 * Created by nik on 14/10/16.
 */
public class HUDAmmoBar {
    /** The background for the HUD ammo bar. */
    private static Texture background;
    /** The currently selected projectile type. */
    private ProjectileType currentProjectileType = ProjectileType.BOLT_BASIC;
    /** The currently selected projectile ammo count. -1 = infinite. */
    private int currentProjectileAmmo = -1;
    /** The font with which to draw our ammo count. */
    private BitmapFont ammoCountFont;
    /** The position an which to draw our ammo count. */
    private float ammoCountPosX;
    private float ammoCountPosY;

    /**
     * Create a new instance of HUDAmmoBar.
     */
    public HUDAmmoBar() {
        ammoCountPosX = C.HUD_AMMO_BAR_MARGIN + (C.HUD_AMMO_BAR_WIDTH * 0.68f);
        ammoCountPosY = C.HUD_AMMO_BAR_MARGIN + (C.HUD_AMMO_BAR_HEIGHT * 0.38f);
        // Load our resources.
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = C.FONT_SIZE_SMALL;
    	ammoCountFont  = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	ammoCountFont.setColor(Color.BLACK);
    	background     = new Texture("graphics/hud/ammobar/ammobar_background.png");
    }
    
    /**
     * Set the current ammo type and count.
     * @param type
     * @param ammo
     */
    public void setCurrentAmmoStatus(ProjectileType type, int ammo) {
    	this.currentProjectileType = type;
    	this.currentProjectileAmmo = ammo;
    }

    /**
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
    	// Draw the ammo bar background.
        batch.draw(background, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_WIDTH, C.HUD_AMMO_BAR_HEIGHT);
        // Draw texture for the currently selected ammo.
        Texture ammoTexture = null;
        switch(currentProjectileType) {
			case BOLT_BASIC:
                ammoTexture = ItemResources.getItemResources().getItemTexture(Item.ItemType.BOLT_BASIC);
				break;
        }
        if(ammoTexture != null) {
            batch.draw(ammoTexture, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_HEIGHT, C.HUD_AMMO_BAR_HEIGHT);
        }
        // Draw the ammo count for the currently selected ammo.
        ammoCountFont.draw(batch, currentProjectileAmmo >= 0 ? currentProjectileAmmo+"" : "--", ammoCountPosX, ammoCountPosY);
    }
}
