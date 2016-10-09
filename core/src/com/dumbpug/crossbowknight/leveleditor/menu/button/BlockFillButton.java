package com.dumbpug.crossbowknight.leveleditor.menu.button;

import com.badlogic.gdx.graphics.Texture;

/**
 * A menu button which holds a block fill type id.
 * @author nikolas.howard
 */
public class BlockFillButton extends MenuButton {
    /** The fill type id. */
    private int fillTypeId;

    /**
     * Create a new instance of the BlockFillButton class.
     */
    public BlockFillButton(int fillTypeId, Texture texture, ButtonType type, int x, int y, int width, int height) {
        super(texture, type, x, y, width, height);
        this.fillTypeId = fillTypeId;
    }

    /**
     * Get the fill type id.
     * @return fill type id.
     */
    public int getFillTypeId() { return this.fillTypeId; }
}
