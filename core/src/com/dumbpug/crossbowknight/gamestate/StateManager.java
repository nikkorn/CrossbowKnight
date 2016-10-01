package com.dumbpug.crossbowknight.gamestate;

import java.util.ArrayList;

/**
 * Manages the rendering of and navigation between states.
 * @author nikolas.howard
 */
public class StateManager {
    /** The current state that will be rendered. */
    private State currentState;
    /** The list of available States. */
    private ArrayList<State> states = new ArrayList<State>();

    /**
     * Set the current State.
     * @param state
     */
    public void setCurrentState(State state){
        this.currentState = state;
    }

    /**
     * Get the state with the provided state name.
     * @param stateName
     * @return state
     */
    public State getState(String stateName){
        for(State state: states) {
            if(state.getStateName().equals(stateName)) {
                // We have found the state we want.
                return state;
            }
        }
        // We did not find a matching state.
        return null;
    }

    /**
     * Remove an active state, although this cannot be the current state.
     * @param stateName
     */
    public void removeState(String stateName){
        // Get the state we want to remove.
        State stateToRemove = getState(stateName);
        // Were we able to find the state we want to delete?
        if(stateToRemove != null) {
            // Make sure that we are not removing the current state.
            if(stateToRemove == this.currentState) {
                throw new RuntimeException("error: cannot remove current state.");
            }
            // Remove the target state.
            states.remove(stateName);
        } else {
            throw new RuntimeException("error: cannot remove state '" + stateName + "' as is does not exist.");
        }
    }

    /**
     * Add a State if it is not already an active state.
     * @param state
     */
    public void addState(State state){
        // Only add this state if it does not already exist in our states list.
        if(getState(state.getStateName()) == null) {
            states.add(state);
        }
    }

    /**
     * Renders the current state.
     */
    public void renderCurrentState(){
        String targetStateName;
        // Check for a state change request
        if((targetStateName = currentState.getTargetStateName()) != null) {
            // A state change request has been made by the current state
            // Firstly, reset the change request in the current state, in
            // case we want to use it again at some point.
            currentState.resetStateChangeRequest();
            // Next, get the state we want to make current.
            State targetState = getState(targetStateName);
            // Make sure that we actually got a state.
            if(targetState != null) {
                targetState.onTransitionFromState(currentState);
                // Finally, set the current state to be the target one.
                setCurrentState(targetState);
            } else {
                throw new RuntimeException("error: cannot change to state '" + targetStateName + "' as is does not exist.");
            }
        }
        // Render the current state.
        currentState.renderState();
    }
}