package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class BehaviorGuard implements Behavior, Serializable{

	protected int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	protected int nextMove = 0;
	protected boolean reversing = false;
	protected int reverseProb = 0;
	
	
	public BehaviorGuard() {
		
	}
	
	public int movement(){
		int result = moves_pre2etermine2[nextMove];
		nextMove = (nextMove+1)%moves_pre2etermine2.length;
		return result;
	}
	
	public int reverseDirection(int dir){
		int result = 0;
		
		switch(dir){
		case 1:
			result = 2;
			break;
		case 2:
			result = 1;
			break;
		case 3:
			result = 4;
			break;
		case 4:
			result = 3;
			break;
		case 5:
			result = 5;
			break;
		default:
			result = 0;
			break;
		}
		
		return result;
	}
	
	public boolean setReverse(int prob){
		
		Random rand = new Random();
		int x = rand.nextInt(prob);
		
		if (x == 0)
			return true;
		else
			return false;
	}
	

}
