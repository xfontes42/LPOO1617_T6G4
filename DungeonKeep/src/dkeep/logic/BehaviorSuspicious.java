package dkeep.logic;

public class BehaviorSuspicious extends BehaviorGuard {

	protected int reverseProb = 6;

	public BehaviorSuspicious() {
		// TODO Auto-generated constructor stub
	}

	public int movement() {
		int result;
		if (!reversing) {
			result = moves_pre2etermine2[nextMove];
			nextMove = (nextMove + 1) % moves_pre2etermine2.length;
		} else {
			if (nextMove == 0) {
				result = reverseDirection(
						moves_pre2etermine2[(moves_pre2etermine2.length - 1) % moves_pre2etermine2.length]);
				nextMove = (moves_pre2etermine2.length - 1) % moves_pre2etermine2.length;
			} else {
				result = reverseDirection(moves_pre2etermine2[(nextMove - 1) % moves_pre2etermine2.length]);
				nextMove = (nextMove - 1) % moves_pre2etermine2.length;
			}
		}

		//calculates if he's going to reverse
		if (setReverse(reverseProb))
			reversing = !reversing;

		return result;
	}


}
