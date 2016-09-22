package com.dumbpug.crossbowknight.audio;

import java.util.HashMap;
import com.badlogic.gdx.audio.Sound;

/**
 * Provides all game audio.
 * @author nikolas.howard
 */
public class Audio {
	/** All cached sounds effects. */
	private HashMap<SoundEffect, Sound> cachedSoundEffects = new HashMap<SoundEffect, Sound>();
	/** All cached music. */
	private HashMap<Music, Sound> cachedMusic = new HashMap<Music, Sound>();
	
	/** 
	 * All game sound effects.
	 */
	public enum SoundEffect {
		BLIP_LOW,
		BLIP_HIGH,
		BLIP_SELECT,
		CHEST_OPEN,
		DOOR_OPEN,
		JUMP,
		LANDING_HEAVY,
		LANDING_SOFT,
		PICKUP
	}
	
	/** 
	 * All game music.
	 */
    public enum Music {
		
	}

    /**
	 * Get sound effect.
	 * @return sound effect.
	 */
	public Sound getSoundEffect(SoundEffect soundEffect) {
		return null;
	}
	
	/**
	 * Get music track.
	 * @return music track.
	 */
	public Sound getMusic() {
		return null;
	}
}
