package com.dumbpug.crossbowknight.graphics;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Holds entity resources.
 * @author nikolas.howard
 */
public class EntityResources {
	/** Singleton instance of entity resources. */
	private static EntityResources instance;
	
	/** Maps which hold entity textures. */
	private HashMap<Projectile, Texture> projectileTextures = new HashMap<Projectile, Texture>();

	/**
	 * Textures for projectiles.
	 */
	public enum Projectile {
		BOLT_BASIC,
	}
	
	/**
	 * Creates an instance of the EntityResources class.
	 */
	private EntityResources() {
		// Populate our projectile textures map.
		for(Projectile projectileTexture : Projectile.values()) {
			String texturePath = "graphics/projectiles/" + projectileTexture + ".png";
			projectileTextures.put(projectileTexture, new Texture(texturePath));
		}
	}
	
	/**
	 * Get our EntityResources singleton instance.
	 * @return EntityResources
	 */
	public static EntityResources getEntityResources() {
		if(instance == null) {
			instance = new EntityResources();
		}
		return instance;
	}
	
	/**
	 * Get projectile texture.
	 * @param projectile
	 * @return texture
	 */
	public Texture getProjectileTexture(Projectile projectile) {
		return this.projectileTextures.get(projectile);
	}
}
