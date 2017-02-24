package com.dumbpug.crossbowknight.entities.characters;

import com.dumbpug.crossbowknight.C;

/**
 * Character stats.
 */
public class Stats {
    /** The characters xp. */
    private int xp = 0;
    /** The characters attribute levels. */
    private int vitality     = 0;
    private int strength     = 0;
    private int defense      = 0;
    private int luck         = 0;
    private int marksmanship = 0;
    private int agility      = 0;

    /**
     * Attribute types.
     */
    public enum Attribute {
        VITALITY,
        STRENGTH,
        DEFENSE,
        LUCK,
        MARKSMANSHIP,
        AGILITY
    }

    /**
     * Get the characters overall Xp.
     * @return Xp
     */
    public int getXp() { return this.xp; }

    /**
     * Set the characters overall Xp.
     * @param xp
     */
    public void setXp(int xp) { this.xp = xp; }
    
    /**
     * Add Xp and return a boolean indicating whether this
     * additional Xp caused up to level up.
     * @param xp
     * @return levelled up.
     */
    public boolean addXp(int xp) {
    	int level = getLevel();
    	this.xp += xp;
    	return level < getLevel();
    }

    /**
     * Get the characters level based on their xp.
     * @return the characters level.
     */
    public int getLevel() {
        int lastLevel = 0;
        for(int level = 0; level <= C.CHARACTER_MAX_LEVEL; level++) {
            if(C.STATS_LEVEL_XP_MODIFIER*(level*(level*C.STATS_LEVEL_XP_MODIFIER)) > this.xp) {
                break;
            }
            lastLevel = level;
        }
        return lastLevel + 1;
    }
    
    /**
     * Get the XP required to reach the specified level.
     * @return The Required XP.
     */
    public int getXpRequiredForLevel(int level) {
        return C.STATS_LEVEL_XP_MODIFIER*(level*(level*C.STATS_LEVEL_XP_MODIFIER));
    }

    /**
     * Set the characters level.
     * This is actually just a convenience method as a
     * characters level is derived from the xp directly.
     * @param level
     */
    public void setLevel(int level) {
        this.xp = C.STATS_LEVEL_XP_MODIFIER*(level*(level*C.STATS_LEVEL_XP_MODIFIER));
    }

    /**
     * Get the number of unspent attribute levels.
     * @return unspent attribute levels.
     */
    public int getUnspentAttributeLevels() {
        int attributeLevelCount = vitality+strength+defense+luck+marksmanship+agility;
        return (this.getLevel()-1) - attributeLevelCount;
    }

    /**
     * Get the level of the provided attribute.
     * @param attribute The attribute.
     * @return attribute level.
     */
    public int getAttributeLevel(Attribute attribute) {
        switch(attribute) {
            case VITALITY:
                return vitality;
            case STRENGTH:
                return strength;
            case DEFENSE:
                return defense;
            case LUCK:
                return luck;
            case MARKSMANSHIP:
                return marksmanship;
            case AGILITY:
                return agility;
        }
        return 0;
    }

    /**
     * Set the level of the provided attribute.
     * @param attribute
     * @param level
     */
    public void setAttributeLevel(Attribute attribute, int level) {
        switch(attribute) {
            case VITALITY:
                vitality = level;
            case STRENGTH:
                strength  = level;
            case DEFENSE:
                defense  = level;
            case LUCK:
                luck  = level;
            case MARKSMANSHIP:
                marksmanship  = level;
            case AGILITY:
                agility  = level;
        }
    }

    /**
     * Get the points of the provided attribute.
     * @param attribute The attribute.
     * @return attribute level.
     */
    public int getAttributePoints(Attribute attribute) {
        int levelBasedAttributeValue = this.getLevel()*C.STATS_ATTRIBUTE_LEVEL_MODIFIER;
        return levelBasedAttributeValue + (this.getAttributeLevel(attribute)*C.STATS_ATTRIBUTE_LEVEL_MODIFIER);
    }
    
    //-----------------------------------------------------
    // The statistics derived from our attribute levels. 
    //-----------------------------------------------------
    
    /**
     * Get the max health stat.
     * @return max health stat
     */
    public int getMaxHealth() { return C.STATS_BASE_HEALTH + getAttributePoints(Attribute.VITALITY); }
    
    /**
     * Get the max stamina stat.
     * @return max stamina stat.
     */
    public int getMaxStamina() { 
    	// Stamina is derived from both the strength and agility attributes.
    	int staminaPoints = getAttributePoints(Attribute.STRENGTH) + (getAttributePoints(Attribute.AGILITY) / 2);
    	return C.STATS_BASE_STAMINA + (staminaPoints/2);
    }
    
    /**
     * Get the stamina refill rate in milliseconds.
     * @return stamina refill rate in milliseconds.
     */
    public long getStaminaRefillRate() { return Math.max(0, C.STATS_BASE_STAMINA_REFILL_RATE - (getAttributePoints(Attribute.STRENGTH)*3)); }
    
    /**
     * Get the speed multiplier.
     * @return speed multiplier.
     */
    public float getSpeedMultiplier() { return 1f; } // TODO Tweak
    
    /**
     * Get the percentage of shots which will be critical shots.
     * @return critical shot percentage.
     */
    public float getCriticalShotChance() { return 0.05f; } // TODO Tweak
    
}
