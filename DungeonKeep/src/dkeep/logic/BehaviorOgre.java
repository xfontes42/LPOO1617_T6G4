package dkeep.logic;

import java.util.Random;

public class BehaviorOgre extends Behavior {

	public BehaviorOgre() {
		// TODO Auto-generated constructor stub
	}

	public int movement(){
		Random random = new Random();
		int result = random.nextInt(4) + 1;
		return result;
	}
}
