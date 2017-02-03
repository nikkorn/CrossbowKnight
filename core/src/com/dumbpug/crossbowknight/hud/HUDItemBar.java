package com.dumbpug.crossbowknight.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.resources.FontPack;
import com.dumbpug.crossbowknight.resources.FontPack.FontType;
import com.dumbpug.crossbowknight.resources.ItemResources;

/**
 * The item bar displayed in the bottom left of the screen.
 */
public abstract class HUDItemBar {
    /** The background for the HUD item bar. */
    private static Texture background;
    /** The font with which to draw our item count. */
    private BitmapFont itemCountFont;
    /** The position an which to draw our item count. */
    private float itemCountPosX;
    private float itemCountPosY;

    /**
     * Create a new instance of HUDItemBar.
     */
    public HUDItemBar() {
    	itemCountPosX = getPositionX() + (C.HUD_ITEM_BAR_WIDTH * 0.68f);
    	itemCountPosY = getPositionY() + (C.HUD_ITEM_BAR_HEIGHT * 0.38f);
        // Load our resources.
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = C.FONT_SIZE_SMALL;
    	itemCountFont  = FontPack.getFontPack().getFont(FontType.MAIN_FONT, parameter);
    	itemCountFont.setColor(Color.BLACK);
    	background = new Texture("graphics/hud/itembar/itembar_background.png");
    }
    
    /**
     * Get the X position of this item bar.
     * @return X position
     */
    public abstract float getPositionX();
    
    /**
     * Get the Y position of this item bar.
     * @return Y position
     */
    public abstract float getPositionY();
    
    /**
     * The item slot we are targeting.
     * @param player
     * @return target item
     */
    public abstract Item getTargetItem(Player player);

    /**
     * Draw the HUD.
     * @param batch
     */
    public void draw(SpriteBatch batch, Player player) {
    	// Draw the item bar background.
        batch.draw(background, getPositionX(), getPositionY(), C.HUD_ITEM_BAR_WIDTH, C.HUD_ITEM_BAR_HEIGHT);
        // Draw texture for the currently selected item (if there is one).
        Item item = getTargetItem(player);
        if(item != null) {
        	Texture itemTexture = ItemResources.getItemResources().getItemTexture(item.getType());
            batch.draw(itemTexture, getPositionX(), getPositionY(), C.HUD_ITEM_BAR_HEIGHT, C.HUD_ITEM_BAR_HEIGHT);
            // Draw the item count for the currently selected item.
            itemCountFont.draw(batch, item.getQuantity() >= 0 ? item.getQuantity()+"" : "--", itemCountPosX, itemCountPosY);
        }
    }
}
