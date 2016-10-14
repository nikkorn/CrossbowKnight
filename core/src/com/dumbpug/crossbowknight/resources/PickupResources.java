package com.dumbpug.crossbowknight.resources;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.crossbowknight.entities.objects.pickups.Pickup.PickupType;

/**
 * Holds pickup resources.
 * @author nikolas.howard
 */
public class PickupResources {
	/** Singleton instance of pickup resources. */
	private static PickupResources instance;
	/** Maps which holds textures for pickup types. */
	private HashMap<PickupType, Texture> pickupTextures = new HashMap<PickupType, Texture>();
	
	/**
	 * Creates an instance of the PickupResources class.
	 */
	private PickupResources() {
		// Populate our pickup type textures map.
		for(PickupType pickupType : PickupType.values()) {
			String texturePath = "graphics/pickups/PICKUP_" + pickupType + ".png";
			pickupTextures.put(pickupType, new Texture(texturePath));
		}
	}
	
	/**
	 * Get our PickupResources singleton instance.
	 * @return PickupResources
	 */
	public static PickupResources getPickupResources() {
		if(instance == null) {
			instance = new PickupResources();
		}
		return instance;
	}
	
	/**
	 * Get pickup texture.
	 * @param pickupType
	 * @return texture
	 */
	public Texture getPickupTexture(PickupType pickupType) {
		return this.pickupTextures.get(pickupType);
	}
}
