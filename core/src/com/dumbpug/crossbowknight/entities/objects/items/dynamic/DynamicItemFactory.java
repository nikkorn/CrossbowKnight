package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import java.util.Random;
import java.util.Scanner;

import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemCategory;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemRarity;
import com.dumbpug.crossbowknight.entities.objects.items.Material;
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
 * Generates dynamic items.
 * @author nikolas.howard
 */
public class DynamicItemFactory {
	/** The token lists used in determining random selections. */
	private DynamicItemFactoryTokenList<ItemCategory> itemCategoryTokens;
	private DynamicItemFactoryTokenList<ItemRarity> rarityTokens;
	private DynamicItemFactoryTokenList<Material> materialTokens;
	/** The random number generator to use */
	private Random random = new Random();

	/**
	 * Create a new instance of the DynamicItemFactory class.
	 */
	public DynamicItemFactory() {
		createTokenLists();
	}
	
	/**
	 * Create the token lists.
	 */
	private void createTokenLists() {
		// Create the category list.
		itemCategoryTokens = new DynamicItemFactoryTokenList<ItemCategory>();
		itemCategoryTokens.addTokens(ItemCategory.CONSUMABLE, C.PROC_GEN_TOKENS_ITEM_CONSUMABLE);
		itemCategoryTokens.addTokens(ItemCategory.AMMO, C.PROC_GEN_TOKENS_ITEM_AMMO);
		itemCategoryTokens.addTokens(ItemCategory.HELMET, C.PROC_GEN_TOKENS_ITEM_HELMET);
		itemCategoryTokens.addTokens(ItemCategory.SHIELD, C.PROC_GEN_TOKENS_ITEM_SHIELD);
		itemCategoryTokens.addTokens(ItemCategory.STOCK, C.PROC_GEN_TOKENS_ITEM_STOCK);
		itemCategoryTokens.addTokens(ItemCategory.STRING, C.PROC_GEN_TOKENS_ITEM_STRING);
		itemCategoryTokens.addTokens(ItemCategory.SIGHT, C.PROC_GEN_TOKENS_ITEM_SIGHT);
		itemCategoryTokens.addTokens(ItemCategory.LIMBS, C.PROC_GEN_TOKENS_ITEM_LIMBS);
		// This entry represents a failed attempt to get an item.
		itemCategoryTokens.addTokens(ItemCategory.DEFAULT, C.PROC_GEN_TOKENS_ITEM_NOTHING);
		// Create the rarity list.
		rarityTokens = new DynamicItemFactoryTokenList<ItemRarity>();
		rarityTokens.addTokens(ItemRarity.COMMON, C.PROC_GEN_TOKENS_RARITY_COMMON);
		rarityTokens.addTokens(ItemRarity.UNCOMMON, C.PROC_GEN_TOKENS_RARITY_UNCOMMON);
		rarityTokens.addTokens(ItemRarity.RARE, C.PROC_GEN_TOKENS_RARITY_RARE);
		rarityTokens.addTokens(ItemRarity.ULTRA_RARE, C.PROC_GEN_TOKENS_RARITY_ULTRA_RARE);
		rarityTokens.addTokens(ItemRarity.MYTHICAL, C.PROC_GEN_TOKENS_RARITY_MYTHICAL);
		// Create the material list.
		materialTokens = new DynamicItemFactoryTokenList<Material>();
		materialTokens.addTokens(Material.WOOD, C.PROC_GEN_TOKENS_MATERIAL_WOOD);
		materialTokens.addTokens(Material.IRON, C.PROC_GEN_TOKENS_MATERIAL_IRON);
		materialTokens.addTokens(Material.STEEL, C.PROC_GEN_TOKENS_MATERIAL_STEEL);
		materialTokens.addTokens(Material.GLASS, C.PROC_GEN_TOKENS_MATERIAL_GLASS);
		materialTokens.addTokens(Material.CRYSTAL, C.PROC_GEN_TOKENS_MATERIAL_CRYSTAL);
		materialTokens.addTokens(Material.ONYX, C.PROC_GEN_TOKENS_MATERIAL_ONYX);
		materialTokens.addTokens(Material.MYTHRIL, C.PROC_GEN_TOKENS_MATERIAL_MYTHRIL);
	}
	
