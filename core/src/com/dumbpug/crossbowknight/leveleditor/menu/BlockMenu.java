package com.dumbpug.crossbowknight.leveleditor.menu;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Block;
import com.dumbpug.crossbowknight.leveleditor.C;
import com.dumbpug.crossbowknight.leveleditor.LevelEditorTextures;
import com.dumbpug.crossbowknight.leveleditor.menu.button.BlockFillButton;
import com.dumbpug.crossbowknight.leveleditor.menu.button.MenuButton.ButtonType;
import com.dumbpug.crossbowknight.leveleditor.menu.button.MenuButtonGroup;
import com.dumbpug.crossbowknight.leveleditor.menu.button.TileMenuButton;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;
import com.dumbpug.crossbowknight.tiles.TileTextures;

public class BlockMenu {
    /** The level editor textures */
    private LevelEditorTextures levelEditorTextures;
    /** The list of block tile buttons. */
    private ArrayList<TileMenuButton> blockTileButtons = new ArrayList<TileMenuButton>();
    /** The group of block fill buttons. */
    private MenuButtonGroup<BlockFillButton> fillTypeButtonGroup = new MenuButtonGroup<BlockFillButton>();
    /** The main menu. */
    private MainMenu mainMenu;

    /**
     * Create a new instance of the BlockMenu class.
     */
    public BlockMenu(MainMenu mainMenu, LevelEditorTextures levelEditorTextures) {
        this.mainMenu            = mainMenu;
        this.levelEditorTextures = levelEditorTextures;
        populateTileButtonList();
        populateFillButtonGroup();
    }

    /**
     * Populate our list of block tile buttons.
     */
    private void populateTileButtonList() {
        int tilePosX  = 600;
        int tilePosY  = Gdx.graphics.getHeight() - C.MENU_TILE_SIZE;
        int tileIndex = 0;
        for(int y = 0; y < C.MENU_TILE_Y_LIMIT; y++) {
            for(int x = 0; x < C.MENU_TILE_X_LIMIT; x++) {
                // Have we reached the limit of the block tiles?
                if(tileIndex >= TileTextures.BlockTile.values().length) {
                    return;
                }
                // Get the indexed texture of the current tile.
                IndexedTileTexture texture = TileTextures.getTileTextures().getBlockTileTexture(TileTextures.BlockTile.values()[tileIndex]);
                // Calculate the position of this tile.
                int posX = tilePosX + (x*C.MENU_TILE_SIZE);
                int posY = tilePosY - (y*C.MENU_TILE_SIZE);
                // Create the tile button.
                TileMenuButton button = new TileMenuButton(texture, ButtonType.TILE, posX, posY, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE);
                blockTileButtons.add(button);
                tileIndex++;
            }
        }
    }

    /**
     * Populate our list of block tile buttons.
     */
    private void populateFillButtonGroup() {
        fillTypeButtonGroup.add(new BlockFillButton(0, levelEditorTextures.getBlockFillFullButtonTexture(),
                ButtonType.BLOCK_FILL_FULL, 605, 5, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE));
        fillTypeButtonGroup.add(new BlockFillButton(1, levelEditorTextures.getBlockFillTopButtonTexture(),
                ButtonType.BLOCK_FILL_TOP, 655, 5, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE));
        fillTypeButtonGroup.add(new BlockFillButton(2, levelEditorTextures.getBlockFillBottomButtonTexture(),
                ButtonType.BLOCK_FILL_BOTTOM, 705, 5, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE));
        fillTypeButtonGroup.add(new BlockFillButton(3, levelEditorTextures.getBlockFillLeftButtonTexture(),
                ButtonType.BLOCK_FILL_LEFT, 755, 5, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE));
        fillTypeButtonGroup.add(new BlockFillButton(4, levelEditorTextures.getBlockFillRightButtonTexture(),
                ButtonType.BLOCK_FILL_RIGHT, 805, 5, C.MENU_TILE_SIZE, C.MENU_TILE_SIZE));
        // We need an active fill type.
        fillTypeButtonGroup.setActive(fillTypeButtonGroup.getButtons().get(0));
    }

    /**
     * Called on mouse click.
     * @param posX relative to menu
     * @param posY relative to menu
     */
    public void onMouseClick(int posX , int posY) {
        // Check buttons for a click.
        for(TileMenuButton button : blockTileButtons) {
            if(button.isMouseClickOnButton(posX, posY)) {
                if(this.mainMenu.getActiveTile() != null) {
                    Block block = new Block(0,0,0,0, Block.TileBlockFillType.values()[fillTypeButtonGroup.getActive().getFillTypeId()]);
                    block.setBlockTexture(button.getIndexedTileTexture());
                    this.mainMenu.getActiveTile().setPhysicsBlock(block);
                }
            }
        }
        // Check fill type buttons for a click.
        for(BlockFillButton button : fillTypeButtonGroup.getButtons()) {
            if(button.isMouseClickOnButton(posX, posY)) {
                fillTypeButtonGroup.setActive(button);
            }
        }
    }

    /**
     * Draw this menu.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Draw the menu buttons.
        for(TileMenuButton tileButton : blockTileButtons) {
            tileButton.draw(batch);
        }
        // Draw the block fill buttons.
        for(BlockFillButton fillButton : fillTypeButtonGroup.getButtons()) {
            fillButton.draw(batch);
        }
    }
}
