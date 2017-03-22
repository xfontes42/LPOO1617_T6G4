package dkeep.logic;
import java.util.Random;

public class Ogre extends Entity {
	public char sprite = 'O';
	public MassiveClub mclub = null;
	public boolean hasClub = false;
	public int stunnedForNTurns = 0;
	public int generateMovement(){
		Random random = new Random();
		int result = random.nextInt(4) + 1;
		return result;
	}

}
