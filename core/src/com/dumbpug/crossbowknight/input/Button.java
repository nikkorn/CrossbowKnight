package com.dumbpug.crossbowknight.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by nik on 17/09/16.
 */
public class Button {
    /** This buttons target key. */
    private Input.Keys key;
    private boolean isPressed = false;
    private boolean isDown = false;

    /**
     * Creates a new instance of the Button class.
     * @param key
     */
    public Button(Input.Keys key) {
        this.key = key;
    }

    public boolean hasBeenPressed() {
        boolean pressed = this.isPressed;
        this.isPressed = false;
        return false;
    }

    /**
     * Get this buttons target key.
     * @return key
     */
    public Input.Keys getKey() {
        return key;
    }


}
