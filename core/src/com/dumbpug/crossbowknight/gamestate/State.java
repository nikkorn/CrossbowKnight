package com.dumbpug.crossbowknight.gamestate;

/**
 * Represents a game state.
 * @author nikolas.howard
 */
public abstract class State {
    /** This will be set to a state name if this state wants the state manager to render a different state. */
    private String targetStateName = null;

    /**
     * This method can be called to set flags to tell the state manager that a new state should be loaded.
     * @param newStateName
     */
    public void changeState(String newStateName){ targetStateName = newStateName; }

    /**
     * Called by the state manager before every render. null = no request, any other
     * value will indicate a request and will be the name of the new state.
     * @return targetStateName
     */
    public String getTargetStateName() { return targetStateName; }

    /**
     * Called by the state manager when we move to another state and we are not
     * deleting this state.
     */
    public void resetStateChangeRequest() { targetStateName = null; }

    /**
     * Called by the StateManager when this state was made current on
     * request from the (previously) current state.
     * @param state The previously current state
     */
    public abstract void onTransitionFromState(State state);

    /**
     * Render the state.
     */
    public abstract void renderState();

    /**
     * Get the name of this state.
     * @return name
     */
    public abstract String getStateName();
}