package com.dumbpug.crossbowknight.audio;

import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.dumbpug.crossbowknight.C;

/**
 * Provides all game audio.
 * @author nikolas.howard
 */
public class Audio {
	/** All cached sounds effects. */
	private static HashMap<SoundEffect, Sound> cachedSoundEffects = new HashMap<SoundEffect, Sound>();
	/** All cached music. */
	private static HashMap<Music, Sound> cachedMusic = new HashMap<Music, Sound>();
	
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
		MAIN_THEME
	}

	/**
	 * Load all audio files.
 	 */
	public static void loadAudio() {
		// Populate our sound effect map.
		for(SoundEffect soundEffect : SoundEffect.values()) {
			String soundEffectPath = C.AUDIO_SOUND_EFFECTS_DIR + "/" + soundEffect + ".wav";
			cachedSoundEffects.put(soundEffect, Gdx.audio.newSound(Gdx.files.internal(soundEffectPath)));
		}
		// Populate our music map.
		for(Music music : Music.values()) {
			String musicPath = C.AUDIO_MUSIC_DIR + "/" + music + ".wav";
			cachedMusic.put(music, Gdx.audio.newSound(Gdx.files.internal(musicPath)));
		}
	}

    /**
	 * Get sound effect.
	 * @return sound effect.
	 */
	public static Sound getSoundEffect(SoundEffect soundEffect) {
		return cachedSoundEffects.get(soundEffect);
	}
	
	/**
	 * Get music track.
	 * @return music track.
	 */
	public static Sound getMusic(Music music) {
		return cachedMusic.get(music);
	}
}
