package DungeonKeep;
import java.util.Random;

public class Ogre extends Entity {
	public char sprite = 'O';
	
	public int generateMovement(){
		Random random = new Random();
		int result = random.nextInt(4) + 1;
		return result;
	}

}
