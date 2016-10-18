package com.dumbpug.crossbowknight.gamemenu.tabs;

import java.util.ArrayList;

/**
 * Holds our in-game menu tabs.
 * @author nikolas.howard
 */
public class GameMenuTabList {
	/** The list of GameMenuTabs. */
	private ArrayList<GameMenuTab> tabs = new ArrayList<GameMenuTab>();
	/** The index of the current menu tab. */
	private int currentTabIndex = 0;
	
	/**
	 * Add a GameMenuTab to this list.
	 * @param gameMenuTab
	 */
	public void add(GameMenuTab gameMenuTab) {
		this.tabs.add(gameMenuTab);
	}
	
	/**
	 * Switch to the tab to  the left of the current one.
	 */
	public void switchLeft() {
		if(currentTabIndex == 0) {
			currentTabIndex = tabs.size() - 1;
		} else {
			currentTabIndex--;
		}
	}
	
	/**
	 * Switch to the tab to  the right of the current one.
	 */
	public void switchRight() {
		if(currentTabIndex == (tabs.size() - 1)) {
			currentTabIndex = 0;
		} else {
			currentTabIndex++;
		}
	}

	/**
	 * Get the current menu tab.
	 * @return current menu tab
	 */
	public GameMenuTab getCurrentMenuTab() { return tabs.get(currentTabIndex); }
}
