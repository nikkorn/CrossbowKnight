package com.dumbpug.crossbowknight.entities.objects.items.dynamic;

import com.dumbpug.crossbowknight.entities.objects.items.Item;

/**
 * Functional interface for creating an item.
 * @author nikolas.howard
 *
 */
public interface IItemGenerator {
	public Item generate();
}
