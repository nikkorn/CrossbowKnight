package com.dumbpug.crossbowknight.level;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.camera.LevelCamera;
import com.dumbpug.crossbowknight.camera.PlayerCameraPositionProvider;
import com.dumbpug.crossbowknight.entities.characters.player.EquipmentUsage;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.entities.objects.items.Gold;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ItemPhysicsBox;
import com.dumbpug.crossbowknight.entities.objects.items.Key;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.AntiqueBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.BarbedBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.BasicBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.ExplosiveBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.HeavyBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.IgnitedBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.RubberBolt;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.SleekBolt;
import com.dumbpug.crossbowknight.entities.objects.items.potions.ChaosPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.DefensePotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotionLarge;
import com.dumbpug.crossbowknight.entities.objects.items.potions.HealthPotionSuper;
import com.dumbpug.crossbowknight.entities.objects.items.potions.SkillPotion;
import com.dumbpug.crossbowknight.entities.objects.items.potions.StrengthPotion;

/**
 * Represents a game level.
 * @author nikolas.howard
 */
public class Level {
	/** The name of this level. */
	private String name = "";
	/** The level camera. */
	private LevelCamera camera;
	/** The level drawer. */
	private LevelDrawer levelDrawer;
	/** The level physics world. */
	private LevelWorld levelWorld;
	
	// -----------------------------------
	// ---------- Level Entities ---------
	/** The Player. */
	private Player player;
	// -----------------------------------
	
	/**
	 * Create a new instance of the Level class.
	 */
	public Level() {
		// Create our level drawer.
		this.levelDrawer = new LevelDrawer(this);
		// Create our level world..
		this.levelWorld = new LevelWorld();
		// Initialise our player.
		initialisePlayer();
		// Set up our level camera.
		this.camera = new LevelCamera(new PlayerCameraPositionProvider(player));
	}
	
	/**
	 * Initialise the player.
	 */
	public void initialisePlayer() {
		// Create our player. // TODO Add actual spawn point!!!
		player = new Player(50, 50);
		// Set our players equipment usage helper.
		player.getEquipment().setEquipmentUsage(new EquipmentUsage(this));
		// Add our player to the level world.
		this.levelWorld.addPlayer(player);
	}
	
	/**
	 * Update the level.
	 */
	public void update() {
		// ------------ Update the level world. -----------
		levelWorld.update();
		// --------------- Handle input. ------------------
		player.processInput();
		
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
			default:
				break;
			}
			if(item != null) {
				item.setItemPhysicsBox(new ItemPhysicsBox(item, player.getPhysicsBox().getX(), player.getPhysicsBox().getY() + 50));
				levelWorld.getItemPool().add(item);
			}
		}
	}

	/**
	 * Get the name of this level.
	 * @return name
	 */
	public String getName() { return name; }

	/**
	 * Set the name of this level.
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	
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
	 * Get the level world.
	 * @return level world.
	 */
	public LevelWorld getLevelWorld() { return this.levelWorld; }
	
	/**
	 * Draw this level
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Draw our level using our level drawer.
		levelDrawer.draw(batch);	
	}
}
