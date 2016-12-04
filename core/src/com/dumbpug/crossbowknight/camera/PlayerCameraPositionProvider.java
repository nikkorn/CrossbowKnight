package com.dumbpug.crossbowknight.camera;

import com.badlogic.gdx.Gdx;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * A CameraPositionProvider which targets a player.
 * Created by nik on 04/12/16.
 */
public class PlayerCameraPositionProvider implements ICameraPositionProvider {
    /** The player on which the camera is targeting. */
    private Player player;

    /**
     * Create a new instance of the PlayerCameraPositionProvider class.
     * @param player
     */
    public PlayerCameraPositionProvider(Player player) { this.player = player; }

    @Override
    public float getXPositon() {
        return -((player.getCurrentOriginPoint().getX() * C.LAYOUT_MULTIPLIER) - (Gdx.graphics.getWidth()/2));
    }

    @Override
    public float getYPositon() {
        return -((player.getCurrentOriginPoint().getY() * C.LAYOUT_MULTIPLIER) - (Gdx.graphics.getHeight()/2));
    }
}
