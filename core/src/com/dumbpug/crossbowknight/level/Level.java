package com.dumbpug.crossbowknight.level;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.camera.PlayerCameraPositionProvider;
import com.dumbpug.crossbowknight.entities.characters.CharacterEffectDrawer;
import com.dumbpug.crossbowknight.entities.characters.player.EquipmentUsage;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.Gold;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPhysicsBox;
import com.dumbpug.crossbowknight.entities.objects.items.Key;
import com.dumbpug.crossbowknight.entities.objects.items.Material;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.AntiqueBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.BarbedBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.BasicBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.ExplosiveBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.HeavyBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.IgnitedBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.RubberBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.SleekBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.ZanyBolt;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Shield;
import com.dumbpug.crossbowknight.entities.objects.items.potions.ChaosPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.DefensePotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotionLarge;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotionSuper;
import com.dumbpug.crossbowknight.entities.objects.items.potions.SkillPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.StrengthPotion;
import com.dumbpug.crossbowknight.level.generator.LevelWorldGenerator;
import com.dumbpug.crossbowknight.tiles.TileInteractionFacilitator;
import com.dumbpug.crossbowknight.tiles.door.Door;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** The level camera. */
	private LevelCamera camera;
	/** The level drawer. */
	private LevelDrawer levelDrawer;
	/** The level world generator. */
	private LevelWorldGenerator levelWorldGenerator;
	/** The level world list. */
	private LevelWorldList worlds;
	
	// -----------------------------------
	// ---------- Level Entities ---------
	/** The Player. */
	private Player player;
	// -----------------------------------
	
	/**
	 * Create a new instance of the Level class.
	 */
	public Level(LevelWorld initialLevelWorld) {
		// Create our level drawer.
		this.levelDrawer = new LevelDrawer(this);
		// Create our level world list.
		this.worlds = new LevelWorldList();
		// Add our initial level world to our level world list.
		this.worlds.setActive(initialLevelWorld);
		// Create our level world generator.
		this.levelWorldGenerator = new LevelWorldGenerator();
		// Initialise our player.
		initialisePlayer();
		// Set up our level camera.
		this.camera = new LevelCamera(new PlayerCameraPositionProvider(player));
		// In order to draw our player effects, we need a level camera.
		player.setCharacterEffectsDrawer(new CharacterEffectDrawer(player, this.getLevelCamera()));
	}
	
	/**
	 * Initialise the player.
	 */
	public void initialisePlayer() {
		// Create our player. // TODO Add actual spawn point!!!
		player = new Player(50, 50);
		// Set our players equipment usage helper.
		player.getEquipment().setEquipmentUsage(new EquipmentUsage(this));
		// Add our player to the initial level world.
		this.worlds.getActive().getCharacterPool().add(player);
		// Set the level world tile interaction facilitator.
		this.worlds.getActive().setTileInteractionFacilitator(new TileInteractionFacilitator(player, this.worlds.getActive()));
	}
	
	/**
	 * Update the level.
	 */
	public void update() {
		// Check for Door usage (Player moving to new LevelWorld).
		handleLevelWorldSwitch();
		
		// If we do not have an active level world we can't carry on.
		if(this.worlds.getActive() == null) {
			return;
		}
		
		// Update the active level world.
		this.worlds.getActive().update();
		
		// ...
		
		// TEST ADDING AN ITEM TO THE WORLD TODO REMOVE!
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) {
			Item item = null;
			// Randomly generate an item!
			Item.ItemType type = Item.ItemType.values()[new Random().nextInt(Item.ItemType.values().length)];
			switch(type) {
			case BOLT_BASIC:
				item = new BasicBolt();
				break;
			case BOLT_HEAVY:
				item = new HeavyBolt();
				break;
			case BOLT_IGNITED:
				item = new IgnitedBolt();
				break;
			case BOLT_SLEEK:
				item = new SleekBolt();
				break;
			case BOLT_RUBBER:
				item = new RubberBolt();
				break;
			case BOLT_ANTIQUE:
				item = new AntiqueBolt();
				break;
			case BOLT_EXPLOSIVE:
				item = new ExplosiveBolt();
				item.setQuantity(50);
				break;
			case BOLT_ZANY:
				item = new ZanyBolt();
				break;
			case BOLT_BARBED:
				item = new BarbedBolt();
				break;
			case GOLD:
				item = new Gold();
				item.setQuantity(new Random().nextInt(100));
				break;
			case HEALTH_POTION:
				item = new HealthPotion();
				break;
			case HEALTH_POTION_LARGE:
				item = new HealthPotionLarge();
				break;
			case HEALTH_POTION_SUPER:
				item = new HealthPotionSuper();
				break;
			case CHAOS_POTION:
				item = new ChaosPotion();
				break;
			case DEFENSE_POTION:
				item = new DefensePotion();
				break;
			case SKILL_POTION:
				item = new SkillPotion();
				break;
			case STRENGTH_POTION:
				item = new StrengthPotion();
				break;
			case KEY:
				item = new Key();
				break;
			case SHIELD_WOOD_0:
				Shield shield = new Shield(0, Material.WOOD);
				shield.setTotalDurability(10);
				shield.setCurrentDurability(10);
				shield.setDefenseBuff(0.4f);
				shield.setIntegrity(0.8f);
				item = shield;
				break;
			default:
				break;
			}
			if(item != null) {
				item.setItemPhysicsBox(new ItemPhysicsBox(item, player.getPhysicsBox().getX(), player.getPhysicsBox().getY() + 50));
				this.worlds.getActive().getItemPool().add(item);
			}
		}
	}

	/**
	 * Check whether we need to switch to another level world.
	 */
	private void handleLevelWorldSwitch() {
		LevelWorld currentLevelWorld = this.worlds.getActive();
		Door activeDoor = currentLevelWorld.getActiveDoor();
		// If we have an active door then we need to move to another level world.
		if(activeDoor != null) {
			// Try to get the target level world from our world list.
			LevelWorld targetLevelWorld = worlds.getByName(activeDoor.getTarget().levelWorld);
			// If we couldn't get this level world then it doesn't already exist. 
			// In this case generate it, using the active door to connect to a door in the generated world.
			targetLevelWorld = levelWorldGenerator.generateLevelWorld(activeDoor, currentLevelWorld);
			// We need to reset the level world we are coming from so that it is fresh for if we revisit it.
			currentLevelWorld.reset();
			
			// TODO Add the player to the new level world at the position of the connecting door.
			
			// Set the target level world as the active one.
			worlds.setActive(targetLevelWorld);
		}
	}

	/**
	 * Get the level camera.
	 * @return level camera.
	 */
	public LevelCamera getLevelCamera() { return camera; }
	
	/**
	 * Get the player.
	 * @return player.
	 */
	public Player getPlayer() { return player; }
	
	/**
	 * Get the active level world.
	 * @return active level world.
	 */
	public LevelWorld getActiveLevelWorld() { return this.worlds.getActive(); }
	
	/**
	 * Draw this level
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw our level using our level drawer.
		levelDrawer.draw(batch);	
	}
}
