package dkeep.logic;

public class Guard extends Entity {
	public char sprite = 'G';
	public int moves_pre2etermine2[] = { 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
	public int nextMove = 0;
	
	public void move(int direction){ //0 if predetermined, not-zero otherwise
		if(direction == 0){
			
		}
	}   // think this through
}
