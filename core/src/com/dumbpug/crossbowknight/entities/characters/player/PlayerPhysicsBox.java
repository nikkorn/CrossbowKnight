package com.dumbpug.crossbowknight.entities.characters.player;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.characters.CharacterPhysicsBox;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPhysicsBox;

/**
 * Physics box for a player.
 * @author nikolas.howard
 */
public class PlayerPhysicsBox extends CharacterPhysicsBox<Player> {
    
	/**
	 * Create a new instance of the PlayerPhysicsBox class.
	 * @param player
	 * @param x
	 * @param y
	 */
    public PlayerPhysicsBox(Player player, float x, float y) {
		super(player, x, y, C.PLAYER_SIZE_WIDTH, C.PLAYER_SIZE_HEIGHT);
		this.setName("PLAYER");
	}

	/**
     * Called by an ItemPhysicsBox instance when the player passes into its pickup sensor area.
     * @param itemPhysicsBox
     */
	public void onItemPhysicsBoxInteraction(ItemPhysicsBox itemPhysicsBox) {
        // Make a pickup sound.
        Audio.getSoundEffect(Audio.SoundEffect.PICKUP).play();
		// Let the Player class know that it has picked up an item.
        this.character.onItemPickup(itemPhysicsBox.getItem());
	}
}
