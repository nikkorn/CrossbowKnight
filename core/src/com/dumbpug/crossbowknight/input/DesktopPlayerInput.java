package com.dumbpug.crossbowknight.input;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by nik on 17/09/16.
 */
public class DesktopPlayerInput implements PlayerInput, InputProcessor {
    private boolean upButtonPressed = false;

    @Override
    public void update() {

    }

    @Override
    public boolean isUpButtonPressed() {
        return false;
    }

    @Override
    public boolean isUpButtonDown() {
        return upButtonPressed;
    }

    @Override
    public boolean isLeftButtonPressed() {
        return false;
    }

    @Override
    public boolean isLeftButtonDown() {
        return false;
    }

    @Override
    public boolean isDownButtonPressed() {
        return false;
    }

    @Override
    public boolean isDownButtonDown() {
        return false;
    }

    @Override
    public boolean isRightButtonPressed() {
        return false;
    }

    @Override
    public boolean isRightButtonDown() {
        return false;
    }

    @Override
    public boolean isAcceptButtonPressed() {
        return false;
    }

    @Override
    public boolean isAcceptButtonDown() {
        return false;
    }

    @Override
    public boolean isCancelButtonPressed() {
        return false;
    }

    @Override
    public boolean isCancelButtonDown() {
        return false;
    }

    @Override
    public boolean isJumpButtonPressed() {
        return false;
    }

    @Override
    public boolean isJumpButtonDown() {
        return false;
    }

    @Override
    public boolean isFireButtonPressed() {
        return false;
    }

    @Override
    public boolean isFireButtonDown() {
        return false;
    }

    @Override
    public boolean isSecondaryButtonPressed() {
        return false;
    }

    @Override
    public boolean isSecondaryButtonDown() {
        return false;
    }

    @Override
    public boolean isThirdButtonPressed() {
        return false;
    }

    @Override
    public boolean isThirdButtonDown() {
        return false;
    }

    @Override
    public boolean isCycleLeftButtonPressed() {
        return false;
    }

    @Override
    public boolean isCycleLeftButtonDown() {
        return false;
    }

    @Override
    public boolean isCycleRightButtonPressed() {
        return false;
    }

    @Override
    public boolean isCycleRightButtonDown() {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
