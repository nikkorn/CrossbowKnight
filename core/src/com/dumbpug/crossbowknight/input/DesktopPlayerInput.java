package com.dumbpug.crossbowknight.input;

import com.badlogic.gdx.Gdx;

/**
 * Created by nik on 17/09/16.
 */
public class DesktopPlayerInput extends PlayerInput {
    private boolean upButtonPressed         = false;
    private boolean downButtonPressed       = false;
    private boolean leftButtonPressed       = false;
    private boolean rightButtonPressed      = false;
    private boolean acceptButtonPressed     = false;
    private boolean cancelButtonPressed     = false;
    private boolean jumpButtonPressed       = false;
    private boolean fireButtonPressed       = false;
    private boolean secondaryButtonPressed  = false;
    private boolean thirdButtonPressed      = false;
    private boolean cycleLeftButtonPressed  = false;
    private boolean cycleRightButtonPressed = false;

    @Override
    public boolean isUpButtonPressed() {
        boolean pressed = upButtonPressed;
        upButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isUpButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.UP_KEY);
    }

    @Override
    public boolean isLeftButtonPressed() {
        boolean pressed   = leftButtonPressed;
        leftButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isLeftButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.LEFT_KEY);
    }

    @Override
    public boolean isDownButtonPressed() {
        boolean pressed   = downButtonPressed;
        downButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isDownButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.DOWN_KEY);
    }

    @Override
    public boolean isRightButtonPressed() {
        boolean pressed    = rightButtonPressed;
        rightButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isRightButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.RIGHT_KEY);
    }

    @Override
    public boolean isAcceptButtonPressed() {
        boolean pressed     = acceptButtonPressed;
        acceptButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isAcceptButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.ACCEPT);
    }

    @Override
    public boolean isCancelButtonPressed() {
        boolean pressed     = cancelButtonPressed;
        cancelButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isCancelButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CANCEL);
    }

    @Override
    public boolean isJumpButtonPressed() {
        boolean pressed   = jumpButtonPressed;
        jumpButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isJumpButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.JUMP);
    }

    @Override
    public boolean isFireButtonPressed() {
        boolean pressed   = fireButtonPressed;
        fireButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isFireButtonDown() {
        return Gdx.input.isButtonPressed(KeyMapping.FIRE);
    }

    @Override
    public boolean isSecondaryButtonPressed() {
        boolean pressed        = secondaryButtonPressed;
        secondaryButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isSecondaryButtonDown() {
        return Gdx.input.isButtonPressed(KeyMapping.SECONDARY);
    }

    @Override
    public boolean isThirdButtonPressed() {
        boolean pressed    = thirdButtonPressed;
        thirdButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isThirdButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.THIRD);
    }

    @Override
    public boolean isCycleLeftButtonPressed() {
        boolean pressed        = cycleLeftButtonPressed;
        cycleLeftButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isCycleLeftButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CYCLE_LEFT);
    }

    @Override
    public boolean isCycleRightButtonPressed() {
        boolean pressed         = cycleRightButtonPressed;
        cycleRightButtonPressed = false;
        return pressed;
    }

    @Override
    public boolean isCycleRightButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CYCLE_RIGHT);
    }

   /** -------------------- InputAdapter Overrides -------------------- */

    /**
     * Use keyDown method to determine when a key is pressed only once.
     * @param keycode
     * @return processed
     */
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == KeyMapping.UP_KEY) {
            this.upButtonPressed = true;
        } else if(keycode == KeyMapping.DOWN_KEY) {
            this.downButtonPressed = true;
        } else if(keycode == KeyMapping.LEFT_KEY) {
            this.leftButtonPressed = true;
        } else if(keycode == KeyMapping.RIGHT_KEY) {
            this.rightButtonPressed = true;
        } else if(keycode == KeyMapping.ACCEPT) {
            this.acceptButtonPressed = true;
        } else if(keycode == KeyMapping.CANCEL) {
            this.cancelButtonPressed = true;
        } else if(keycode == KeyMapping.JUMP) {
            this.jumpButtonPressed = true;
        } else if(keycode == KeyMapping.THIRD) {
            this.thirdButtonPressed = true;
        } else if(keycode == KeyMapping.CYCLE_LEFT) {
            this.cycleLeftButtonPressed = true;
        } else if(keycode == KeyMapping.CYCLE_RIGHT) {
            this.cycleRightButtonPressed = true;
        }
        return true;
    }

    /**
     * Use touchDown method to determine when a mouse button is pressed only once.
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(button == KeyMapping.FIRE) {
            this.fireButtonPressed = true;
        } else if(button == KeyMapping.SECONDARY) {
            this.secondaryButtonPressed = true;
        }
        return true;
    }
}
