package dkeep.logic;

import java.util.Random;

/**
 * The enemy found in the dungeon level.
 * 
 * A guard can have one of three types of personality: rookie, drunken and suspicious.
 * They move in the same predetermined path, but may change their direction depending on their behavior.
 * They will catch the hero if they are in an alert state and in a cell adjacent to them.
 * 
 * @see BehaviorGuard
 */
public class Guard extends Entity {

	public Behavior behavior = new BehaviorGuard();
	
	/**
	 * An enum for the guard type (rookie, drunken or suspicious).
	 */
	public enum GuardType {rookie, drunken, suspicious};
	
	public GuardType type;

	public char sprite = 'G';
	public int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	public int nextMove = 0;
	
	/**
	 * Creates a guard.
	 * 
	 * @param n the guard's personality
	 */
	public Guard(int n) {
		switch (n) {

		case 1:
	
			behavior = new BehaviorDrunken();
			type = GuardType.drunken;
			break;
		case 2:
			
			behavior = new BehaviorSuspicious();
			type = GuardType.suspicious;
			break;
		default:
		
			behavior = new BehaviorRookie();
			type = GuardType.rookie;
			break;
		}
	}

	/**
	 * Retrieves the current position in the guard's trajectory.
	 */
	public int movement() {
		return behavior.movement();
	}
	
	/**
	 * Retrieves the guard's behavior.
	 * 
	 * @return the guard's behavior
	 */
	public Behavior getBehavior(){
		return behavior;
	}
}
