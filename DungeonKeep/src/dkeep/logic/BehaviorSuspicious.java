package dkeep.logic;

public class BehaviorSuspicious extends BehaviorGuard {

	protected int reverseProb = 7;
	
	public BehaviorSuspicious() {
		// TODO Auto-generated constructor stub
	}

	public int movement() {
		System.out.println("\n wat");
		int result;
		if (!reversing) {
			result = moves_pre2etermine2[nextMove];
			nextMove = (nextMove + 1) % moves_pre2etermine2.length;
		} else {
			result = reverseDirection(moves_pre2etermine2[nextMove]);
			nextMove = (nextMove - 1) % moves_pre2etermine2.length;
		}
	
		reversing = setReverse(reverseProb);
		
		return result;
	}
	
	
}
