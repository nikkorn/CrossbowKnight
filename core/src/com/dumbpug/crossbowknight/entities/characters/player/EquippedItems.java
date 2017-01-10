package com.dumbpug.crossbowknight.entities.characters.player;

import com.dumbpug.crossbowknight.audio.Audio;
import com.dumbpug.crossbowknight.entities.objects.items.Item;
import com.dumbpug.crossbowknight.entities.objects.items.ammo.Ammo;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Helmet;
import com.dumbpug.crossbowknight.entities.objects.items.dynamic.Shield;

/**
 * Represents the items that are equipped to the player.
 * @author nikolas.howard
 */
public class EquippedItems {
	/** The player. */
	private Player player;
	/** The equipment usage helper. */
	private EquipmentUsage equipmentUsage = null;
	
	/** The equipped item slots. */
	private Item primaryItemSlot = null;
	private Item secondaryItemSlot = null;
	
	/** The equipped projectile ammo. */
	private Ammo primaryAmmoSlot = null;
	private Ammo secondaryAmmoSlot = null;
	
	/** The equipped shield. */
	private Shield shield = null;
	
	/** The equipped helmet. */
	private Helmet helmet = null;
	
	/** The equipped crossbow parts. */
	// private CrossbowStock stock = null;
	// private CrossbowString string = null;
	// private CrossbowLimbs limbs = null;
	// private CrossbowSight sight = null;
	
	/**
	 * Create a new instance of the EquippedItems class.
	 * @param player
	 */
	public EquippedItems(Player player) { this.player = player; }
	
	/**
	 * Swap the primary and secondary item slots.
	 */
	public void swapItems() {
		// Only swap if we have something to swap.
		if(primaryItemSlot != null || secondaryItemSlot != null) {
			Item primary      = primaryItemSlot;
			primaryItemSlot   = secondaryItemSlot;
			secondaryItemSlot = primary;
			// Play swap sound.
			Audio.getSoundEffect(Audio.SoundEffect.SWAP).play();
		}
	}
	
	/**
	 * Swap the primary and secondary ammo slots.
	 */
	public void swapAmmo() {
		// Only swap if we have something to swap.
		if(primaryAmmoSlot != null || secondaryAmmoSlot != null) {
			Ammo primary      = primaryAmmoSlot;
			primaryAmmoSlot   = secondaryAmmoSlot;
			secondaryAmmoSlot = primary;
			// Play swap sound.
			Audio.getSoundEffect(Audio.SoundEffect.SWAP).play();
		}
	}
	
