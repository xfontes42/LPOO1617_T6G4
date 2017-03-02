package dkeep.logic;

public class BehaviorDrunken extends BehaviorGuard {

	protected int reverseProb = 3;
	protected boolean sleeping = false;
	protected int sleepProb = 6; // prob = 1/6 = 17%
	protected int awakeProb = 5; // prob = 1/5 = 20%

	public BehaviorDrunken() {
		// TODO Auto-generated constructor stub
	}

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
			if (setReverse(sleepProb))
				sleeping = true;

		}

		return result;
	}
	
}
