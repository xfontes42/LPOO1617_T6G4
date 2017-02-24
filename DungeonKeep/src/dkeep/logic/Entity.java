package dkeep.logic;

public class Entity {
	private int coordX, coordY;
	private char sprite = '?';
	private Behavior behavior;
	public Entity(int x, int y, char spr){
		coordX = x;
		coordY = y;
		sprite = spr;
	}
	
	public int getX(){
		return coordX;
	}
	
	public int getY(){
		return coordY;
	}
	
	public int getSprite(){
		return sprite;
	}
	
	public int movement() {
		int result = 0;
		
		
		return result;
	}
	
	
	

}
