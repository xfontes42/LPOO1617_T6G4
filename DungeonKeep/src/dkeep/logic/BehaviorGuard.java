package dkeep.logic;

public class BehaviorGuard extends Behavior {

	private int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	private int nextMove = 0;
	
	public BehaviorGuard() {
		
	}
	
	public int movement(){
		int result = moves_pre2etermine2[nextMove];
		nextMove = (nextMove+1)%moves_pre2etermine2.length;
		return result;
	}

}
