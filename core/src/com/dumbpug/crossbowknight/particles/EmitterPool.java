package com.dumbpug.crossbowknight.particles;

import java.util.ArrayList;
import java.util.Iterator;

 /**
 * Manager for world Emitters.
 * @author nikolas.howard
 */
public class EmitterPool {
    /** The list of emitters. */
    private ArrayList<Emitter> emitters;

    /**
     * Create a new instance of the EmitterPool class.
     */
    public EmitterPool() { emitters = new ArrayList<Emitter>(); }

    /**
     * Add a item to the pool.
     * Returns true if the item was successfully added.
     * @param emitter
     */
    public void add(Emitter emitter) { emitters.add(emitter); }
    
    /**
     * Return true if the emitter pool contains the specified emitter.
     * @param emitter
     * @return contains emitter.
     */
    public boolean contains(Emitter emitter) { return emitters.contains(emitter); }

    /**
    * Iterate over the emitter pool and update them before remove any emitters which are no longer active.
    */
    public void update() {
        Iterator<Emitter> emitterIterator = emitters.iterator();
        while (emitterIterator.hasNext()) {
            Emitter currentEmitter = emitterIterator.next();
            if (currentEmitter.isAlive()) {
                currentEmitter.update();
            } else {
                emitterIterator.remove();
            }
        }
    }

    /**
     * Draw everything in our emitter pool.
     */
    public void draw() {
        // Draw the emitters.
        for(Emitter emitter : emitters) {
            emitter.draw();
        }
    }
}
