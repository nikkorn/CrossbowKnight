package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.nbp.NBPBloom;
import com.dumbpug.nbp.NBPBoxType;
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
	
	/**
     * Initialise a new instance of the Player class.
     * @param x
     * @param y
     */
    public Player(float x, float y) {
        // Initialise our players physics box.
        playerPhysicsBox = new PlayerPhysicsBox(this, x, y, C.PLAYER_SIZE_WIDTH, C.PLAYER_SIZE_HEIGHT, NBPBoxType.KINETIC);
        playerPhysicsBox.setName("PLAYER");
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
    public void jump() { playerPhysicsBox.jump(); }

	/**
	 * Handles the player being pushed by a force bloom in the world.
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 * @param distance
	 */
	public void onPushedByForce(NBPBloom bloom, float angleOfForce, float force, float distance) {
		// TODO Auto-generated method stub
	}

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
		// TODO Auto-generated method stub
		
	}
}