	/**
	 * Use the primary item.
	 */
	public void useItem() {
		// Try to use the primary item if there is one.
		if(primaryItemSlot != null) {
			// Was the item slot freed
			boolean itemSlotFreed = false;
			// There is an item! use it.
			if(primaryItemSlot.isQuantifiable()) {
				// Does the item have an infinite quantity?
				if(primaryItemSlot.getQuantity() == -1) {
					// Use the item! (can fail)
					equipmentUsage.onItemUse(this.player, primaryItemSlot);
				} else {
					// Use the item (can fail, if so don't decrement item quantity)
					if(equipmentUsage.onItemUse(this.player, primaryItemSlot)) {
						// Decrement the item quantity.
						primaryItemSlot.setQuantity(primaryItemSlot.getQuantity() - 1);
						// If the new quantity is 0 then remove it from slot/inventory.
						if(primaryItemSlot.getQuantity() == 0) {
							// We have used the last unit of this item.
							itemSlotFreed = true;
						}
					}
				}
			} else {
				// Attempt to use our single unit of this item.
				itemSlotFreed = equipmentUsage.onItemUse(this.player, primaryItemSlot);
			}
			// Was the item slot freed up?
			if(itemSlotFreed) {
				// We have used our item!
				primaryItemSlot = null;
				// TODO May have to remove item from inventory.
				// If the secondary slot is not empty, then swap them.
				this.swapItems();
			}
		} else {
			// Play sound effect to show this action failed.
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
	}
	
	/**
	 * Use the primary item.
	 */
	public void useAmmo() {
		// Try to use the primary ammo if there is one.
		if(primaryAmmoSlot != null) {
			// Was the ammo slot freed
			boolean ammoSlotFreed = false;
			// There is ammo! use it.
			if(primaryAmmoSlot.isQuantifiable()) {
				// Does the ammo have an infinite quantity?
				if(primaryAmmoSlot.getQuantity() == -1) {
					// Attempt to use the ammo! (can fail)
					equipmentUsage.onAmmoUse(this.player, primaryAmmoSlot);
				} else {
					// Use the ammo (can fail, if so don't decrement ammo quantity)
					if(equipmentUsage.onAmmoUse(this.player, primaryAmmoSlot)) {
						// Decrement the ammo quantity.
						primaryAmmoSlot.setQuantity(primaryAmmoSlot.getQuantity() - 1);
						// If the new quantity is 0 then remove it from slot/inventory.
						if(primaryAmmoSlot.getQuantity() == 0) {
							// We have used the last unit of this ammo.
							ammoSlotFreed = true;
						}
					}
				}
			} else {
				// Attempt to use up our single unit of ammo.
				ammoSlotFreed = equipmentUsage.onAmmoUse(this.player, primaryAmmoSlot);
			}
			// Was the ammo slot freed up?
			if(ammoSlotFreed) {
				// We have used our ammo!
				primaryAmmoSlot = null;
				// TODO May have to remove ammo from inventory.
				// If the secondary slot is not empty, then swap them.
				this.swapAmmo();
			}
		} else {
			// Play sound effect to show this action failed.
			Audio.getSoundEffect(Audio.SoundEffect.BLIP_LOW).play();
		}
	}
	
	/**
	 * Set the primary item slot.
	 * @param item
	 */
	public void setPrimaryItemSlot(Item item) { this.primaryItemSlot = item; }
	
	/**
	 * Get the primary item slot.
	 * @returns item
	 */
	public Item getPrimaryItemSlot() { return this.primaryItemSlot; }
	
	/**
	 * Set the secondary item slot.
	 * @param item
	 */
	public void setSecondaryItemSlot(Item item) { this.secondaryItemSlot = item; }
	
	/**
	 * Get the secondary item slot.
	 * @returns item
	 */
	public Item getSecondaryItemSlot() { return this.secondaryItemSlot; }
	
	/**
	 * Set the primary ammo slot.
	 * @param ammo
	 */
	public void setPrimaryAmmoSlot(Ammo ammo) { this.primaryAmmoSlot = ammo; }
	
	/**
	 * Get the primary ammo slot.
	 * @returns ammo
	 */
	public Ammo getPrimaryAmmoSlot() { return this.primaryAmmoSlot; }
	
	/**
	 * Set the secondary ammo slot.
	 * @param ammo
	 */
	public void setSecondaryAmmoSlot(Ammo ammo) { this.secondaryAmmoSlot = ammo; }
	
	/**
	 * Get the secondary ammo slot.
	 * @returns ammo
	 */
	public Ammo getSecondaryAmmoSlot() { return this.secondaryAmmoSlot; }

	/**
	 * Get the equipment usage helper.
	 * @return equipment usage helper
	 */
	public EquipmentUsage getEquipmentUsage() { return equipmentUsage; }

	/**
	 * Set the equipment usage helper.
	 * @param equipment usage helper
	 */
	public void setEquipmentUsage(EquipmentUsage equipmentUsage) { this.equipmentUsage = equipmentUsage; }
	
	/**
	 * Set the shield slot.
	 * @param shield
	 */
	public void setShieldSlot(Shield shield) { this.shield = shield; }
	
	/**
	 * Get the shield slot.
	 * @returns shield
	 */
	public Shield getShieldSlot() { return this.shield; }
	
	/**
	 * Set the helmet slot.
	 * @param helmet
	 */
	public void setHelmetSlot(Helmet helmet) { this.helmet = helmet; }
	
	/**
	 * Get the helmet slot.
	 * @returns helmet
	 */
	public Helmet getHelmetSlot() { return this.helmet; }
}
