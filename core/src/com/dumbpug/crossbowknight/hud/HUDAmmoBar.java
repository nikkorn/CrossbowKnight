package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;

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
    private int currentProjectileAmmo = 12;
    /** The font with which to draw our ammo count. */
    BitmapFont ammoCountFont;

    /**
     * Create a new instance of HUDAmmoBar.
     */
    public HUDAmmoBar() {
        // Load our resources.
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = 23;
    	ammoCountFont  =  FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
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
        batch.draw(background, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_HEIGHT*1.68f, C.HUD_AMMO_BAR_HEIGHT);
        // TODO Draw texture for the currently selected ammo.
        switch(currentProjectileType) {
			case BOLT_BASIC:
				break;
        }
        // TODO Draw the ammo count for the currently selected ammo.
        ammoCountFont.draw(batch, currentProjectileAmmo + "", 63, 29);
    }
}
