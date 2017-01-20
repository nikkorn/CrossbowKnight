package com.dumbpug.crossbowknight.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.GameMath;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.effects.DamageType;
import com.dumbpug.crossbowknight.effects.ProlongedDamageEffect;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.characters.Character;
import com.dumbpug.crossbowknight.entities.characters.Stats.Attribute;

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
	/** The angle of focus for this player (where we are looking). */
    private float angleOfFocus = 0f;
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
		// TODO Swap primary and secondary item slots.
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
			
			
			
			// TODO REMOVE!!!!!!!!!!!!!!!!
			ProlongedDamageEffect burny = new ProlongedDamageEffect();
			burny.setIntiallyAppliedDamage(10);
			burny.setDamageAppliedAtIntervals(2);
			burny.setDurationInSeconds(6);
			burny.setIntervalInSeconds(2);
			burny.setType(DamageType.BURNING);
			this.applyDamageEffect(burny);
			// TODO REMOVE!!!!!!!!!!!!!!!!
			
			
			
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
	 * Called when the player levels up.
	 */
	public void onLevelUp() {
		// We have levelled up. The players max health should now reflect this change. 
		this.getHealthStatus().setMaxHealth(C.CHARACTER_BASE_HEALTH + this.getStats().getAttributePoints(Attribute.VITALITY));
		
		// ...
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
	public void drawCharacter(SpriteBatch batch, LevelCamera camera) {
		// Let the player drawer take care of drawing.
		playerDrawer.draw(batch, camera.getX(), camera.getY());
	}
}
