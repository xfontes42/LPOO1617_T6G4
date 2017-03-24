package dkeep.logic;

/**
 * The behavior that drunken guards have. 
 * 
 * They occasionally fall asleep, and may reverse their direction upon waking up. 
 * The hero will not be caught if the guard is asleep.
 * 
 * Drunken guards have a probability of 1 in 6 of falling asleep, 1 in 5 of then waking up and of 1 in 3 of reversing direction when waking up.
 */
public class BehaviorDrunken extends BehaviorGuard {

	protected int reverseProb = 3;
	protected boolean sleeping = false;
	protected int sleepProb = 6; // prob = 1/6 = 17%
	protected int awakeProb = 5; // prob = 1/5 = 20%

	public BehaviorDrunken() {
		
	}

	/**
	 * Returns the guard's next move. They may also end up falling asleep or reversing direction.
	 * 
	 * @return the guard's next move
	 */
	public int movement() {
		int result;

		if (sleeping) {
			result = 5; // stays in place

			// calculates if still asleep
			if (setReverse(awakeProb)) { // acorda
				sleeping = false;
				if (setReverse(reverseProb)) {
					reversing = !reversing;
				}
			}

		} else {
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

			// calculates if falling asleep
			if (setReverse(sleepProb)) {
				sleeping = true;

			}
		}
		return result;
	}

}
