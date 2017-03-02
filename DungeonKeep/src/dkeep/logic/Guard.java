package dkeep.logic;

import java.util.Random;

public class Guard extends Entity {
	public Behavior behavior;

	public enum GuardType {rookie, drunken, suspicious};
	
	public GuardType type;

	public char sprite = 'G';
	public int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	public int nextMove = 0;

	public Guard() {
		Random rand = new Random();

		int n = rand.nextInt(3);
		switch (n) {
		case 0:
			behavior = new BehaviorRookie();
			type = GuardType.rookie;
			break;
		case 1:
			behavior = new BehaviorDrunken();
			type = GuardType.drunken;
			break;
		case 2:
			behavior = new BehaviorSuspicious();
			type = GuardType.suspicious;
			break;
		}
	}

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

	public void move(int direction) { // 0 if predetermined, not-zero otherwise
		if (direction == 0) {

		}
	} // think this through
}
