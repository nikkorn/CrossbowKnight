package com.dumbpug.crossbowknight.leveleditor.menu.button;

import java.util.ArrayList;

/**
 * Holds a number of menu buttons where only a single button can be active at a time.
 * Created by nik on 09/10/16.
 */
public class MenuButtonGroup<T extends MenuButton> {
    /** The list of menu buttons. */
    private ArrayList<T> menuButtonList;

    /**
     * Creates a new instance of MenuButtonGroup.
     */
    public MenuButtonGroup() { menuButtonList = new ArrayList<T>(); }

    /**
     * Creates a new instance of MenuButtonGroup.
     * Sets the state of the menu button list.
     */
    public MenuButtonGroup(ArrayList<T> buttons) { this.menuButtonList = buttons; }

    /**
     * Get all of the buttons in this group.
     * @return buttons
     */
    public ArrayList<T> getButtons() { return menuButtonList; }

    /**
     * Add a button to this group.
     * @param button
     */
    public void add(T button) {
        if(menuButtonList.contains(button)) {
            throw new RuntimeException("Cannot add button to group, already exists in group");
        }
        menuButtonList.add(button);
    }

    /**
     * Remove a button from this group.
     * @param button
     */
    public void remove(T button) {
        if(!menuButtonList.contains(button)) {
            throw new RuntimeException("Cannot remove button from group as it is not present in the group");
        }
        menuButtonList.remove(button);
    }

    /**
     * Set the active button in this group.
     * @param button
     */
    public void setActive(T button) {
        if(!menuButtonList.contains(button)) {
            throw new RuntimeException("The supplied button does not exist is this group");
        }
        for (T listButton : menuButtonList) {
            listButton.setActive(false);
        }
        button.setActive(true);
    }

    /**
     * Disable a button in this group.
     * @param button
     */
    public void disable(T button) {
        if(!menuButtonList.contains(button)) {
            throw new RuntimeException("The supplied button does not exist is this group");
        }
        button.setActive(false);
    }

    /**
     * Disable all buttons in this group.
     * @param button
     */
    public void disableAll(T button) {
        for (T listButton : menuButtonList) {
            listButton.setActive(false);
        }
    }

    /**
     * Get the active button in this group.
     * @return active button
     */
    public T getActive() {
        for (T listButton : menuButtonList) {
            if(listButton.isActive()) {
                return listButton;
            }
        }
        return null;
    }
}
