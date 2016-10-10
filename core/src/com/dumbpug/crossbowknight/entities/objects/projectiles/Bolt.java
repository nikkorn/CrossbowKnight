package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.crossbowknight.resources.EntityResources.Projectile;
import com.dumbpug.nbp.NBPBloom;

/**
 * Created by nik on 09/10/16.
 */
public abstract class Bolt {
	/** The sprite for this bolt */
	protected Sprite boltSprite;
	/** The physics box for this bolt. */
	protected BoltPhysicsBox boltPhysicsBox;
	
	public Bolt() {
		// Set the bolt sprite.
		boltSprite = new Sprite(getBoltTexture());
	}

	/**
	 * Called when BoltPhysicsBox is pushed by a bloom. 
	 * @param bloom
	 * @param angleOfForce
	 * @param force
	 */
	public void onPush(NBPBloom bloom, float angleOfForce, float force) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Get the texture for this bolt.
	 * @return texture.
	 */
	public abstract Texture getBoltTexture();
	
	/**
	 * Get the type of this bolt.
	 * @return
	 */
	public abstract Projectile getBoltType();
}
