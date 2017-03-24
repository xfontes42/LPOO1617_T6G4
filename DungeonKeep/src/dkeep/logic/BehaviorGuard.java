package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * The base class for a guard's behavior.
 * 
 * The guard's behavior will dictate how they follow their predetermined path (if they stop, how often they reverse direction).
 * Each behavior will have a different probability of changing the way they are following the path.
 * 
 * @see Guard
 * @see BehaviorRookie
 * @see BehaviorDrunken
 * @see BehaviorSuspicious
 */
public class BehaviorGuard implements Behavior, Serializable{

	protected int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	protected int nextMove = 0;
	protected boolean reversing = false;
	protected int reverseProb = 0;
	
	
	public BehaviorGuard() {
		
	}
	
	/**
	 * @brief Returns the next direction the guard is heading.
	 * @return the next direction they're heading (1-4)
	 */
	public int movement(){
		int result = moves_pre2etermine2[nextMove];
		nextMove = (nextMove+1)%moves_pre2etermine2.length;
		return result;
	}
	
	/**
	 * @brief Calculates a direction opposite to the one provided.
	 * @param dir the direction being reversed
	 * @return the opposite direction (e.g. the opposite of 1, up, is 2, down)
	 */
	public int reverseDirection(int dir){
		int result = 0;
		
		switch(dir){
		case 1:
			result = 2;
			break;
		case 2:
			result = 1;
			break;
		case 3:
			result = 4;
			break;
		case 4:
			result = 3;
			break;
		case 5:
			result = 5;
			break;
		default:
			result = 0;
			break;
		}
		
		return result;
	}
	
	/**
	 * @brief Toggles the guard's walking direction, depending on their reversing probabilty
	 * @param prob the guard's reversing probability (e.g. if prob = 5, the probability is of 20% (1/5 = 0.2))
	 * @return true if the direction was reversed, false if they stay walking the same direction
	 */
	public boolean setReverse(int prob){
		
		Random rand = new Random();
		int x = rand.nextInt(prob);
		
		if (x == 0)
			return true;
		else
			return false;
	}
	

}
