package DungeonKeep;

public class Guard extends Entity {
	public char sprite = 'G';
	public char moves_predetermined[] = { 'l', 'd', 'd', 'd', 'd', 'l', 'l', 'l', 'l', 'l', 'l', 'd', 'r', 'r', 'r',
			'r', 'r', 'r', 'r', 'u', 'u', 'u', 'u', 'u' };
	public int nextMove = 0;
	
	public void move(int direction){ //0 if predetermined, not-zero otherwise
		if(direction == 0){
			
		}
	}   // think this through
}
