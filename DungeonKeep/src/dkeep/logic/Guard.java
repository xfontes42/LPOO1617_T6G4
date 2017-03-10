package dkeep.logic;

import java.util.Random;

public class Guard extends Entity {
	//public Vector<Behavior> behavior = new Vector<Behavior>(0);
	public Behavior behavior = new BehaviorGuard();
	public enum GuardType {rookie, drunken, suspicious};
	
	public GuardType type;

	public char sprite = 'G';
	public int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	public int nextMove = 0;
	
	/*
	public Guard() {
		Random rand = new Random();

		int n = rand.nextInt(3);
		switch (n) {
		case 0:
			//behavior.add(new BehaviorRookie());
			behavior = new BehaviorRookie();
			type = GuardType.rookie;
			break;
		case 1:
			//behavior.add(new BehaviorDrunken());
			behavior = new BehaviorDrunken();
			type = GuardType.drunken;
			break;
		case 2:
			//behavior.add(new BehaviorSuspicious());
			behavior = new BehaviorSuspicious();
			type = GuardType.suspicious;
			break;
		}
	}*/

	public Guard(int n) {
		switch (n) {

		case 1:
			//behavior.add(new BehaviorDrunken());
			behavior = new BehaviorDrunken();
			type = GuardType.drunken;
			break;
		case 2:
			//behavior.add(new BehaviorSuspicious());
			behavior = new BehaviorSuspicious();
			type = GuardType.suspicious;
			break;
		default:
			//behavior.add(new BehaviorRookie());
			behavior = new BehaviorRookie();
			type = GuardType.rookie;
			break;
		}
	}

	public int movement() {
		//return behavior.get(0).movement();
		return behavior.movement();
	}
	
	public Behavior getBehavior(){
		return behavior;
	}
}
