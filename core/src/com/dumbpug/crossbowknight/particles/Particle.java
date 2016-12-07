package com.dumbpug.crossbowknight.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nik on 12/11/16.
 */
public abstract class Particle {
	/** The position of this particle. */
	private float posX, posY;
	/** The life of this particle in millis. */
	private long life = C.PARTICLES_DEFAULT_LIFE;
	/** The time this particle was created. */
	private long timeOfCreation = System.currentTimeMillis();
    
    public Particle() {}
    
    /**
     * Update the particle.
     */
    abstract void update();

    /**
     * Draw the particle.
     * @param batch
     */
    abstract void draw(SpriteBatch batch);
    
    /**
     * Called when this particle is first created.
     * @param batch
     */
    abstract void onCreation();
    
    /**
     * Called when this particle dies.
     * @param batch
     */
    abstract void onEnd();

    /**
     * Get whether this particle is alive.
     * @return isAlive
     */
	public boolean isAlive() { return getRemainingLife() > 0; }
	
	/**
     * Get the remaining life of this particle in millis.
     * @return life
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
