package dkeep.logic;

public class Entity {
	private int coordX, coordY;
	private char sprite = '?';
	//private Behavior behavior = new BehaviorGuard();

	public Entity() {

	}

	public void moveEntity(int direction) {
		switch (direction) {
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
		case 5:
			break;
		}
	}

	public Entity(int x, int y, char spr) {
		coordX = x;
		coordY = y;
		sprite = spr;
	}

	public void startAtPosition(int x, int y) {
		coordX = x;
		coordY = y;
	}

	public void setX(int x) {
		coordX = x;
	}

	public void setY(int y) {
		coordY = y;
	}

	public void setSprite(char spr) {
		sprite = spr;
	}

	public int getX() {
		return coordX;
	}

	public int getY() {
		return coordY;
	}

	public char getSprite() {
		return sprite;
	}

	public int movement() {
		return 5;
	}

}
