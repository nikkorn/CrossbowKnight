package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import java.util.Random;
import java.util.Scanner;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemCategory;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemRarity;
import com.dumbpug.crossbowknight.entities.objects.items.Item.ItemType;
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
				return generateShield();
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
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new BasicBolt();
						}
					});
				break;
			case UNCOMMON:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new HeavyBolt();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new SleekBolt();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new BarbedBolt();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new IgnitedBolt();
						}
					});
				break;
			case RARE:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new ExplosiveBolt();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new RubberBolt();
						}
					});
				break;
			case ULTRA_RARE:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new AntiqueBolt();
						}
					});
				break;
			case MYTHICAL:
				break;
		}
		Item winningAmmo = itemGeneratorList.selectToken(random).generate();
		// Set the quantity on the ammo if we have it and it is not infinite.
		if(winningAmmo != null && winningAmmo.isQuantifiable() && (winningAmmo.getQuantity() != -1)) {
			winningAmmo.setQuantity(quantity);
		}
		return winningAmmo;
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
					.add(new IItemGenerator() {
						public Item generate() {
							return new HealthPotion();
						}
					});
				break;
			case UNCOMMON:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new HealthPotionLarge();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new DefensePotion();
						}
					});
				break;
			case RARE:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new HealthPotionSuper();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new SkillPotion();
						}
					})
					.add(new IItemGenerator() {
						public Item generate() {
							return new StrengthPotion();
						}
					});
				break;
			case ULTRA_RARE:
				itemGeneratorList
					.add(new IItemGenerator() {
						public Item generate() {
							return new ChaosPotion();
						}
					});
				break;
			case MYTHICAL:
				break;
		}
		return itemGeneratorList.selectToken(random).generate();
	}
	
	/**
	 * Generate an shield item.
	 * @return shield item.
	 */
	private Item generateShield() {
		// Generate a rarity for this shield item.
		Material material = materialTokens.selectToken(random);
		// Generate an available item type id.
		int itemTypeId = getRandomItemTypeId(ItemCategory.SHIELD, material);
		// Create the shield item. 
		Shield generatedShield = new Shield(itemTypeId, material);
		// Generate a rarity for this shield item.
		ItemRarity rarity = rarityTokens.selectToken(random);
		generatedShield.setRarity(rarity);
		
		// TODO Generate some buffs and characteristics for the shield.
		generatedShield.setTotalDurability(100);
		generatedShield.setCurrentDurability(100);
		
		// Return the shield.
		return generatedShield;
	}
	
	/**
	 * Takes an item category and material and returns an available 
	 * item type id for the combination.
	 * @param category
	 * @param material
	 * @return available id
	 */
	private int getRandomItemTypeId(ItemCategory category, Material material) {
		int matchingTypeCount       = 0;
		String targetItemTypePrefix = category.toString().toLowerCase() + "_" + material.toString().toLowerCase() + "_";
		for(ItemType type : Item.ItemType.values()) {
			if(type.toString().toLowerCase().startsWith(targetItemTypePrefix)) {
				matchingTypeCount++;
			}
		}
		if(matchingTypeCount > 0) {
			return random.nextInt(matchingTypeCount);
		} else {
			throw new RuntimeException("There are no item types with prefix " + targetItemTypePrefix);
		}
	}
	
	// ...
	
	/**
	 * Test item generation.
	 * @param args
	 */
	public static void main(String[] args) {
		DynamicItemFactory dif = new DynamicItemFactory();
		Scanner scanner        = new Scanner(System.in);
		String input           = scanner.nextLine();
		
		while(!input.equals("exit")) {
			Item item = dif.generateItem(true);
			
			if(item != null) {
				System.out.println("Got      : " + item.getName());
				System.out.println("Quantity : " + item.getQuantity());
			} else {
				System.out.println("No Item!");
			}
			
			input = scanner.nextLine();
		}
		
		scanner.close();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

