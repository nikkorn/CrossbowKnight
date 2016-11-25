package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import java.util.ArrayList;
import java.util.Random;

/**
 * A list of tokens from which random selections can be made.
 * @author nikolas.howard
 * @param <T>
 */
public class DynamicItemFactoryTokenList<T> {
	/** The list of tokens. */
	protected ArrayList<T> list;
	
	/**
	 * Create a new instance of the DynamicItemFactoryTokenList class.
	 */
	public DynamicItemFactoryTokenList() { list = new ArrayList<T>(); }
	
	/**
	 * Add the token to the token list as many times as defined by count.
	 * @param token
	 * @param count
	 */
	public void addTokens(T token, int count) {
		for(int i = 0; i < count; i++) {
			list.add(token);
		}
	}
	
	/**
	 * Randomly pick a token in the list and return it.
	 * @param random
	 * @return token
	 */
	public T selectToken(Random random) {
		int selectionIndex = random.nextInt(list.size());
		return list.get(selectionIndex);
	}
}
