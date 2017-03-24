package dkeep.logic;

/**
 * The level element that denotes a possible exit.
 * 
 * The hero cannot go through doors while they are closed (the sprite is an 'I'), only when they are open (sprite is an 'S').
 */
public class Door extends Entity {
	public char sprite = 'I';
}
