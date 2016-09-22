package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.graphics.Animation;

/**
 * Draws the player.
 * @author nikolas.howard
 */
public class PlayerDrawer {
	/** The player. */
	private Player player;
	
	/** ---------- Drawing resources ----------- */
	/** Standard walking animation */
	private Animation walkAnimation;
	/** Players head sprite. */
	private Sprite head;
	/** Players arms sprite. */
	private Sprite arms;
	/** Players idle sprite. */
	private Sprite idle;
	/** Players airborne sprite. */
	private Sprite airborne;
	/** ---------------------------------------- */
	
	/**
	 * Create a new instance of the PlayerDrawer class.
	 * @param player
	 */
	public PlayerDrawer(Player player) {
		this.player = player;
		// Set up resources for drawing.
		walkAnimation = new Animation(new Texture("graphics/characters/player/robo_walk_t.png"), 9, 1, 1/16f);
		head = new Sprite(new Texture("graphics/characters/player/robo_head.png"));
		head.setSize(C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER, C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER);
		head.setOrigin(head.getWidth()*(14/29f), head.getHeight()*(17/29f));
		arms = new Sprite(new Texture("graphics/characters/player/robo_arms.png"));
		arms.setSize(C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER, C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER);
		arms.setOrigin(arms.getWidth()*(14/29f), arms.getHeight()/2);
		idle = new Sprite(new Texture("graphics/characters/player/robo_idle.png"));
		idle.setSize(C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER, C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER);
		airborne = new Sprite(new Texture("graphics/characters/player/robo_air.png"));
		airborne.setSize(C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER, C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER);
	}
	
	/**
	 * Draw the player.
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 */
	public void draw(SpriteBatch batch, float offsetX, float offsetY) {
		// Get the players position.
		float playerPosX = player.getCurrentOriginPoint().getX() - (C.PLAYER_SIZE_WIDTH/2);
		float playerPosY = player.getCurrentOriginPoint().getY() - (C.PLAYER_SIZE_HEIGHT/2);
		
		// The position of the cursor defines the direction we are facing.
		boolean facingRight = Gdx.input.getX() > (Gdx.graphics.getWidth()/2);
		
		// Get angle of player and pointer.
		double viewAngle = !facingRight ? -player.getAngleOfFocus() : -(player.getAngleOfFocus() + 180);

		// Get next frame from our walking animation.
		TextureRegion walkAnimationFrame = walkAnimation.getCurrentFrame(true);
		if(!facingRight) {
			walkAnimationFrame.flip(!walkAnimationFrame.isFlipX(), false);
		} else {
			walkAnimationFrame.flip(walkAnimationFrame.isFlipX(), false);
		}
		
		head.setRotation((float) viewAngle);
		head.setPosition((playerPosX * C.LAYOUT_MULTIPLIER) + offsetX, (playerPosY * C.LAYOUT_MULTIPLIER) + offsetY);
		arms.setRotation((float) viewAngle);
		arms.setPosition((playerPosX * C.LAYOUT_MULTIPLIER) + offsetX, (playerPosY * C.LAYOUT_MULTIPLIER) + offsetY);
		idle.setPosition((playerPosX * C.LAYOUT_MULTIPLIER) + offsetX, (playerPosY * C.LAYOUT_MULTIPLIER) + offsetY);
		airborne.setPosition((playerPosX * C.LAYOUT_MULTIPLIER) + offsetX, (playerPosY * C.LAYOUT_MULTIPLIER) + offsetY);
		
		head.setFlip(!facingRight, false);
		arms.setFlip(!facingRight, false);
		idle.setFlip(!facingRight, false);
		airborne.setFlip(!facingRight, false);

		if(!player.isTouchingFloor()) {
			// If our player is airborne, then draw airborne body.
			airborne.draw(batch);
		} else if(player.isIdle()) {
			// If our player is idle then draw idle body.
			idle.draw(batch);
		} else {
			// If our player is not airborne and not idle, they must be walking. Draw walk animation.
			batch.draw(walkAnimationFrame, (playerPosX * C.LAYOUT_MULTIPLIER) + offsetX, (playerPosY * C.LAYOUT_MULTIPLIER) + offsetY, 
					C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER, C.PLAYER_SIZE_HEIGHT * C.LAYOUT_MULTIPLIER);
		}
		
		head.draw(batch);
		arms.draw(batch);
	}
}
