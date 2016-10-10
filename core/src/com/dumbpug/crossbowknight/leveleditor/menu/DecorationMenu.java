package com.dumbpug.crossbowknight.leveleditor.menu;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.graphics.TileResources;
import com.dumbpug.crossbowknight.leveleditor.C;
import com.dumbpug.crossbowknight.leveleditor.menu.button.MenuButton.ButtonType;
import com.dumbpug.crossbowknight.leveleditor.menu.button.TileMenuButton;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;

public class DecorationMenu {
    /** The list of decoration tile buttons */
    private ArrayList<TileMenuButton> decorationTileButtons = new ArrayList<TileMenuButton>();
    /** The main menu. */
    private MainMenu mainMenu;

    /**
     * Create a new instance of the DecorationMenu class.
     */
    public DecorationMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        populateTileButtonList();
    }

    /**
     * Populate our list of background tile buttons.
     */
    private void populateTileButtonList() {
        int tilePosX  = 600;
        int tilePosY  = Gdx.graphics.getHeight() - C.MENU_TILE_SIZE;
        int tileIndex = 0;

        for(int y = 0; y < C.MENU_TILE_Y_LIMIT; y++) {
            for(int x = 0; x < C.MENU_TILE_X_LIMIT; x++) {
                // Have we reached the limit of the decoration tiles?
                if(tileIndex >= TileResources.DecorationTile.values().length) {
                    return;
                }
                // Get the indexed texture of the current tile.
                IndexedTileTexture texture = TileResources.getTileTextures().getDecorationTileTexture(TileResources.DecorationTile.values()[tileIndex]);
                // Calculate the position of this tile.
                int posX = tilePosX + (x*C.MENU_TILE_SIZE);
                int posY = tilePosY - (y*C.MENU_TILE_SIZE);
                // Create the tile button.
                TileMenuButton button = new TileMenuButton(texture, ButtonType.TILE, posX, posY, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE);
                decorationTileButtons.add(button);
                tileIndex++;
            }
        }
    }

    /**
     * Called on mouse click.
     * @param posX relative to menu
     * @param posY relative to menu
     */
    public void onMouseClick(int posX , int posY) {
        // Check buttons for a click.
        for(TileMenuButton button :  decorationTileButtons) {
            if(button.isMouseClickOnButton(posX, posY)) {
                if(this.mainMenu.getActiveTile() != null) {
                    this.mainMenu.getActiveTile().setDecorationTexture(button.getIndexedTileTexture());
                }
            }
        }
    }

    /**
     * Draw this menu.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Draw the menu buttons.
        for(TileMenuButton tileButton : decorationTileButtons) {
            tileButton.draw(batch);
        }

        // ****
    }
}
