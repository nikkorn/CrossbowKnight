package com.dumbpug.crossbowknight.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * Represents a Particle.
 * Created by nik on 12/11/16.
 */
public abstract class Particle {
	/** The life of this particle in millis. */
	private long life = C.PARTICLES_DEFAULT_LIFE;
	/** The time this particle was created. */
	private long timeOfCreation = System.currentTimeMillis();
    
    /**
     * Update the particle.
     */
    abstract void update();

	/**
	 * Get the X position of this particle.
	 * @return x position.
     */
	abstract float getPositionX();

	/**
	 * Get the Y position of this particle.
	 * @return y position.
	 */
	abstract float getPositionY();

    /**
     * Draw the particle.
     * @param batch
     */
    abstract void draw(SpriteBatch batch);
    
    /**
     * Called when this particle is first created.
     */
    abstract void onCreation();
    
    /**
     * Called when this particle dies.
     */
    abstract void onEnd();

    /**
     * Get whether this particle is alive.
     * @return isAlive
     */
	public boolean isAlive() { return getRemainingLife() > 0; }
	
	/**
     * Get the remaining life of this particle in millis.
     * @return remaining life
     */
	public long getRemainingLife() { 
		long remainingLife = life - (System.currentTimeMillis() - timeOfCreation);
		return remainingLife > 0 ? remainingLife : 0; 
	}
	
    /**
     * Get the life of this particle in millis.
     * @return life
     */
	public long getLife() { return life; }

	/**
	 * Set the life of this particle in millis.
	 * @param life
	 */
	public void setLife(long life) { this.life = life; }
}
