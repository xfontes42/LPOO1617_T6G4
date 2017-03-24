package dkeep.logic;

/**
 * The player character.
 * 
 * The hero's objective is to escape the level without being caught by the guards or struck by the ogres.
 * This character may also be able to have a club in order to be able to stun ogres, in the event they run into one.
 */
public class Hero extends Entity {
	
	boolean hasKey = false;
	public boolean hasClub = false;
	public Hero(){
		this.sprite = 'H';
	}
	
}
