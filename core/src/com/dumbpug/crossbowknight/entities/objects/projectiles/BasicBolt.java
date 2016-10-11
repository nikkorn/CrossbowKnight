package com.dumbpug.crossbowknight.entities.objects.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.EntityResources;

/**
 * The most basic type of bolt in the game.
 * Created by nik on 10/10/16.
 */
public class BasicBolt extends Bolt {
	
    /**
     * Create a new instance of the BasicBolt class.
     * @param x
     * @param y
     * @param angle
     * @param velocity
     */
    public BasicBolt(float x, float y, float angle, float velocity) {
        super(x, y, angle, velocity);
    }

    @Override
    public void draw(SpriteBatch batch, float levelCameraXOffset, float levelCameraYOffset) {
        // Set the sprite up.
    	float hitboxPadding = (getBoltHeadSize()-getPhysicsBox().getWidth())/2;
    	float boltSpritePositionX = (getPhysicsBox().getX() - hitboxPadding) * C.LAYOUT_MULTIPLIER;
    	float boltSpritePositionY = (getPhysicsBox().getY() - (getBoltHeadSize() + hitboxPadding)) * C.LAYOUT_MULTIPLIER;
        getBoltSprite().setSize(getBoltHeadSize() * C.LAYOUT_MULTIPLIER, (getBoltHeadSize()*2) * C.LAYOUT_MULTIPLIER);
        getBoltSprite().setOrigin(getBoltSprite().getWidth()/2, getBoltSprite().getWidth()*1.5f); // TODO May be 0.5f
        getBoltSprite().setPosition(boltSpritePositionX + levelCameraXOffset, boltSpritePositionY + levelCameraYOffset);
        getBoltSprite().setRotation((float) ((BoltPhysicsBox) this.getPhysicsBox()).getLastAngleOfVelocity());
        // Draw the sprite.
        getBoltSprite().draw(batch);
    }

    @Override
    public Sprite getBoltSprite() { return EntityResources.getEntityResources().getProjectileSprite(this.getProjectileType()); }

    @Override
    public ProjectileType getProjectileType() { return ProjectileType.BOLT_BASIC; }

    @Override
    public float getBaseDamage() { return 12f; }

    @Override
    public float getBoltHeadSize() { return C.PROJECTILE_BASE_BOLT_HEAD_SIZE; }
}
