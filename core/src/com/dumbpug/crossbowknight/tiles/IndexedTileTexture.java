package com.dumbpug.crossbowknight.tiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by nik on 03/10/16.
 */
public class IndexedTileTexture {
    /** The index of the tile texture. */
    private int texureIndex;
    /** The actual tile texture */
    private Texture texture;

    /**
     * Create a new instance of IndexedTileTexture.
     * @param texture
     * @param index
     */
    public IndexedTileTexture(Texture texture, int index) {
        this.texture     = texture;
        this.texureIndex = index;
    }

    /**
     * Get the texture.
     * @return texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Get the texture index.
     * @return texture index
     */
    public int getTextureIndex() {
        return this.texureIndex;
    }
}
