package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.GameMath;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.dialog.Dialog;
import com.dumbpug.crossbowknight.dialog.DialogType;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.tiles.ITileInteractionResolver;
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
	/** The tile interaction resolver. */
	private ITileInteractionResolver tileInteractionResolver;
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
		if(CrossbowKnight.getPlayerInput().isRightButtonDown()) { this.characterPhysicsBox.moveRight(); }
		if(CrossbowKnight.getPlayerInput().isLeftButtonDown()) { this.characterPhysicsBox.moveLeft(); }
		
		// Make our player jump or interact with a tile if a resolver is defined.
		if(CrossbowKnight.getPlayerInput().isJumpButtonPressed()) { onJump(); }
		
		// Swap primary and secondary ammo slots or interact with a tile if a resolver is defined.
		if(CrossbowKnight.getPlayerInput().isCycleLeftButtonPressed()) { onCycleLeft(); }
		// Swap primary and secondary item slots or interact with a tile if a resolver is defined.
		if(CrossbowKnight.getPlayerInput().isCycleRightButtonPressed()) { onCycleRight(); }
		
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
		if(!isGuarding && CrossbowKnight.getPlayerInput().isFireButtonPressed()) { equipment.useAmmo(); }
		// Use item. TODO Replace for better key.
		if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) { equipment.useItem(); }
	}
	
	/** Called when the Player presses the cycle left button. */
	public void onCycleLeft() {
		// Check to see if this action can be resolved with our tile interaction resolver.
		if(tileInteractionResolver != null && tileInteractionResolver.cycleLeft()) {
			return;
		}
		// This button press was not used to interact with a tile, handle it here.
		this.equipment.swapAmmo();
	}
	
	/** Called when the Player presses the cycle right button. */
	public void onCycleRight() {
		// Check to see if this action can be resolved with our tile interaction resolver.
		if(tileInteractionResolver != null && tileInteractionResolver.cycleRight()) {
			return;
		}
		
		// TODO Remove!
		this.getDialogList().add(new Dialog(DialogType.NEGATIVE, "SWAP ITEMS"));
		
		// This button press was not used to interact with a tile, handle it here.
		this.equipment.swapItems();
	}
	
	/** Called when the Player presses the cycle left button. */
	public void onJump() {
		// Check to see if this action can be resolved with our tile interaction resolver.
		if(tileInteractionResolver != null && tileInteractionResolver.select()) {
			
			// TODO Remove!
			this.getDialogList().add(new Dialog(DialogType.SPEECH, "I can't jump!"));
			
			return;
		}
		// This button press was not used to interact with a tile, handle it here and attempt to jump.
		if(this.characterPhysicsBox.jump()) {
			// The player was able to jump! Play jump sound effect.
			Audio.getSoundEffect(Audio.SoundEffect.JUMP).play();
		}
	}
	
	/**
	 * Called when a player picks up an item.
	 * @param item
	 */
	public void onItemPickup(Item item) {
		// Based on the type of the item we may want to display some info via character dialogs.
		switch(item.getType()) {
			case GOLD:
				this.getDialogList().add(new Dialog(DialogType.POSITIVE, "+" + item.getQuantity() + "G "));
			default:
				// No need to display any info.
				break;
		}
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
	 * Get whether this character is a player.
	 * @return is player.
	 */
	public boolean isPlayer() { return true; }
	
	/**
	 * Set the tile interaction resolver for this character.
	 * This resolver will determine if certain key presses should be resolved at a tile level.
	 * @param resolver
	 */
	public void setTileInteractionResolver(ITileInteractionResolver resolver) { this.tileInteractionResolver = resolver; }
	
	/**
	 * Draw the player.
	 */
	@Override
	public void drawCharacter(SpriteBatch batch, LevelCamera camera) { playerDrawer.draw(batch, camera.getX(), camera.getY()); }
}
