package com.dumbpug.crossbowknight.resources;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Helmet;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Shield;

/**
 * Holds character resources.
 * @author nikolas.howard
 */
public class CharacterResources {
	/** Singleton instance of character resources. */
	private static CharacterResources instance;
	
	/** Maps which hold equipped shield sprites. */
	private HashMap<String, Sprite> equippedShieldSprites = new HashMap<String, Sprite>();
	
	/** Maps which hold equipped helmet sprites. */
	private HashMap<String, Sprite> equippedHelmetSprites = new HashMap<String, Sprite>();
	
	/**
	 * Get our CharacterResources singleton instance.
	 * @return CharacterResources
	 */
	public static CharacterResources getCharacterResources() {
		if(instance == null) {
			instance = new CharacterResources();
		}
		return instance;
	}
	
	/**
	 * Get equipped shield sprite.
	 * @param shield
	 * @return equipped shield sprite
	 */
	public Sprite getEquippedShieldSprite(Shield shield) {
		Sprite sprite = null;
		// Get the sprite if we have it cached, or create it if we don't.
		if(equippedShieldSprites.containsKey(shield.getType().toString())) {
			sprite = equippedShieldSprites.get(shield.getType().toString());
		} else {
			String texturePath = "graphics/shields/" + shield.getType().toString() + ".png";
			sprite             = new Sprite(new Texture(texturePath));
			equippedShieldSprites.put(shield.getType().toString(), sprite);
		}
		return sprite;
	}
	
	/**
	 * Get equipped helmet sprite.
	 * @param helmet
	 * @return equipped helmet sprite
	 */
	public Sprite getEquippedHelmetSprite(Helmet helmet) {
		Sprite sprite = null;
		// Get the sprite if we have it cached, or create it if we don't.
		if(equippedHelmetSprites.containsKey(helmet.getType().toString())) {
			sprite = equippedHelmetSprites.get(helmet.getType().toString());
		} else {
			String texturePath = "graphics/helmets/" + helmet.getType().toString() + ".png";
			sprite             = new Sprite(new Texture(texturePath));
			equippedShieldSprites.put(helmet.getType().toString(), sprite);
		}
		return sprite;
	}
}
