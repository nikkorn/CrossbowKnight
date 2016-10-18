package com.dumbpug.crossbowknight.gamemenu;

import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.gamemenu.tabs.CharacterMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.EquipmentMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabList;
import com.dumbpug.crossbowknight.gamemenu.tabs.InventoryMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.MapMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.SystemMenuTab;

/**
 * Represents the in-game menu.
 * Created by nik on 16/10/16.
 */
public class GameMenu {
	/** The drawer of the game menu. */
	private GameMenuDrawer gameMenuDrawer;
	/** The menu tab list. */
	private GameMenuTabList menuTabList;
	/** The menu tabs. */
	private InventoryMenuTab inventoryMenuTab;
	private EquipmentMenuTab equipmentMenuTab;
	private CharacterMenuTab characterMenuTab;
	private MapMenuTab mapMenuTab;
	private SystemMenuTab systemMenuTab;
	
	/**
	 * Create a new instance of the GameMenu class.
	 */
	public GameMenu() {
		// Create the menu drawer.
		gameMenuDrawer = new GameMenuDrawer(this);
		// Create the menu tabs.
		inventoryMenuTab = new InventoryMenuTab();
		equipmentMenuTab = new EquipmentMenuTab();
		characterMenuTab = new CharacterMenuTab();
		mapMenuTab       = new MapMenuTab();
		systemMenuTab    = new SystemMenuTab();
		// Create the menu tab list.
		menuTabList = new GameMenuTabList();
		menuTabList.add(inventoryMenuTab);
		menuTabList.add(equipmentMenuTab);
		menuTabList.add(characterMenuTab);
		menuTabList.add(mapMenuTab);
		menuTabList.add(systemMenuTab);
	}

	/**
	 * Update the game menu.
	 */
	public void update() {
		// Check whether the player wants to change the tab.
		if(CrossbowKnight.getPlayerInput().isCycleLeftButtonPressed()) {
			menuTabList.switchLeft();
		} else if(CrossbowKnight.getPlayerInput().isCycleRightButtonPressed()) {
			menuTabList.switchRight();
		}
		// Update the current tab.
		this.menuTabList.getCurrentMenuTab().update();
	}
	
	/**
	 * Get the game menu tab list.
	 * @return menuTabList.
	 */
	public GameMenuTabList getGameMenuTabList() { return this.menuTabList; }
	
	/**
	 * Get the drawer for the game menu.
	 * @return gameMenuDrawer.
	 */
	public GameMenuDrawer getGameMenuDrawer() { return this.gameMenuDrawer; }
}
