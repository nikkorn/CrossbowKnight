package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.Ammo;
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
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch, Player player) {
    	// Draw the ammo bar background.
        batch.draw(background, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_WIDTH, C.HUD_AMMO_BAR_HEIGHT);
        // Draw texture for the currently selected ammo (if there is one).
        Ammo ammo = player.getEquipment().getPrimaryAmmoSlot();
        if(ammo != null) {
        	Texture ammoTexture = ItemResources.getItemResources().getItemTexture(ammo.getType());
            batch.draw(ammoTexture, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_MARGIN, C.HUD_AMMO_BAR_HEIGHT, C.HUD_AMMO_BAR_HEIGHT);
            // Draw the ammo count for the currently selected ammo.
            ammoCountFont.draw(batch, ammo.getQuantity() >= 0 ? ammo.getQuantity()+"" : "--", ammoCountPosX, ammoCountPosY);
        }
    }
}
