package dkeep.logic;

public class Key extends Entity {
	public char sprite = 'k';
	public void moveEntity(){}
	
	public void openDoors(Hero hero){
		hero.setSprite('K');
		hero.hasKey = true;
		
	}
}
