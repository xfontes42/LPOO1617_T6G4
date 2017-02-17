package DungeonKeep;

public class Entity {
	public int coordX, coordY;
	public int nextMove;
	public void startAtPosition(int x, int y){
		coordX = x;
		coordY = y;
	}
	
	public void moveEntity(int direction){
		switch(direction){
		case 1:
			coordY--;
			break;
		case 2:
			coordY++;
			break;
		case 3:
			coordX--;
			break;
		case 4:
			coordX++;
			break;
		}
	}
}
	
