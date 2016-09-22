package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPPoint;
import com.dumbpug.crossbowknight.entities.characters.Character;

/**
 * Represents the player.
 * @author nikolas.howard
 */
public class Player extends Character {
    /** The angle of focus for this player (where we are looking). */
    private float angleOfFocus = 0f;
	/** The physics box for this player. */
    private PlayerPhysicsBox playerPhysicsBox;
    /** The player drawer. */
    private PlayerDrawer playerDrawer;
    
	/**
     * Initialise a new instance of the Player class.
     * @param x
     * @param y
     */
    public Player(float x, float y) {
        // Initialise our players physics box.
        playerPhysicsBox = new PlayerPhysicsBox(this, x, y);
        playerPhysicsBox.setName("PLAYER");
        // Create our player drawer.
        playerDrawer = new PlayerDrawer(this);
    }
    
    /**
	 * Get this players current point of origin.
	 * @return players point of origin.
	 */
	public NBPPoint getCurrentOriginPoint() {
		// This players point of origin will match the origin of its physics box.
		return playerPhysicsBox.getCurrentOriginPoint();
	}
	
	/**
     * Move the player to the left.
     */
    public void moveLeft() { playerPhysicsBox.moveLeft(); }

    /**
     * Move the player to the right.
     */
    public void moveRight() { playerPhysicsBox.moveRight(); }

    /**
     * Make the player jump if he can.
     */
    public void jump() {
		// Attempt to jump.
		if(playerPhysicsBox.jump()) {
			// The player was able to jump! Play jump sound effect.
			Audio.getSoundEffect(Audio.SoundEffect.JUMP).play();
		}
	}

	/**
	 * Called by physics entity when the player box lands on a static floor.
	 */
	public void onLanding() {
		// We landed so play a thump!
		Audio.getSoundEffect(Audio.SoundEffect.LANDING_SOFT).play();
	}

	/**
	 * Get whether the player is currently idle (not moving at all)
	 * @return is idle.
	 */
	public boolean isIdle() {
		return (playerPhysicsBox.getVelx() < 0.2f && playerPhysicsBox.getVelx() > -0.2f) &&
				(playerPhysicsBox.getVely() < 0.2f && playerPhysicsBox.getVely() > -0.2f);
	}

	/**
	 * Get whether the player is touching the floor (if false then the player is airborne).
	 * @return is touching floor.
	 */
	public boolean isTouchingFloor() { return playerPhysicsBox.isTouchingFloor(); }

	/**
	 * Handles the player being pushed by a force bloom in the world.
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 * @param distance
	 */
	public void onPushedByForce(NBPBloom bloom, float angleOfForce, float force, float distance) { }

	/**
	 * Get this players physics box.
	 * @return playerPhysicsBox
	 */
	public PlayerPhysicsBox getPlayerPhysicsBox() { return playerPhysicsBox; }
	
	/**
	 * Get this players angle of focus.
	 * @return angleOfFocus
	 */
	public float getAngleOfFocus() { return angleOfFocus; }

	/**
	 * Set this players angle of focus.
	 * @param angleOfFocus
	 */
	public void setAngleOfFocus(float angleOfFocus) { this.angleOfFocus = angleOfFocus; }

	@Override
	public void draw(SpriteBatch batch, float offsetX, float offsetY) {
		// Let the player drawer take care of drawing.
		playerDrawer.draw(batch, offsetX, offsetY);
	}
}
