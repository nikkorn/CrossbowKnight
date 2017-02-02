package com.dumbpug.crossbowknight.input;

import com.badlogic.gdx.Gdx;

/**
 * Created by nik on 17/09/16.
 */
public class DesktopPlayerInput extends PlayerInput {
    private boolean fireButtonPressed       = false;
    private boolean secondaryButtonPressed  = false;

    @Override
    public boolean isUpButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.UP_KEY);
    }

    @Override
    public boolean isUpButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.UP_KEY);
    }

    @Override
    public boolean isLeftButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.LEFT_KEY);
    }

    @Override
    public boolean isLeftButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.LEFT_KEY);
    }

    @Override
    public boolean isDownButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.DOWN_KEY);
    }

    @Override
    public boolean isDownButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.DOWN_KEY);
    }

    @Override
    public boolean isRightButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.RIGHT_KEY);
    }

    @Override
    public boolean isRightButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.RIGHT_KEY);
    }

    @Override
    public boolean isAcceptButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.ACCEPT);
    }

    @Override
    public boolean isAcceptButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.ACCEPT);
    }

    @Override
    public boolean isCancelButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.CANCEL);
    }

    @Override
    public boolean isCancelButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CANCEL);
    }

    @Override
    public boolean isJumpButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.JUMP);
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
    	return Gdx.input.isKeyJustPressed(KeyMapping.THIRD);
    }

    @Override
    public boolean isThirdButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.THIRD);
    }

    @Override
    public boolean isCycleLeftButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.CYCLE_LEFT);
    }

    @Override
    public boolean isCycleLeftButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CYCLE_LEFT);
    }

    @Override
    public boolean isCycleRightButtonPressed() {
    	return Gdx.input.isKeyJustPressed(KeyMapping.CYCLE_RIGHT);
    }

    @Override
    public boolean isCycleRightButtonDown() {
        return Gdx.input.isKeyPressed(KeyMapping.CYCLE_RIGHT);
    }

   /** -------------------- InputAdapter Overrides -------------------- */

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
