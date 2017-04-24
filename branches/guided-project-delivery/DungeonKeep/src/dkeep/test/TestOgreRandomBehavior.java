package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestOgreRandomBehavior {
	char[][] testMap = { { 'X', 'X', 'I', 'I', 'X' }, { 'X', 'H', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', 'X' },
			{ 'X', 'O', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

	//Improve this version to have the Ogre actually moving in the map.
	@Test(timeout = 1000)
	public void testRandomBehavior() {
		State game = new State(testMap);
		game.startEntities();
		for (int i = 0; i < game.entities.size(); i++) {
			if (game.entities.get(i) instanceof Ogre) {
				Ogre shrek = (Ogre) game.entities.get(i);
				boolean moved_up = false;
				boolean moved_down = false;
				boolean moved_left = false;
				boolean moved_right = false;
				while (!(moved_up && moved_down && moved_left && moved_right)) {
					int mov = shrek.generateMovement();
					switch (mov) {
					case 1:
						moved_up = true;
						break;
					case 2:
						moved_down = true;
						break;
					case 3:
						moved_left = true;
						break;
					case 4:
						moved_right = true;
						break;
					default:
						fail("Expected a move between 1 and 4");
						break;
					}
				}

			}
		}
	}

}
