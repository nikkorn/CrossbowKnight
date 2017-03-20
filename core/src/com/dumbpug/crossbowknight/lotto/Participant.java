package com.dumbpug.crossbowknight.lotto;

/**
 * A participant in the lottery draw.
 * @author nikolas.howard
 */
public class Participant<T> {
	/** The number of tickets that this participant holds. */
	private int tickets = 1;
	/** The participant. */
	private T participant;
	
	/**
	 * Create a new instance of the Participant class.
	 * @param participant
	 */
	public Participant(T participant) { this.participant = participant; }
	
	/**
	 * Create a new instance of the Participant class.
	 * @param participant
	 * @param tickets
	 */
	public Participant(T participant, int tickets) { 
		this.participant = participant; 
		this.tickets     = tickets;
	}
	
	/**
	 * Get the number of participant tickets.
	 * @return tickets.
	 */
	public int getTickets() { return tickets; }

	/**
	 * Get the participant.
	 * @return participant
	 */
	public T getParticipant() { return participant; }
}
