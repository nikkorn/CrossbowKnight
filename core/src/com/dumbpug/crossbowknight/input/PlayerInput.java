package com.dumbpug.crossbowknight.input;

/**
 * Interface for all player input.
 */
public interface PlayerInput {
    void update();
    boolean isUpButtonPressed();
    boolean isUpButtonDown();
    boolean isLeftButtonPressed();
    boolean isLeftButtonDown();
    boolean isDownButtonPressed();
    boolean isDownButtonDown();
    boolean isRightButtonPressed();
    boolean isRightButtonDown();
    boolean isAcceptButtonPressed();
    boolean isAcceptButtonDown();
    boolean isCancelButtonPressed();
    boolean isCancelButtonDown();
    boolean isJumpButtonPressed();
    boolean isJumpButtonDown();
    boolean isFireButtonPressed();
    boolean isFireButtonDown();
    boolean isSecondaryButtonPressed();
    boolean isSecondaryButtonDown();
    boolean isThirdButtonPressed();
    boolean isThirdButtonDown();
    boolean isCycleLeftButtonPressed();
    boolean isCycleLeftButtonDown();
    boolean isCycleRightButtonPressed();
    boolean isCycleRightButtonDown();
}
