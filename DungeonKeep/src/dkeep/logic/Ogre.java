package dkeep.logic;
import java.util.Random;

/**
 * The enemy that attacks the player in the Keep.
 * 
 * The ogre moves in a random direction, and can have a club they swing in a random direction.
 * This enemy may be stunned for two turns by the hero if they have a weapon, but they will still swing the club.
 */
public class Ogre extends Entity {
	public char sprite = 'O';
	public MassiveClub mclub = null;
	public boolean hasClub = false;
	public int stunnedForNTurns = 0;
	
	/**
	 * Generates a movement direction for the ogre and the club.
	 * 
	 * @return The direction (up, down, left or right)
	 */
	public int generateMovement(){
		Random random = new Random();
		int result = random.nextInt(4) + 1;
		return result;
	}

}
