package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import java.util.Random;

/**
 * A list of individual item generator tokens.
 * @author nikolas.howard
 * @param <IItemGenerator>
 */
public class ItemGeneratorList extends DynamicItemFactoryTokenList<IItemGenerator> {
	
	/**
	 * Add the token to the token list as many times as defined by count.
	 * @param token
	 * @param count
	 */
	public ItemGeneratorList add(IItemGenerator itemGenerator) {
		list.add(itemGenerator);
		return this;
	}
	
	/**
	 * Add the token to the token list as many times as defined by count.
	 * @param token
	 * @param count
	 */
	public ItemGeneratorList add(IItemGenerator itemGenerator, int count) {
		for(int i = 0; i < count; i++) {
			list.add(itemGenerator);
		}
		return this;
	}
	
	/**
	 * Randomly pick an item generator in the list and return it.
	 * @param random
	 * @return item generator
	 */
	public IItemGenerator selectToken(Random random) {
		int selectionIndex = random.nextInt(list.size());
		return list.get(selectionIndex);
	}
}
