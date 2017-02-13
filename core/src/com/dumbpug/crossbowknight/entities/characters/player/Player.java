package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.GameMath;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.characters.Character;

/**
 * Represents the player.
 * @author nikolas.howard
 */
public class Player extends Character {
    /** The player drawer. */
    private PlayerDrawer playerDrawer;
	/** The players inventory. */
	private Inventory inventory;
	/** The players equipment. */
	private EquippedItems equipment;
    /** Whether the player is currently guarding. */
    private boolean isGuarding = false;
    
	/**
     * Initialise a new instance of the Player class.
     * @param x
     * @param y
     */
    public Player(float x, float y) {
        // Initialise our players physics box.
        this.characterPhysicsBox = new PlayerPhysicsBox(this, x, y);
        // Create our player drawer.
        playerDrawer = new PlayerDrawer(this);
		// Create our players inventory.
		inventory = new Inventory();
		// Create our players equipped items helper.
		equipment = new EquippedItems(this);
    }

	/**
	 * Process input for this player.
	 */
	public void processInput() {
		// Set players angle of focus. Will depend on whether we are running on the Ouya or desktop.
		if(Ouya.runningOnOuya) {
			// TODO Get angle of focus from analog stick position.
		} else {
			setAngleOfFocus((float) GameMath.GetAngleOfLineBetweenTwoPoints(Gdx.input.getX(), Gdx.input.getY(),
					Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
		}
		
		// Move our player.
		if(CrossbowKnight.getPlayerInput().isRightButtonDown()) {
			this.characterPhysicsBox.moveRight();
		}
		if(CrossbowKnight.getPlayerInput().isLeftButtonDown()) {
			this.characterPhysicsBox.moveLeft();
		}
		// Make our player jump.
		if(CrossbowKnight.getPlayerInput().isJumpButtonPressed()) {
			// Attempt to jump.
			if(this.characterPhysicsBox.jump()) {
				// The player was able to jump! Play jump sound effect.
				Audio.getSoundEffect(Audio.SoundEffect.JUMP).play();
			}
		}
		
		// Swap primary and secondary ammo slots.
		if(CrossbowKnight.getPlayerInput().isCycleLeftButtonPressed()) {
			this.equipment.swapAmmo();
		}
		// Swap primary and secondary ammo slots.
		if(CrossbowKnight.getPlayerInput().isCycleRightButtonPressed()) {
			this.equipment.swapItems();
		}
	
		// TODO Use item.
		
		// Check for shield button being held.
		if(CrossbowKnight.getPlayerInput().isSecondaryButtonDown()) {
			// We can only be guarding if we have a shield equipped.
			if(this.equipment.getShieldSlot() != null) {
				// We are guarding.
				this.isGuarding = true;
				// Reset fire button input in case it was pressed.
				CrossbowKnight.getPlayerInput().isFireButtonPressed();
			} else {
				// We can't guard without a shield.
				this.isGuarding = false;
			}
		} else {
			this.isGuarding = false;
		}
		
		// Check for a request to fire the players weapon.
		// The player CANNOT fire if they are guarding with a shield.
		if(!isGuarding && CrossbowKnight.getPlayerInput().isFireButtonPressed()) {
			equipment.useAmmo();
		}
	}
	
	/**
	 * Called when a player picks up an item.
	 * @param item
	 */
	public void onItemPickup(Item item) {
		// Add this item to the players inventory.
		this.inventory.add(item);
	}
	
	/**
	 * Get whether the player is currently guarding.
	 * @return is guarding.
	 */
	public boolean isGuarding() { return this.isGuarding; }
	
	/**
	 * Get the players inventory.
	 * @return inventory.
     */
	public Inventory getInventory() { return this.inventory; }
	
	/**
	 * Get the players equipment.
	 * @return equipment
	 */
	public EquippedItems getEquipment() { return equipment; }
	
	/**
	 * Draw the player.
	 */
	@Override
	public void drawCharacter(SpriteBatch batch, LevelCamera camera) { playerDrawer.draw(batch, camera.getX(), camera.getY()); }
}
