package com.dumbpug.crossbowknight.entities.characters;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.level.LevelWorld;

/**
 * Manager for in-game characters.
 * @author nikolas.howard
 */
public class CharacterPool {
	/** The list of characters. */
	private ArrayList<Character> characters;
	/** The level world. */
	private LevelWorld levelWorld;
	
	/**
	 * Create a new instance of the CharacterPool class.
	 */
	public CharacterPool(LevelWorld levelWorld) {
		characters      = new ArrayList<Character>();
		this.levelWorld = levelWorld;
	}
	
	/**
	 * Add a character to the pool.
	 * @param character
	 */
	public void add(Character character) {
		// Add our character.
		characters.add(character);
		// Add our characters physics box to the level world.
		levelWorld.getPhysicsWorld().addBox(character.getPhysicsBox());
	}
	
	/**
	 * Update the characters in the pool.
	 * Input should also be processed for any characters who are players.
	 */
	public void update() {
		for(Character character : characters) {
			// If this is a player then process input.
			if(character instanceof Player) {
				((Player) character).processInput();
			}
			// Update the character
			character.update();
		}
	}
	
	/**
	 * Iterate over the item pool any dead characters.
	 * If the dead character is a player, do not remove them.
	 */
	public void removeDeadCharacters() {
		for (Iterator<Character> iterator = characters.iterator(); iterator.hasNext();) {
			Character character = iterator.next();
		    // Remove this character from the level if their health is depleted 
			// and they are NOT a player.
		    if(character.getHealthStatus().isHealthDepleted() && !(character instanceof Player)) {
		        iterator.remove();
		    }
		}
	}
	
	/**
	 * Draw all characters.
	 * @param batch
	 */
	public void draw(SpriteBatch batch, LevelCamera camera) {
		for(Character character : characters) {
			character.draw(batch, camera);
		}
	}
}