	/**
	 * Set the RNG to use in generating dynamic items.
	 * @param random
	 */
	public void setRNG(Random random) { this.random = random; }
	
	/**
	 * Set the RNG seed to use in generating dynamic items.
	 * @param seed
	 */
	public void setRNGSeed(long seed) { this.random.setSeed(seed); }
	
	/**
	 * Generate an item.
	 * @param canFail
	 * @return
	 */
	public Item generateItem(boolean canFail) {
		// Get an item category.
		ItemCategory category = itemCategoryTokens.selectToken(random);
		// We may have been a loser in getting an item. If so, and canFail is not true, then keep going till we get a category.
		while((!canFail) && category == null) {
			category = itemCategoryTokens.selectToken(random);
		}
		// Generate an item based on its category.
		switch(category) {
			case AMMO:
				return generateAmmo();
			case CONSUMABLE:
				return generateConsumable();
			case HELMET:
				System.out.println("Generate Helmet!");
				return null; // TODO
			case LIMBS:
				System.out.println("Generate Limbs!");
				return null; // TODO
			case SHIELD:
				System.out.println("Generate Shield!");
				return null; // TODO
			case SIGHT:
				System.out.println("Generate Sight!");
				return null; // TODO
			case STOCK:
				System.out.println("Generate Stock!");
				return null; // TODO
			case STRING:
				System.out.println("Generate String!");
				return null; // TODO
			case DEFAULT:
				// We failed in getting an item!
				return null;
			default:
				// We failed in getting an item!
				return null; 
		}
	}
	
	/**
	 * Generate an ammo item.
	 * @return ammo item.
	 */
	private Item generateAmmo() {
		// Generate a rarity for this ammo item.
		ItemRarity rarity = rarityTokens.selectToken(random);
		// Create an item generator list.
		ItemGeneratorList itemGeneratorList = new ItemGeneratorList();
		// We need to generate a quantity.
		int quantity = random.nextInt(3) + 1;
		// Generate an ammo item based on its rarity.
		switch(rarity) {
			case COMMON:
				break;
			case UNCOMMON:
				itemGeneratorList
					.add(() -> new BasicBolt(), quantity)
					.add(() -> new HeavyBolt(), quantity)
					.add(() -> new SleekBolt(), quantity)
					.add(() -> new BarbedBolt(), quantity)
					.add(() -> new IgnitedBolt(), quantity);
				break;
			case RARE:
				itemGeneratorList
					.add(() -> new ExplosiveBolt(), quantity)
					.add(() -> new RubberBolt(), quantity);
				break;
			case ULTRA_RARE:
				itemGeneratorList
					.add(() -> new AntiqueBolt(), quantity);
				break;
			case MYTHICAL:
				break;
		}
		return itemGeneratorList.selectToken(random).generate();
	}
	
	/**
	 * Generate a consumable item.
	 * @return consumable item.
	 */
	private Item generateConsumable() {
		// Generate a rarity for this consumable.
		ItemRarity rarity = rarityTokens.selectToken(random);
		// Create an item generator list.
		ItemGeneratorList itemGeneratorList = new ItemGeneratorList();
		// Generate a consumable based on its rarity.
		switch(rarity) {
			case COMMON:
				itemGeneratorList
					.add(() -> new HealthPotion());
				break;
			case UNCOMMON:
				itemGeneratorList
					.add(() -> new HealthPotionLarge())
					.add(() -> new DefensePotion());
				break;
			case RARE:
				itemGeneratorList
					.add(() -> new HealthPotionSuper())
					.add(() -> new SkillPotion())
					.add(() -> new StrengthPotion());
				break;
			case ULTRA_RARE:
				itemGeneratorList
					.add(() -> new ChaosPotion());
				break;
			case MYTHICAL:
				break;
		}
		return itemGeneratorList.selectToken(random).generate();
	}
	
	// ...
	
	/**
	 * Test item generation.
	 * @param args
	 */
	public static void main(String[] args) {
		DynamicItemFactory dif = new DynamicItemFactory();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			scanner.nextLine();
			
			Item item = dif.generateItem(true);
			
			if(item != null) {
				System.out.println("Got      : " + item.getName());
				System.out.println("Quantity : " + item.getQuantity());
			} else {
				System.out.println("No Item!");
			}
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

