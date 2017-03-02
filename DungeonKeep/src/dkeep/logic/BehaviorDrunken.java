package dkeep.logic;

public class BehaviorDrunken extends BehaviorGuard {

	protected int reverseProb = 3;
	protected boolean sleeping = false;
	protected int sleepProb = 6;
	protected int awakeProb = 5;
	
	public BehaviorDrunken() {
		// TODO Auto-generated constructor stub
	}

	public int movement() {
		int result;
		if (!reversing) {
			result = moves_pre2etermine2[nextMove];
			nextMove = (nextMove + 1) % moves_pre2etermine2.length;
		} else {
			result = reverseDirection(moves_pre2etermine2[nextMove]);
			nextMove = (nextMove - 1) % moves_pre2etermine2.length;
		}

		
		return result;
	}
}
