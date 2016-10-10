package com.dumbpug.crossbowknight.resources;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.crossbowknight.entities.objects.projectiles.ProjectileType;

/**
 * Holds entity resources.
 * @author nikolas.howard
 */
public class EntityResources {
	/** Singleton instance of entity resources. */
	private static EntityResources instance;
	
	/** Maps which hold entity entity sprites. */
	private HashMap<ProjectileType, Sprite> projectileSprite = new HashMap<ProjectileType, Sprite>();
	
	/**
	 * Creates an instance of the EntityResources class.
	 */
	private EntityResources() {
		// Populate our projectile sprites map.
		for(ProjectileType projectileType : ProjectileType.values()) {
			String texturePath = "graphics/projectiles/" + projectileType + ".png";
			projectileSprite.put(projectileType, new Sprite(new Texture(texturePath)));
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
	 * @param projectileType
	 * @return texture
	 */
	public Sprite getProjectileSprite(ProjectileType projectileType) {
		return this.projectileSprite.get(projectileType);
	}
}
