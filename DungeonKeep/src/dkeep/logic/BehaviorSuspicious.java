package dkeep.logic;

/**
 * The behavior that suspicious guards have. 
 * 
 * They never stop, but they may reverse direction randomly (with a probability of 1 in 6).
 */
public class BehaviorSuspicious extends BehaviorGuard {

	protected int reverseProb = 6;

	public BehaviorSuspicious() {
		
	}

	/**
	 * Returns the guard's next move. They may also end up reversing their direction.
	 * 
	 * @return the guard's next move
	 */
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
