package dkeep.logic;

/**
 * The level element the player needs to reach in order to be able to open the level exit.
 * 
 * In the dungeons this element is a lever, while in the keep this is a key. 
 */
public class Key extends Entity {
	public char sprite = 'k';
	public void moveEntity(){}
	
	/**
	 * Gives the hero a key.
	 * 
	 * @param hero the player character
	 */
	public void openDoors(Hero hero){
		hero.setSprite('K');
		hero.hasKey = true;
		
	}
}
